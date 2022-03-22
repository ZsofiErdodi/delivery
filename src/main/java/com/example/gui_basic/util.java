package com.example.gui_basic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class util {

    static double partialResult = 0;
    static double subtotal = 0;
    static double sum = 0;
    static Integer noOfPackages = 1;       // number on packages affects the total price
    static Double m1;
    static Double m2;
    static Double m3;

  /**  ORDERING OF DIMENSIONS
   *     numbers must be mixed because m1 (the only variable value for packet sizes) can be larger than m2
   *     AFTER ORDERING: m2 max 36, m1 is the smallest number above 36, m3 the largest number
   */
    public static void sizeOrder(Double x, Double y, Double z) {
        Double[] size = new Double[]{x, y, z};
        Arrays.sort(size);

        if (size[1] > 36 ) {
            m1 = size[1];
            m2 = size[0];
            m3 = size[2];
        } else {
            m1 = size[0];
            m2 = size[1];
            m3 = size[2];
        }
    }

  /**
   *   CALCULATION
   */
    public static double calculate(int distance, double size1, double size2, double size3, double weight) {
        sizeOrder(size1, size2, size3);

        if (distance < 5) {
            partialResult = 500;
        } else if (distance < 100) {
            partialResult = 750;
        } else if (distance >= 100) {
            partialResult = 1000;
        }

        if ((m1 > 11.5) && (m1 <= 19.5)) {
            partialResult = 1.1 * partialResult;
        } else if (m1 > 19.5) {
            partialResult = 1.2 * partialResult;
        }

        if ((weight > 1) && (weight <= 3)) {
            partialResult = 1.1 * partialResult;
        } else if ((weight > 3) && (weight <= 10)) {
            partialResult = 1.2 * partialResult;
        } else if ((weight > 10) && (weight <= 20)) {
            partialResult = 1.3 * partialResult;
        }

        BigDecimal bd1 = new BigDecimal(partialResult).setScale(2, RoundingMode.HALF_UP);
        partialResult = bd1.doubleValue();
        BigDecimal bd2 = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);
        sum = bd2.doubleValue();

        subtotal = subtotal + partialResult;

        if (noOfPackages < 2) {
            sum = subtotal;
        } else {
            if (noOfPackages >= 2 && noOfPackages < 5) {
                sum = subtotal * 0.85;
            } else if (noOfPackages >= 5) {
                sum = subtotal * 0.75;
            }
        }

        noOfPackages++;

        return sum;
    }

  /**
   *   RESET FIELDS (while continuing to count with the partial results)
   */
    public static void reset() {
        main.m1Field.setText("");
        main.m2Field.setText("");
        main.m3Field.setText("");
        main.distanceField.setText("");
        main.weightField.setText("");

        main.m1Field.setStyle("-fx-background-color: WHITE");
        main.m2Field.setStyle("-fx-background-color: WHITE");
        main.m3Field.setStyle("-fx-background-color: WHITE");
        main.weightField.setStyle("-fx-background-color: WHITE");
        main.distanceField.setStyle("-fx-background-color: WHITE");
    }

  /**
   *   CANCEL THE ENTIRE PACKAGE DELIVERY PROCESS
   */
    public static void clear() {
        reset();
        partialResult = 0;
        subtotal = 0;
        sum = 0;
        noOfPackages = 1;
        main.noField.setText(noOfPackages.toString());
        main.sum.setText(Double.toString(sum));
    }

  /**
   *   CHECK INPUT FIELDS (form & value)
   */
    public static boolean inputCheck(String tav, String meret1, String meret2, String meret3, String tomeg) {
        boolean goodOrNot = true;

        //resetting colors
        main.m1Field.setStyle("-fx-background-color: WHITE");
        main.m2Field.setStyle("-fx-background-color: WHITE");
        main.m3Field.setStyle("-fx-background-color: WHITE");
        main.weightField.setStyle("-fx-background-color: WHITE");
        main.distanceField.setStyle("-fx-background-color: WHITE");

        try {
            Double.parseDouble(meret1);
        } catch(NumberFormatException e) {
            goodOrNot = false;
            main.m1Field.setStyle("-fx-background-color: RED");
            if (meret1 == "") {
                errormessage.display("Size field empty");
            } else {
                errormessage.display("Data format error");
            }
        }

        try {
            Double.parseDouble(meret2);
        } catch(NumberFormatException e) {
            goodOrNot = false;
            main.m2Field.setStyle("-fx-background-color: RED");
            if (meret2 == "") {
                errormessage.display("Size field empty");
            } else {
                errormessage.display("Data format error");
            }
        }

        try {
            Double.parseDouble(meret3);
        } catch(NumberFormatException e) {
            goodOrNot = false;
            main.m3Field.setStyle("-fx-background-color: RED");
            if (meret3 == "") {
                errormessage.display("Size field empty");
            } else {
                errormessage.display("Data format error");
            }
        }

        try {
            Double.parseDouble(tomeg);
        } catch(NumberFormatException e) {
            goodOrNot = false;
            main.weightField.setStyle("-fx-background-color: RED");
            if (tomeg == "") {
                errormessage.display("Weight field empty");
            } else {
                errormessage.display("Data format error");
            }
        }

        try {
            Integer.parseInt(tav);
        } catch(NumberFormatException e) {
            goodOrNot = false;
            main.distanceField.setStyle("-fx-background-color: RED");
            if (tav == "") {
                errormessage.display("Distance field empty");
            } else {
                errormessage.display("Data format error");
            }
        }

        // Check values: there's a max value for size and weight & none of the fields should be <= 0
        // IMPORTANT: these ifs must be after checking the format of the fields
        sizeOrder(Double.parseDouble(meret1), Double.parseDouble(meret2), Double.parseDouble(meret3));
        if ( (m1 > 37.5) || (m2 > 36) || (m3 > 61) ) {
            goodOrNot = false;
            main.m1Field.setStyle("-fx-background-color: RED");
            main.m2Field.setStyle("-fx-background-color: RED");
            main.m3Field.setStyle("-fx-background-color: RED");
            errormessage.display("Size of the package must not exceed 37.5 cm x 36 cm x 61 cm");
        }

        if (Double.parseDouble(meret1) <= 0) {
            goodOrNot = false;
            main.m1Field.setStyle("-fx-background-color: RED");
            errormessage.display("Value cannot be less than or equal to 0");
        }
        if (Double.parseDouble(meret2) <= 0) {
            goodOrNot = false;
            main.m2Field.setStyle("-fx-background-color: RED");
            errormessage.display("Value cannot be less than or equal to 0");
        }
        if (Double.parseDouble(meret3) <= 0) {
            goodOrNot = false;
            main.m3Field.setStyle("-fx-background-color: RED");
            errormessage.display("Value cannot be less than or equal to 0");
        }
        if (Double.parseDouble(tomeg) <= 0) {
            goodOrNot = false;
            main.weightField.setStyle("-fx-background-color: RED");
            errormessage.display("Value cannot be less than or equal to 0");
        } else if (Double.parseDouble(tomeg) > 20) {
            goodOrNot = false;
            main.weightField.setStyle("-fx-background-color: RED");
            errormessage.display("Weight of the package must not exceed 20 kg");
        }
        if (Integer.parseInt(tav) <= 0) {
            goodOrNot = false;
            main.distanceField.setStyle("-fx-background-color: RED");
            errormessage.display("Value cannot be less than or equal to 0");
        }
        return goodOrNot;
    }


}

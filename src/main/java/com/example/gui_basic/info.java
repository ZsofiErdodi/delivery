package com.example.gui_basic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class info {

    public static void display() {
        VBox vbox = new VBox();
        vbox.setPrefWidth(300);
        Text text = new Text();
        Button close = new Button("Close");
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(40);
        Stage infoWindow = new Stage();
        infoWindow.setTitle("Info");

        text.setText("Pricing:\n\n" +
                "Base price depending on delivery distance :\n" +
                "0 <  t < 5 km             500 Ft\n" +
                "5 ≤ t < 100 km          750 Ft\n" +
                "100 km ≤ t                1000 Ft\n\n" +
                "Size of the package:\n" +
                "11,5 cm x 36 cm x 61 cm (S)       base price\n" +
                "19,5 cm x 36 cm x 61 cm (M)     +10%\n" +
                "37,5 cm x 36 cm x 61 cm (L)       +20!\n\n" +
                "Weight of the package:\n" +
                "0 < m ≤ 1 kg         base price/price determined by size\n" +
                "1 < m ≤ 3 kg         +10%\n" +
                "3 < m ≤ 10 kg       +20%\n" +
                "10 < m ≤ 20 kg     +30%\n\n" +
                "Number of packages:\n" +
                "1 pc                       base price/price determined by size/weight\n" +
                "2 ≤ x < 5 pcs         -15%\n" +
                "5 pcs ≤ x                -25%\n\n\n" +
                "The transport distance can be given in km rounded to the nearest whole number,\n" +
                "the volume must be expressed in cm to one decimal place,\n" +
                "and the weight in kg to 1 decimal place."
        );

        vbox.getChildren().addAll(text, close);
        Scene infoScene = new Scene(vbox);
        infoWindow.setScene(infoScene);
        infoWindow.show();

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                infoWindow.close();
            }
        });
        }


    }


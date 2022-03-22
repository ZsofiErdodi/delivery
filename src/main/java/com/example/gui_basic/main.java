package com.example.gui_basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    VBox vbox = new VBox();
    HBox line1 = new HBox();
    static Label noField = new Label("1");
    Button infoButton = new Button("Info");
    HBox line2 = new HBox();
    HBox line3 = new HBox();
    static TextField m1Field = new TextField();
    static TextField m2Field = new TextField();
    static TextField m3Field = new TextField();
    HBox line4 = new HBox();
    static TextField weightField = new TextField();
    static TextField distanceField = new TextField();
    HBox line5 = new HBox();
    static Label sum = new Label("0");
    Button clear = new Button("Clear fields");
    HBox line6 = new HBox();
    Button addButton = new Button("Add one more package");
    Button finishedButton = new Button("Finished");
    Button newButton = new Button("New delivery");
    Button closeButton = new Button("Close");
    Scene scene = new Scene(vbox);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Package delivery fee calculation");
        primaryStage.setScene(scene);
        noField.setMaxWidth(30);
        m1Field.setMaxWidth(50);
        m2Field.setMaxWidth(50);
        m3Field.setMaxWidth(50);
        weightField.setMaxWidth(50);
        distanceField.setMaxWidth(50);
        sum.setPrefWidth(70);
        line1.setSpacing(10);
        line3.setSpacing(10);
        line4.setSpacing(10);
        line5.setSpacing(10);
        line6.setSpacing(30);
        VBox.setMargin(line1, new Insets(20, 20, 20, 20));         // top, right, bottom, left
        VBox.setMargin(line2, new Insets(0, 20, 5, 20));
        VBox.setMargin(line3, new Insets(0, 20, 25, 20));
        VBox.setMargin(line4, new Insets(0, 20, 40, 20));
        VBox.setMargin(line5, new Insets(0, 20, 50, 20));
        VBox.setMargin(line6, new Insets(0, 20, 20, 20));
        HBox.setMargin(infoButton, new Insets(0,0,0,205));
        HBox.setMargin(weightField, new Insets(0,30,0,0));
        line6.setAlignment(Pos.CENTER);
        noField.setStyle("-fx-background-color: transparent");
        sum.setStyle("-fx-background-color: transparent");

        line1.getChildren().addAll(new Label("No. of package(s): "), noField, infoButton);
        line2.getChildren().add(new Label("Size of package (cm): "));
        line3.getChildren().addAll(m1Field, m2Field, m3Field);
        line4.getChildren().addAll(new Label("Weight of package (kg): "), weightField, new Label("Distance (km): "), distanceField);
        line5.getChildren().addAll(new Label("Subtotal (Ft): "), sum, addButton, finishedButton);
        line6.getChildren().addAll(clear, newButton, closeButton);
        vbox.getChildren().addAll(line1, line2, line3, line4, line5, line6);

        primaryStage.close();
        primaryStage.setOnCloseRequest((WindowEvent we) -> Platform.exit());
        primaryStage.show();

        infoButton.setOnAction(event);
        addButton.setOnAction(event);
        finishedButton.setOnAction(event);
        clear.setOnAction(event);
        newButton.setOnAction(event);
        closeButton.setOnAction(event);
    }

    EventHandler<ActionEvent> event = actionEvent -> {
        // Close button
        if (actionEvent.getSource() == closeButton) {
            Platform.exit();
            // Clear button
        } else if (actionEvent.getSource() == clear) {
            util.reset();
            // Add one more package button
        } else if (actionEvent.getSource() == addButton) {
            boolean kitoltes = util.inputCheck(main.distanceField.getText(), main.m1Field.getText(), main.m2Field.getText(), main.m3Field.getText(), main.weightField.getText());
            if (kitoltes) {
                util.calculate(Integer.parseInt(distanceField.getText()), Double.parseDouble(m1Field.getText()), Double.parseDouble(m2Field.getText()), Double.parseDouble(m3Field.getText()), Double.parseDouble(weightField.getText()));
                util.reset();
                noField.setText(util.noOfPackages.toString());
                sum.setText(Double.toString(util.sum));
            }
        }
        // Finished button
        else if (actionEvent.getSource() == finishedButton) {
            boolean kitoltes = util.inputCheck(main.distanceField.getText(), main.m1Field.getText(), main.m2Field.getText(), main.m3Field.getText(), main.weightField.getText());
            if (kitoltes) {
                util.calculate(Integer.parseInt(distanceField.getText()), Double.parseDouble(m1Field.getText()), Double.parseDouble(m2Field.getText()), Double.parseDouble(m3Field.getText()), Double.parseDouble(weightField.getText()));
                Integer db = util.noOfPackages - 1;
                results.display(db.toString(), Double.toString(util.sum));
            }
        }
        // New delivery button
        else if (actionEvent.getSource() == newButton) {
            util.clear();
            // Info button
        } else if (actionEvent.getSource() == infoButton) {
            info.display();
        }
    };

}

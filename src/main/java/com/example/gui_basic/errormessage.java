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

public class errormessage {
    public static void display(String error) {
        VBox vbox = new VBox();
        vbox.setPrefWidth(300);
        Text text = new Text();
        Button close = new Button("OK");
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(30);
        Stage errorWindow = new Stage();
        errorWindow.setTitle("Error");

        text.setText("\nError entering data: \n\n" + error);

        vbox.getChildren().addAll(text, close);
        Scene errorScene = new Scene(vbox);
        errorWindow.setScene(errorScene);
        errorWindow.show();

        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                errorWindow.close();
            }
        });
    }
}

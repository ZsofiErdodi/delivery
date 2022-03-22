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

public class results {

    public static void display(String noOfPackages, String sum) {
        Integer number = Integer.parseInt(noOfPackages);
        String pieces;
        VBox vbox = new VBox();
        vbox.setPrefWidth(300);
        Text text = new Text();
        Button close = new Button("OK");
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.setSpacing(30);
        Stage resultWindow = new Stage();
        resultWindow.setTitle("Delivery fee");

        if (number == 1) { pieces = " piece"; }
        else { pieces = " pieces"; }

        text.setText("\nNumber of packages sent:    " + noOfPackages + pieces +
                "\n\n Total delivery fee:                 " + sum + " Ft");

        vbox.getChildren().addAll(text, close);
        Scene resultScene = new Scene(vbox);
        resultWindow.setScene(resultScene);
        resultWindow.show();

        resultWindow.setOnCloseRequest(evt -> {
            evt.consume();
            resultWindow.close();
            util.clear();
        });
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resultWindow.close();
                util.clear();
            }
        });
    }
}

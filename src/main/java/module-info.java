module com.example.gui_basic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens com.example.gui_basic to javafx.fxml;
    exports com.example.gui_basic;
}
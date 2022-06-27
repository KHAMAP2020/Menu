module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.menu to javafx.fxml;
    exports com.example.menu;
    exports controller;
    opens controller to javafx.fxml;
}
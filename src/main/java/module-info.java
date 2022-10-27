module com.vicnetto.javafx2048 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vicnetto.javafx2048 to javafx.fxml;
    exports com.vicnetto.javafx2048;
}
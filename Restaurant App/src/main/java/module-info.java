module com.team15 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com to javafx.fxml;
    exports com;
}
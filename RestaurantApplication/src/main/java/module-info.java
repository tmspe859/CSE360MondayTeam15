module com.team15.restaurantapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.team15.restaurantapplication to javafx.fxml;
    exports com.team15.restaurantapplication;
    exports com.team15.restaurantapplication.controllers;
    opens com.team15.restaurantapplication.controllers to javafx.fxml;
}
module com.restaurantapp {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.desktop;

        opens com.restaurantapp.controllers to javafx.fxml;
        exports com.restaurantapp;
        }
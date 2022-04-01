module com.team15.restaurantapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.team15.restaurantapplication to javafx.fxml;
    exports com.team15.restaurantapplication;
}
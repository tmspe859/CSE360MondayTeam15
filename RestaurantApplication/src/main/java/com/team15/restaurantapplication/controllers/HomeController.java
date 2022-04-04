package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button checkoutButton; // Possibly unneeded fx:id

    @FXML
    private Button homeButton; // Possibly unneeded fx:id

    @FXML
    private Button menuButton; // Possibly unneeded fx:id

    @FXML
    private Button profileButton; // Possibly unneeded fx:id

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        if (!UserSession.isManager()) {
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            Order currentOrder = currentUser.getCurrentOrder();
            RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", currentOrder); // Change scene
        }
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home", null); // Change scene
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null); // Change scene
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeSceneToProfile("profile.fxml","RestaurantApp - Profile", null);
    }
}

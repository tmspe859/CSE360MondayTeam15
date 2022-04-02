package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProfileController {

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField username;

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", null); // Change scene
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
        RestaurantApplication.changeScene("profile.fxml","RestaurantApp - Profile", null); // Change scene
    }

    @FXML
    void saveClicked(ActionEvent event) {
        // UPDATE ALL USER INFORMATION
    }

}

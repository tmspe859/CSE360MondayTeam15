package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class checkoutController {

    @FXML
    private TextField costText;

    @FXML
    private TextField couponText;

    @FXML
    private Button purchaseButton;

    @FXML
    private Button submitCouponButton;

    @FXML
    private TextField taxText;

    @FXML
    private TextField totalText;

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout"); // Change scene
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home"); // Change scene
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu"); // Change scene
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        if (UserSession.getCurrentUser() != null) {// If already logged in
            RestaurantApplication.changeScene("profile.fxml","RestaurantApp - Profile"); // Change scene
        } else { // Otherwise
            RestaurantApplication.changeScene("login.fxml", "RestaurantApp - Home");  // Display login page
        }
    }

    @FXML
    void purchaseClicked(ActionEvent event) {

    }

    @FXML
    void submitCouponClicked(ActionEvent event) {

    }

}

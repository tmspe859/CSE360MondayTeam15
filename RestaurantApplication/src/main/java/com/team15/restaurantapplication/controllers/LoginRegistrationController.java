package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginRegistrationController {

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private TextField confirm_password;

    @FXML
    private TextField email;


    @FXML
    void checkoutClicked(ActionEvent event) {

    }

    @FXML
    void forgotPasswordClicked(ActionEvent event) {

    }

    @FXML
    void homeClicked(ActionEvent event) {

    }

    @FXML
    void loginClicked(ActionEvent event) {

    }

    @FXML
    void menuClicked(ActionEvent event) {

    }

    @FXML
    void profileClicked(ActionEvent event) {

    }

    @FXML
    void registerClicked(ActionEvent event) {

    }

    @FXML
    void alreadyHaveAccountClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("login.fxml","RestaurantApp - Login");
    }

    @FXML
    void registerFromLoginClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("register.fxml","RestaurantApp - Register");
    }

}

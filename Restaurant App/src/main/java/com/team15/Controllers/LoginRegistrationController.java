package com;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginRegistrationController {

    @FXML
    private TextField confirm_password;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

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
    void registerFromLoginClicked(ActionEvent event) throws IOException {
        // Application.changeScene("register.fxml", "Register!", 1000, 800);
    }

    @FXML
    void alreadyHaveAccountClicked(ActionEvent event)  throws IOException {
        // Application.changeScene("login.fxml", "Login!", 1000, 800);
    }

}

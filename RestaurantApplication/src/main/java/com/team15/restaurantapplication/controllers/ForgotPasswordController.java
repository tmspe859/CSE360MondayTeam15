package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField password;

    @FXML
    private Button submitButton;

    @FXML
    private TextField username;

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void submitClicked(ActionEvent event) {
        System.out.println("\"" + username.getText() + "\"");
    }

}

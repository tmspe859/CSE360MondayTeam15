package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    private Text noUsernameErrorMsg;

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void submitClicked(ActionEvent event) { // Need to search for
        noUsernameErrorMsg.setVisible(false);
        if (!username.getText().isEmpty()) { // If a username was input
            // Search for the user
            // If they exist, update password
            // Otherwise, output error
        } else { // If there was no username input
            System.out.println("\"" + username.getText() + "\"");
            noUsernameErrorMsg.setVisible(true);
        }
    }

}

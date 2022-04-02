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
    private Button submitButton; // Possibly unneeded fx:id

    @FXML
    private TextField username;

    @FXML
    private Text noUsernameErrorMsg;

    @FXML
    // Closes this popUp window when clicked
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    @FXML
    // When clicked it will update the password for the given user or output an error
    void submitClicked(ActionEvent event) { // Need to search for

        // Clear any previously displayed errors
        noUsernameErrorMsg.setVisible(false);

        if (!username.getText().isEmpty()) { // If any username was input
            // SEARCH FOR THE USER IN THE DATABASE
            // IF THEY EXIST, UPDATE THEIR PASSWORD
            // OTHERWISE, OUTPUT ERROR
        } else { // If there was no username input
            noUsernameErrorMsg.setVisible(true); // Display error message
        }
    }

}

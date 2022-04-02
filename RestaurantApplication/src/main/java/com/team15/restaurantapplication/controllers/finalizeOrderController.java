package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class finalizeOrderController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardCode;

    @FXML
    private TextField cardDate;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField deliveryName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button placeOrderButton;

    @FXML
    private CheckBox rememberCheckbox;

    @FXML
        // Closes this popUp window when clicked
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    @FXML
    void placeOrderClicked(ActionEvent event) {
        // READ IN TEXT FIELDS TO BE STORED IN PROPER LOCATIONS
        // MOVE ACTIVE ORDER TO ORDER HISTORY
        // STORE USER PAYMENT INFO IF APPROPRIATE
        // CLEAR ACTIVE ORDER
    }

}

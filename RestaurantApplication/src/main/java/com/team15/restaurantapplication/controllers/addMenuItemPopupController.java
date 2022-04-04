package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addMenuItemPopupController {

    @FXML
    private CheckBox calorieCheckbox;

    @FXML
    private TextField description;

    @FXML
    private TextField imagePath;

    @FXML
    private TextField ingredients;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private CheckBox spicyCheckbox;

    @FXML
    private CheckBox veganCheckbox;

    @FXML
    private CheckBox vegetarianCheckbox;

    @FXML
    private Button cancelButton;

    @FXML
    void addClicked(ActionEvent event) {
        // VERIFY FOOD ITEM NAME DOESN'T ALREADY EXIST
        // CREATE NEW DATABASE ENTRY FOR FOOD ITEM USING DATA FROM TEXTFIELDS
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

}

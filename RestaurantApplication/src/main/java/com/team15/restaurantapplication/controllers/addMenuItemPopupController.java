package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
    void addClicked(ActionEvent event) {

    }

    @FXML
    void cancelClicked(ActionEvent event) {

    }

}

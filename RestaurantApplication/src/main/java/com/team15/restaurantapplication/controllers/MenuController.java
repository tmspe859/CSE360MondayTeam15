package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MenuController {

    @FXML
    private CheckBox calorieCheckbox;

    @FXML
    private Button checkoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField searchText;

    @FXML
    private CheckBox spicyCheckbox;

    @FXML
    private CheckBox veganCheckbox;

    @FXML
    private CheckBox vegetarianCheckbox;

    @FXML
    void checkoutClicked(ActionEvent event) {

    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home");
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu");
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("login.fxml","RestaurantApp - Home");  // NEEDS LOGIC FOR ALREADY LOGGED IN INDIVIDUALS
    }

    @FXML
    void refreshClicked(ActionEvent event) {

    }

}

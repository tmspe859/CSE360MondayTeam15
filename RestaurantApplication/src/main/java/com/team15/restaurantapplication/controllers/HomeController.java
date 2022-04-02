package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button checkoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button profileButton;

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
}

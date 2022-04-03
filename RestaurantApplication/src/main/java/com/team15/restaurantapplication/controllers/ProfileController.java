package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProfileController {

    @FXML
    private AnchorPane currentOrderPane;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField placeInQueue;

    @FXML
    private TextField totalCost;

    @FXML
    private TextField username;

    @FXML
    private TextField waitTime;

    @FXML
    public void initialize(){
        currentOrderPane.setMinHeight(120 + (5 * 120));
    }

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        Customer currentUser = (Customer) UserSession.getCurrentUser();
        Order currentOrder = currentUser.getCurrentOrder();
        RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", currentOrder); // Change scene
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home", null); // Change scene
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null); // Change scene
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeSceneToProfile("profile.fxml","RestaurantApp - Profile", null);
    }

    @FXML
    void saveClicked(ActionEvent event) {
        // UPDATE ALL USER INFORMATION
    }

}

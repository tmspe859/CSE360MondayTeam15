package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuItemController {
    
    @FXML
    private Text name;

    @FXML
    private Text description;

    @FXML
    private Text price;
    
    @FXML
    private ImageView image;

    @FXML
    private Button addToCart;

    @FXML
    void addToCartClicked(ActionEvent event) { // Add associated item to the pending order
        // Check if the item already exists in the order
        // If it does, increase the quantity to be ordered by 1
        // If it doesn't, add the item to the order with quantity set to 1
    }

}

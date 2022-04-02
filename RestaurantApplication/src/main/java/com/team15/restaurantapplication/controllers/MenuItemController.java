package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.MenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MenuItemController {
    
    @FXML
    private Label name;

    @FXML
    private Text description;

    @FXML
    private Label price;
    
    @FXML
    private ImageView image;

    @FXML
    private Button addToCartBtn;

    private MenuItem item;

    public void setData(MenuItem item) {
        this.item = item;
        name.setText(item.getName());
        price.setText("$" + item.getPrice());
        //Image img = new Image(item.getImgPath());
        
        //image.setImage(img);


    }

    @FXML
    void addToCartClicked(ActionEvent event) { // Add associated item to the pending order
        // Check if the item already exists in the order
        // If it does, increase the quantity to be ordered by 1
        // If it doesn't, add the item to the order with quantity set to 1
    }

}

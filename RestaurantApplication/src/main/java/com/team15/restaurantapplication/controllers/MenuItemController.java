package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.UserSession;

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
    private Label description;

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
        description.setText(item.getDesc());
        Image img = new Image(item.getImgPath());
        
        image.setImage(img);


    }

    @FXML
    void addToCartClicked(ActionEvent event) { 
        // Add associated item to the pending order
        // CHECK IF THE ITEM ALREADY EXISTS IN THE ORDER
        // IF IT DOES, INCREASE THE QUANTITY TO BE ORDERED BY 1
        // IF IT DOESN'T, ADD THE ITEM TO THE ORDER WITH QUANTITY SET TO 1

        //Check if user is not a manager (returns false)
        if(!UserSession.getCurrentUserType()){
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            currentUser.getCurrentOrder().addItem(this.item);
        }
        
    }

}

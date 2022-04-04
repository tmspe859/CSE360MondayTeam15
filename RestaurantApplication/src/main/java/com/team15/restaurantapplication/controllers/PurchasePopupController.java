package com.team15.restaurantapplication.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import com.team15.restaurantapplication.RestaurantApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class PurchasePopupController implements Initializable {

    @FXML
    private Text message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        message.setText("Processing Purchase Request...");
        Timer timer = new Timer();
        timer.schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    message.setText("Purchase Complete.");
                    timer.cancel();
                }
            }, 
            2500 
        );
    }



}

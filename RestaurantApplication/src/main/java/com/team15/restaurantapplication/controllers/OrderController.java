package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.classes.Order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderController {

    @FXML
    private Label cost;

    @FXML
    private Label date;

    @FXML
    private Label items;

    private Order order;

    public void setData(Order order) {

        this.order = order;
        String totalCost = String.format("$%,.2f", order.getTotalCost());
        cost.setText(totalCost);
        date.setText(order.getDate());

        items.setText(order.getItemString());

    }

}
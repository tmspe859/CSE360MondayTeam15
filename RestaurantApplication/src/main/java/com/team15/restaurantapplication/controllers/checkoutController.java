package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.models.CouponModel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class checkoutController extends Controller {

    @FXML
    private TextField costText;

    @FXML
    private TextField couponText;

    @FXML
    private Button finalizeButton;

    @FXML
    private Button submitCouponButton;

    @FXML
    private TextField discountText;

    @FXML
    private TextField totalText;

    @FXML
    private GridPane cartGrid;

    private Order currentOrder;
    
    @Override
    public void setProps(Object props){
        
        this.currentOrder = (Order) props;

        if(this.currentOrder != null) {
            Double totalCost = this.currentOrder.getTotalCost();
            Double discount = 0.0;

            String costString = String.format("$%,.2f", totalCost);
            String discountString = "% " + String.format("%,.2f", discount);
            String totalString = String.format("$%,.2f", totalCost);

            costText.setText(costString);
            discountText.setText(discountString);
            totalText.setText(totalString);

            initializeOrders(this.currentOrder.getItems());
        }

    }

    public void initializeOrders(ArrayList<MenuItem> cartItems) {
        
        new Thread(() -> {
            Platform.runLater(() -> {

                int column = 0;
                int row = 1;
                try {
                
                    for(int i=0; i < cartItems.size(); i++){
                        FXMLLoader fxmlloader = new FXMLLoader();
                        fxmlloader.setLocation(RestaurantApplication.class.getResource("itemcheckout.fxml"));
        
                        Pane menuItem = fxmlloader.load();
                        
                        ItemPageController itemController = fxmlloader.getController();

                        itemController.setProps(cartItems.get(i));
                    
                        if(column == 2){
                            column = 0;
                            row++;
                        }
        
                        cartGrid.add(menuItem, column++, row);
                        GridPane.setMargin(menuItem, new Insets(10));
        
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }).start();
        
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
    void finalizeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.popUp("finalizeOrder.fxml","RestaurantApp - Finalize Order", currentOrder);
    }

    @FXML
    void submitCouponClicked(ActionEvent event) {
        String code = couponText.getText();

        int id = Integer.parseInt(code.split("-")[0]);

        String title = code.split("-")[1];

        double amount = CouponModel.getCouponAmount(id, title);

        discountText.setText("% " + amount);

        double discount = (amount/100) * currentOrder.getTotalCost();

        double total = this.currentOrder.getTotalCost() - discount;

        totalText.setText(String.format("$%,.2f", total));
    }

}

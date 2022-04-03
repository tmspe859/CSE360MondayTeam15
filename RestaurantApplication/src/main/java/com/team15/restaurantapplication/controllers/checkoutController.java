package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.UserSession;
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
    private TextField taxText;

    @FXML
    private TextField totalText;

    @FXML
    private GridPane cartGrid;

    private Order currentOrder;
    
    @Override
    public void setProps(Object props){
        
        this.currentOrder = (Order) props;

        if(this.currentOrder != null){
            costText.setText("$ " + this.currentOrder.getTotalCost());
            totalText.setText("$ " + this.currentOrder.getTotalCost());

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

    }

}

package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.*;

import com.team15.restaurantapplication.models.DeliveryInfoModel;
import com.team15.restaurantapplication.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProfileController {

    @FXML
    private TextArea coupons;

    @FXML
    private TextArea coupons1;

    @FXML
    private TextArea currentOrderItems;

    @FXML
    private AnchorPane currentOrderPane;

    @FXML
    private AnchorPane currentOrderPane1;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField joinedDate;

    @FXML
    private TextField lastName;

    @FXML
    private TextField numberOfOrders;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField placeInQueue;

    @FXML
    private TextArea previousOrderList;

    @FXML
    private TextField rewardsPoints;

    @FXML
    private TextField totalCost;

    @FXML
    private TextField username;

    @FXML
    private TextField waitTime;

    @FXML
    private Text message;

    @FXML
    void initialize() {
        User currentUser = UserSession.getCurrentUser();

        firstName.setText(currentUser.getFirstName());
        lastName.setText(currentUser.getLastName());
        username.setText(currentUser.getUserName());
        emailAddress.setText(currentUser.getEmail());
        password.setText(currentUser.getPassword());
        joinedDate.setText(currentUser.getJoinedDate());

        if (!currentUser.isManager()) {

            Customer customer = (Customer) currentUser;
            DeliveryInfo deliveryInfo = customer.getDeliveryInfo();
            numberOfOrders.setText(customer.getNumOfOrders().toString());
            rewardsPoints.setText(customer.getRewardsPoints().toString());
            if(deliveryInfo != null){

                deliveryAddress.setText(deliveryInfo.getAddress());
                phoneNumber.setText(deliveryInfo.getPhone());
            }

        }

    }

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        Customer currentUser = (Customer) UserSession.getCurrentUser();
        Order currentOrder = currentUser.getCurrentOrder();
        RestaurantApplication.changeScene("checkout.fxml", "RestaurantApp - Checkout", currentOrder); // Change scene
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml", "RestaurantApp - Home", null); // Change scene
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml", "RestaurantApp - Menu", null); // Change scene
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeSceneToProfile("profile.fxml", "RestaurantApp - Profile", null);
    }

    @FXML
    void saveClicked(ActionEvent event) {
        // UPDATE ALL USER INFORMATION

        boolean isValid = validateForm();

        if(!isValid)
            return;

        int userID = UserSession.getCurrentUser().getAccountID();

        UserModel.updateUser(
            firstName.getText(),
            lastName.getText(),
            username.getText(),
            emailAddress.getText(),
            password.getText(),
            userID
        );

        Customer customer = (Customer) UserSession.getCurrentUser();
        String recipient = firstName.getText() + " " + lastName.getText();
        DeliveryInfo newInfo = new DeliveryInfo(recipient, deliveryAddress.getText(), phoneNumber.getText());
        if(customer.getDeliveryInfo() != null){
            DeliveryInfoModel.updateDeliveryInfo(newInfo, userID);
        } else {
            DeliveryInfoModel.addDeliveryInfo(newInfo, userID);
        }
        

    }

    private boolean validateForm() {
        
        if(firstName.getText().isEmpty()){
            firstName.setStyle("-fx-text-box-border: green;");
            message.setText("Please enter your First Name");
            return false;
        }
        if(lastName.getText().isEmpty()){
            lastName.setStyle("-fx-text-box-border: green;");
            message.setText("Please enter your Last Name");
            return false;
        }
        if(username.getText().isEmpty()){
            username.setStyle("-fx-text-box-border: green;");
            message.setText("Please enter your username");
            return false;
        }
        if(emailAddress.getText().isEmpty()){
            emailAddress.setStyle("-fx-text-box-border: green;");
            message.setText("Please enter your Email Address");
            return false;
        }
        if(password.getText().isEmpty()){
            password.setStyle("-fx-text-box-border: green;");
            message.setText("Please enter your Password");
            return false;
        }

        return true;
    }

}

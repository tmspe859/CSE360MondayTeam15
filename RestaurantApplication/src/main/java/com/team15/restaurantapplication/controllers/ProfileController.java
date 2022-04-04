package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.*;

import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;
import com.team15.restaurantapplication.models.CouponModel;
import com.team15.restaurantapplication.models.DeliveryInfoModel;
import com.team15.restaurantapplication.models.OrderModel;
import com.team15.restaurantapplication.models.UserModel;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

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
    private GridPane previousOrderList;

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
    private TextField couponName;

    @FXML
    private Text couponText1;

    @FXML
    private Text couponText2;

    @FXML
    private Text couponText3;

    @FXML
    private Text couponText4;

    @FXML
    private TextField discount;

    @FXML
    private TextArea earnedRewards;

    @FXML
    private TextField recipentUsername;

    @FXML
    private Button sendButton;

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

            initializePreviousOrders(customer.getAccountID());

        } else {
            couponText1.setVisible(true);
            couponText2.setVisible(true);
            couponText3.setVisible(true);
            couponText4.setVisible(true);
            recipentUsername.setVisible(true);
            couponName.setVisible(true);
            discount.setVisible(true);
            sendButton.setVisible(true);
        }

    }

    private void initializePreviousOrders(int customerId){

        ArrayList<Order> previousOrders = OrderModel.getOrdersByCustomer(customerId);
        int length = previousOrders.size();
        if(length > 0){
            totalCost.setText(String.format("$%,.2f", previousOrders.get(length-1).getTotalCost()));
            String[] items = previousOrders.get(length-1).getItemString().split(",");
            for(String item : items){
                currentOrderItems.appendText(item + "\n");
            }
            numberOfOrders.setText(length + " ");
        }
        new Thread(() -> {
            
            Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            int i = 0;
            try {
                
                for (Order order : previousOrders) {
                    if(i==length-1){
                        break;
                    }
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(RestaurantApplication.class.getResource("order.fxml"));
    
                    Pane orderView = fxmlloader.load();
                    
                    OrderController itemController = fxmlloader.getController();

                    itemController.setData(order);
                
                    if(column == 1){
                        column = 0;
                        row++;
                    }
    
                    previousOrderList.add(orderView, column++, row);
                    GridPane.setMargin(orderView, new Insets(10));
                    i++;
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
        if (!UserSession.isManager()) {
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            Order currentOrder = currentUser.getCurrentOrder();
            RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", currentOrder); // Change scene
        }
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

        if(!UserSession.isManager()){
            Customer customer = (Customer) UserSession.getCurrentUser();
            String recipient = firstName.getText() + " " + lastName.getText();
            DeliveryInfo newInfo = new DeliveryInfo(recipient, deliveryAddress.getText(), phoneNumber.getText());
            if(customer.getDeliveryInfo() != null){
                DeliveryInfoModel.updateDeliveryInfo(newInfo, userID);
            } else {
                DeliveryInfoModel.addDeliveryInfo(newInfo, userID);
            }
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

    @FXML
    void sendClicked(ActionEvent event) throws emailExistsException, usernameTakenException {
        User user = UserModel.getUser(recipentUsername.getText());
        CouponModel.createCoupon(couponName.getText(), Double.parseDouble(discount.getText()), user.getAccountID());
        couponName.setText("");
        discount.setText("");
        recipentUsername.setText("");
    }

}

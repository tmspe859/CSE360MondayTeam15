package com.team15.restaurantapplication.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.team15.restaurantapplication.classes.CardInfo;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.DeliveryInfo;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;
import com.team15.restaurantapplication.models.CardInfoModel;
import com.team15.restaurantapplication.models.DeliveryInfoModel;
import com.team15.restaurantapplication.models.OrderModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class finalizeOrderController extends Controller implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardCode;

    @FXML
    private TextField cardDate;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private TextField deliveryName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button placeOrderButton;

    @FXML
    private CheckBox rememberCheckbox;

    private Order currentOrder;

    @Override
    public void setProps(Object props) {
        this.currentOrder = (Order) props;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        CardInfo cardInfo = UserSession.getCardInfo();
        DeliveryInfo deliveryInfo = UserSession.getDeliveryInfo();
        
        if(cardInfo != null){
            cardNumber.setText(cardInfo.getCardNumber());
            cardName.setText(cardInfo.getCardName());
            cardDate.setText(cardInfo.getExpiration());
            cardCode.setText(cardInfo.getSecurityCode());
        }

        if(deliveryInfo != null){
            deliveryAddress.setText(deliveryInfo.getAddress());
            deliveryName.setText(deliveryInfo.getRecipient());
            phoneNumber.setText(deliveryInfo.getPhone());
        }
        
        
    }


    @FXML
        // Closes this popUp window when clicked
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    @FXML
    void placeOrderClicked(ActionEvent event) throws emailExistsException, usernameTakenException {
        // READ IN TEXT FIELDS TO BE STORED IN PROPER LOCATIONS
        // MOVE ACTIVE ORDER TO ORDER HISTORY
        // STORE USER PAYMENT INFO IF APPROPRIATE
        // CLEAR ACTIVE ORDER

        //[TODO] validate payment and delivery info

        if(rememberCheckbox.isSelected()){ 
            System.out.println("Remember me"); 
            updateCardInfo();
            updateDeliveryInfo();
        }

        //Save order data to database
        String itemString = currentOrder.getItemString();
        double totalCost = currentOrder.getTotalCost();
        int customerId = UserSession.getCurrentUser().getAccountID();
        OrderModel.createOrder(itemString, totalCost, customerId);
    }

    private void updateDeliveryInfo() {
        if(!UserSession.isManager()){
            Customer customer = (Customer) UserSession.getCurrentUser();
            String recipient = deliveryName.getText();
            DeliveryInfo newInfo = new DeliveryInfo(recipient, deliveryAddress.getText(), phoneNumber.getText());
            if(customer.getDeliveryInfo() != null){
                DeliveryInfoModel.updateDeliveryInfo(newInfo, customer.getAccountID());
            } else {
                DeliveryInfoModel.addDeliveryInfo(newInfo, customer.getAccountID());
            }
        }
    }


    private void updateCardInfo(){
        if(!UserSession.isManager()){
            Customer customer = (Customer) UserSession.getCurrentUser();

            String number = cardNumber.getText();
            String name = cardName.getText();
            String expiration = cardDate.getText();
            String code = cardCode.getText();

            CardInfo newInfo = new CardInfo(number, name, expiration, code);
            if(customer.getPaymentInfo() != null){
                CardInfoModel.updateCardInfo(newInfo, customer.getAccountID());
            } else {
                CardInfoModel.addCardInfo(newInfo, customer.getAccountID());
            }
        }
    }

}

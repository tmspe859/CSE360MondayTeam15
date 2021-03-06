package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.CardInfo;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.DeliveryInfo;
import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.classes.User;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;
import com.team15.restaurantapplication.models.CardInfoModel;
import com.team15.restaurantapplication.models.DeliveryInfoModel;
import com.team15.restaurantapplication.models.UserModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginRegistrationController {

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private TextField confirm_password;

    @FXML
    private TextField email;

    @FXML
    private Text message;

    @FXML
    private Text noMatchError;


    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        if (!UserSession.isManager()) {
        Customer currentUser = (Customer) UserSession.getCurrentUser();
        Order currentOrder = currentUser.getCurrentOrder();
        RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", currentOrder); // Change scene
        }
    }


    @FXML
    void forgotPasswordClicked(ActionEvent event) throws IOException {
        RestaurantApplication.popUp("forgotPassword.fxml", "RestaurantApp - Reset Password"); // Display pop up
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home", null); // Change scene
    }

    @FXML
    void loginClicked(ActionEvent event) throws IOException {
        User user = UserModel.getUser(username.getText(), password.getText());
        if(user != null){
            System.out.println(user.getFirstName());
            
            //set user session
            if(!user.isManager()){
                Customer customer = (Customer) user;
                DeliveryInfo deliveryInfo = DeliveryInfoModel.getDeliveryInfo(user.getAccountID());
                CardInfo cardInfo = CardInfoModel.getCardInfo(user.getAccountID());
                customer.setDeliveryInfo(deliveryInfo);
                customer.setPaymentInfo(cardInfo);
                UserSession.setCurrentUser(customer);
            } else {
                UserSession.setCurrentUser(user);
            }

            //switch scene
            RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home", null);

        } else {
            //display 'incorrect username or password' message
            message.setFill(Color.RED);
            message.setText("Incorrect username or password");
        }
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
    void registerClicked(ActionEvent event) throws IOException {
        noMatchError.setVisible(false); // Clear any previous error messages
        if (password.getText().equals(confirm_password.getText())) {

            //Create new user in database
            Customer newCustomer;

            try {
                newCustomer = (Customer) UserModel.createUser(firstname.getText(), lastname.getText(),
                        username.getText(), email.getText(), password.getText(), false);

                if (newCustomer != null) {

                    //set user session
                    UserSession.setCurrentUser(newCustomer);

                    //switch scene
                    RestaurantApplication.changeScene("home.fxml", "RestaurantApp - Home", null);

                } else {
                    message.setText("username or email already exist");
                }

            } catch (emailExistsException e) {
                message.setFill(Color.RED);
                message.setText("email already exist");
            } catch (usernameTakenException e) {
                message.setFill(Color.RED);
                message.setText("username is taken");
            }
        } else {
            noMatchError.setVisible(true); // Display error for non-matching passwords
        }
        

    }

    @FXML
    void alreadyHaveAccountClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("login.fxml","RestaurantApp - Login", null); // Change scene
    }

    @FXML
    void registerFromLoginClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("register.fxml","RestaurantApp - Register", null); // Change scene
    }

}

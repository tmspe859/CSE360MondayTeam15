package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.User;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;
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
    void checkoutClicked(ActionEvent event) {

    }

    @FXML
    void forgotPasswordClicked(ActionEvent event) throws IOException {
        RestaurantApplication.popUp("forgotPassword.fxml", "RestaurantApp - Reset Password");
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home");
    }

    @FXML
    void loginClicked(ActionEvent event) throws IOException {
        User user = UserModel.getUser(username.getText(), password.getText());
        if(user != null){
            System.out.println(user.getFirstName());
            //set user session
            UserSession.getInstace(user);

            //switch scene
            RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home");

        } else {
            //display 'incorrect username or password' message
            message.setText("Incorrect username or password");
        }
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu");
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("login.fxml","RestaurantApp - Home");  // NEEDS LOGIC FOR ALREADY LOGGED IN INDIVIDUALS
    }

    @FXML
    void registerClicked(ActionEvent event) throws IOException {

        //Create new user in database
        Customer newCustomer;

        try {
            newCustomer = (Customer) UserModel.createUser(firstname.getText(), lastname.getText(),
                                username.getText(), email.getText(), password.getText(), false);
            
            if(newCustomer != null){

                //set user session
                UserSession.getInstace(newCustomer);
    
                //switch scene
                RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home");
            
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
        
        

    }

    @FXML
    void alreadyHaveAccountClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("login.fxml","RestaurantApp - Login");
    }

    @FXML
    void registerFromLoginClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("register.fxml","RestaurantApp - Register");
    }

}

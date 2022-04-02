package com.team15.restaurantapplication.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.UserSession;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ItemPageController extends Controller implements Initializable {

    @FXML
    private Button addToCart;

    @FXML
    private Label description;

    @FXML
    private ImageView image;

    @FXML
    private GridPane ingredientsList;

    @FXML
    private Label name;

    @FXML
    private Label price;

    private MenuItem item;

    private boolean[] itemIngredients;

    @Override
    public void setProps(Object props){
        
        this.item = (MenuItem) props;

        if(this.item != null){
            name.setText(this.item.getName());
            description.setText(this.item.getDesc());
            price.setText("$" + this.item.getPrice());

            Image img = new Image(item.getImgPath());
            image.setImage(img);

            initializeIngredients(this.item.getIngredients());
        }

    }

    private void initializeIngredients(ArrayList<String> ingredients){

        int i = 0;
        itemIngredients = new boolean[ingredients.size()]; 

        for(String ingredient : ingredients){

            final int j = i; 

            CheckBox check = new CheckBox(ingredient);
            check.setSelected(true);
            //i.isSelected();
            check.selectedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    itemIngredients[j] = newValue;
                }
                
            });

            ingredientsList.add(check, 0, i);

            i++;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void addToCartClicked(ActionEvent event) {
        //Check if user is not a manager (returns false)
        if(!UserSession.getCurrentUserType()){
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            currentUser.getCurrentOrder().addItem(this.item);
        }
    }

    @FXML
    void checkoutClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", null); // Change scene
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
        if (UserSession.getCurrentUser().getAccountID() != null) {// If already logged in
            RestaurantApplication.changeScene("profile.fxml","RestaurantApp - Profile", null); // Change scene
        } else { // Otherwise
            RestaurantApplication.changeScene("login.fxml", "RestaurantApp - Home", null);  // Display login page
        }
    }


}

package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.MenuItem;

import javafx.application.Platform;
import com.team15.restaurantapplication.classes.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private CheckBox calorieCheckbox;

    @FXML
    private Button checkoutButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button menuButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField searchText;

    @FXML
    private CheckBox spicyCheckbox;

    @FXML
    private CheckBox veganCheckbox;

    @FXML
    private CheckBox vegetarianCheckbox;

    @FXML
    private GridPane menu;

    @FXML 
    private ScrollPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        /*
        menu.setMinWidth(Region.USE_COMPUTED_SIZE);
        menu.setPrefWidth(Region.USE_COMPUTED_SIZE);
        menu.setMaxWidth(Region.USE_PREF_SIZE);

        menu.setMinHeight(Region.USE_COMPUTED_SIZE);
        menu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        menu.setMaxHeight(Region.USE_PREF_SIZE);*/


        new Thread(() -> {
            
            Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
            
                for(int i=0; i < 30; i++){
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(RestaurantApplication.class.getResource("menuitem.fxml"));
    
                    Pane menuItem = fxmlloader.load();
                    
                    MenuItemController itemController = fxmlloader.getController();
                    itemController.setData(new MenuItem("Banana", 3.99, "Banana description",
                            null, null, RestaurantApplication.class.getResource("images.jpg").toExternalForm()));
                
                
                    if(column == 5){
                        column = 0;
                        row++;
                    }
    
                    menu.add(menuItem, column++, row);
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
    void checkoutClicked(ActionEvent event) {
        // CHANGE SCENE
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home");
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu");
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        if (UserSession.getCurrentUser() != null) {// If already logged in
            RestaurantApplication.changeScene("profile.fxml","RestaurantApp - Profile"); // Change scene
        } else { // Otherwise
            RestaurantApplication.changeScene("login.fxml", "RestaurantApp - Home");  // Display login page
        }
    }

    @FXML
    void refreshClicked(ActionEvent event) {
        // RELOAD MENU WITH NON-MATCHING ITEMS FILTERED OUT
    }

}

package com.team15.restaurantapplication.controllers;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.Menu;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.Order;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.team15.restaurantapplication.models.MenuItemModel;
import com.team15.restaurantapplication.models.Database;
import java.sql.*;

public class MenuController implements Initializable {

    @FXML
    private Button addItemButton;

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

        if (UserSession.getCurrentUser().isManager()) {
            addItemButton.setVisible(true);
        }

        /*
        menu.setMinWidth(Region.USE_COMPUTED_SIZE);
        menu.setPrefWidth(Region.USE_COMPUTED_SIZE);
        menu.setMaxWidth(Region.USE_PREF_SIZE);

        menu.setMinHeight(Region.USE_COMPUTED_SIZE);
        menu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        menu.setMaxHeight(Region.USE_PREF_SIZE);*/


        Menu fullMenu = Menu.getInstance();

        new Thread(() -> {
            
            Platform.runLater(() -> {
            int column = 0;
            int row = 1;
            try {
                String searchString = searchText.getText();

                ArrayList<Boolean> searchTags = new ArrayList<Boolean>();
                searchTags.add(vegetarianCheckbox.isSelected());
                searchTags.add(veganCheckbox.isSelected());
                searchTags.add(calorieCheckbox.isSelected());
                searchTags.add(spicyCheckbox.isSelected());
                
                ArrayList<MenuItem> loadedMenu = fullMenu.getFilteredItems(searchString, searchTags);
                for (MenuItem item : loadedMenu) {
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(RestaurantApplication.class.getResource("menuitem.fxml"));
    
                    Pane menuItem = fxmlloader.load();
                    
                    MenuItemController itemController = fxmlloader.getController();

                    itemController.setData(item);
                
                
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
    void checkoutClicked(ActionEvent event) throws IOException {
        if (!UserSession.isManager()) {
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            Order currentOrder = currentUser.getCurrentOrder();
            RestaurantApplication.changeScene("checkout.fxml","RestaurantApp - Checkout", currentOrder); // Change scene
        }
    }

    @FXML
    void homeClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("home.fxml","RestaurantApp - Home", null);
    }

    @FXML
    void menuClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null);
    }

    @FXML
    void profileClicked(ActionEvent event) throws IOException {
        RestaurantApplication.changeSceneToProfile("profile.fxml","RestaurantApp - Profile", null);
    }

    @FXML
    void refreshClicked(ActionEvent event) {
        this.menu.getChildren().clear();
        this.initialize(null, null);
    }
    @FXML
    void addItemClicked(ActionEvent event) throws IOException {
        RestaurantApplication.popUp("addMenuItemPopup.fxml","RestaurantApp - Add Menu Item");
    }
}

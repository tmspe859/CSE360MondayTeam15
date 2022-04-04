package com.team15.restaurantapplication.controllers;

import java.io.IOException;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.classes.User;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.classes.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MenuItemController {

    @FXML
    private Pane pane;

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label price;

    @FXML
    private ImageView image;

    @FXML
    private Button addToCartBtn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    private MenuItem item;

    @FXML
    void initialize() {
        if (UserSession.getCurrentUser().isManager()) {
            updateButton.setVisible(true);
            deleteButton.setVisible(true);
            addToCartBtn.setVisible(false);
        }
    }

    public void setData(MenuItem item) {
        this.item = item;
        name.setText(item.getName());
        price.setText("$" + item.getPrice());

        if(description != null){
            description.setWrapText(true);
            description.prefWidthProperty().bind(pane.widthProperty());
            description.setText(item.getDesc());
        }
        
        Image img = new Image(item.getImgPath());
        
        image.setImage(img);


    }

    @FXML
    void addToCartClicked(ActionEvent event) { 
        // Add associated item to the pending order
        // CHECK IF THE ITEM ALREADY EXISTS IN THE ORDER
        // IF IT DOES, INCREASE THE QUANTITY TO BE ORDERED BY 1
        // IF IT DOESN'T, ADD THE ITEM TO THE ORDER WITH QUANTITY SET TO 1

        //Check if user is not a manager (returns false)\
        if(!UserSession.isManager()){
            Customer currentUser = (Customer) UserSession.getCurrentUser();
            currentUser.getCurrentOrder().addItem(this.item);
        }
        
    }

    @FXML 
    void removeFromCart(ActionEvent event){
        System.out.println("Item remove");
    }

    @FXML 
    void itemClicked(MouseEvent event) throws IOException {
        
        RestaurantApplication.changeScene("itemPage.fxml", "RestaurantApp - Home", this.item);
    }

    @FXML
    void onHoverIn(MouseEvent event) {
        pane.setStyle("-fx-background-color:#dae7f3;");
    }

    @FXML
    void onHoverOut(MouseEvent event) {
        pane.setStyle("-fx-background-color:#FFFFFF;");
    }

    @FXML
    void deleteClicked(ActionEvent event) throws IOException {
        // REMOVE ITEM FROM DB
        // REMOVE ITEM FROM SESSION MENU
        String itemName = this.name.getText();
        Menu fullMenu = Menu.getInstance();
        fullMenu.deleteItem(itemName);

        // CHANGE SCENE TO MENU/REFRESH MENU
        RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null); // Change scene
    }

    @FXML
    void updateClicked(ActionEvent event) throws IOException {
        RestaurantApplication.popUp("editMenuItemPopup.fxml","RestaurantApp - Edit Menu Item", this.item);
    }

}

package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.team15.restaurantapplication.classes.Menu;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.RestaurantApplication;

public class addMenuItemPopupController {

    @FXML
    private CheckBox calorieCheckbox;

    @FXML
    private TextField description;

    @FXML
    private TextField imagePath;

    @FXML
    private TextField ingredients;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private CheckBox spicyCheckbox;

    @FXML
    private CheckBox veganCheckbox;

    @FXML
    private CheckBox vegetarianCheckbox;

    @FXML
    private Button cancelButton;

    @FXML
    void addClicked(ActionEvent event) {
        // VERIFY FOOD ITEM NAME DOESN'T ALREADY EXIST
        // CREATE NEW DATABASE ENTRY FOR FOOD ITEM USING DATA FROM TEXTFIELDS

        Menu fullMenu = Menu.getInstance();

        ArrayList<Boolean> selectedTags = new ArrayList<Boolean>();
        selectedTags.add(this.vegetarianCheckbox.isSelected());
        selectedTags.add(this.veganCheckbox.isSelected());
        selectedTags.add(this.calorieCheckbox.isSelected());
        selectedTags.add(this.spicyCheckbox.isSelected());

        Boolean pushed = fullMenu.addItem(
            new MenuItem(
                this.name.getText(), 
                Double.parseDouble(this.price.getText()), 
                this.description.getText(), 
                addMenuItemPopupController.splitIngredientString(this.ingredients.getText()), 
                selectedTags, 
                this.imagePath.getText()
            )
        );

        System.out.println(pushed);
        System.out.println("END ADDITION");
        try {
            RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null); // Change scene
        } catch (IOException e) {
            System.err.println("IOException!");
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    private static ArrayList<String> splitIngredientString(String ingredients) {
        ArrayList<String> result = new ArrayList<String>();
        String[] splitString =  ingredients.split(",\s*");
        for (String ing : splitString) result.add(ing);
        return result;
    }
}

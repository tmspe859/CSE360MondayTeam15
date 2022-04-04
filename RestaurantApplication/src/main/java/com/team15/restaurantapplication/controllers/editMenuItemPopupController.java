package com.team15.restaurantapplication.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.team15.restaurantapplication.RestaurantApplication;
import com.team15.restaurantapplication.classes.Menu;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.models.MenuItemModel;

public class editMenuItemPopupController extends Controller {

    private MenuItem item;

    @FXML
    private CheckBox calorieCheckbox;

    @FXML
    private Button cancelButton;

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


    @Override
    public void setProps(Object props){
        this.item = (MenuItem) props;
        this.name.setText(this.item.getName());
        this.description.setText(this.item.getDesc());
        this.price.setText(this.item.getPrice().toString());
        this.ingredients.setText(editMenuItemPopupController.ingredientsToString(this.item.getIngredients()));
        
        ArrayList<Boolean> tags = this.item.getTags();
        this.vegetarianCheckbox.setSelected(tags.get(0));
        this.veganCheckbox.setSelected(tags.get(1));
        this.calorieCheckbox.setSelected(tags.get(2));
        this.spicyCheckbox.setSelected(tags.get(3));

        this.imagePath.setText(this.item.getImgPath());
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    @FXML
    void editClicked(ActionEvent event) {
        // UPDATE ENTRY BASED ON DATA PASSED IN TEXT FIELDS
        Menu fullMenu = Menu.getInstance();

        StringBuilder selectedTags = new StringBuilder();
        selectedTags.append((this.vegetarianCheckbox.isSelected()) ? "1" : "0");
        selectedTags.append(",");
        selectedTags.append((this.veganCheckbox.isSelected()) ? "1" : "0");
        selectedTags.append(",");
        selectedTags.append((this.calorieCheckbox.isSelected()) ? "1" : "0");
        selectedTags.append(",");
        selectedTags.append((this.spicyCheckbox.isSelected()) ? "1" : "0");

        System.out.println("EDITING ITEM!");

        Boolean pushed = fullMenu.updateItem(
            this.item.getName(),
            new String[]{
                MenuItemModel.nameColumn,
                MenuItemModel.priceColumn,
                MenuItemModel.descColumn,
                MenuItemModel.tagsColumn,
                MenuItemModel.ingredientsColumn,
                MenuItemModel.imgPathColumn
            },
            new Object[]{
                this.name.getText(),
                Double.parseDouble(this.price.getText()),
                this.description.getText(),
                selectedTags.toString(),
                this.ingredients.getText(),
                this.imagePath.getText()
            });

        System.out.println(pushed);
        System.out.println("END EDIT");

        try {
            RestaurantApplication.changeScene("menu.fxml","RestaurantApp - Menu", null); // Change scene
        } catch (IOException e) {
            System.err.println("IOException!");
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow(); // Set stage to this window
        stage.close(); // Close the stage
    }

    private static String ingredientsToString(ArrayList<String> ings) {
        StringBuilder strBuilder = new StringBuilder();
        Boolean firstAddition = true;
        for (String str : ings) {
            if (!firstAddition) strBuilder.append(", ");
            strBuilder.append(str);
            firstAddition = false;
        }
        return strBuilder.toString();
    }

}

package com.team15.restaurantapplication;

import com.team15.restaurantapplication.controllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import com.team15.restaurantapplication.classes.Customer;
import com.team15.restaurantapplication.classes.UserSession;
import com.team15.restaurantapplication.controllers.Controller;
import com.team15.restaurantapplication.models.Database;

public class RestaurantApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource("login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("RestaurantApp - Login");
        primaryStage.setScene(new Scene(root.load()));
        primaryStage.show();
        /*
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
         */
    }

    public static void changeScene(String fxml, String title, Object props) throws IOException {
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource(fxml));
        primaryStage.setResizable(false);
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root.load()));

        if(props != null){
            Controller controller = root.getController();
            controller.setProps(props);
        }

        primaryStage.show();
    }

    public static void popUp(String fxml, String title) throws IOException {
        Stage popUpStage = new Stage();
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource(fxml));
        popUpStage.setResizable(false);
        popUpStage.setTitle(title);
        popUpStage.setScene(new Scene(root.load()));
        popUpStage.show();
    }

    public static void main(String[] args) {
        Connection connection = Database.connect();
        Database.checkTables(connection);
        UserSession.getInstance(new Customer());
        launch(args);
    }

    public static void changeSceneToProfile(String fxml, String title, Object props) throws IOException {
        if (UserSession.getCurrentUser().getAccountID() != null) { // If already logged in
            FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource(fxml));
            primaryStage.setResizable(false);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root.load()));
            
            if (props != null) {
                Controller controller = root.getController();
                controller.setProps(props);
            }

            primaryStage.show();
        } else { // Otherwise
            RestaurantApplication.changeScene("login.fxml", "RestaurantApp - Home", null);  // Display login page
        }

    }
}


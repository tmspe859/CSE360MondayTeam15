package com.team15.restaurantapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import com.team15.restaurantapplication.classes.UserSession;
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

    public static void changeScene(String fxml, String title) throws IOException {
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource(fxml));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root.load()));
        primaryStage.show();
    }

    public static void popUp(String fxml, String title) throws IOException {
        Stage popUpStage = new Stage();
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource(fxml));
        popUpStage.setTitle(title);
        popUpStage.setScene(new Scene(root.load()));
        popUpStage.show();
    }



    public static void main(String[] args) {
        Connection connection = Database.connect();
        Database.checkTables(connection);
        launch(args);
    }
}
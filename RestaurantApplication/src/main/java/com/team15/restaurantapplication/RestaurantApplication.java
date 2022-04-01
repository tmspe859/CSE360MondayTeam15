package com.team15.restaurantapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(RestaurantApplication.class.getResource("login.fxml"));
        stage.setTitle("RestaurantApp - Login");
        stage.setScene(new Scene(root.load()));
        stage.show();
        /*
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
         */
    }

    public static void main(String[] args) {
        launch();
    }
}
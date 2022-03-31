package Restaurant App.src.main.java.com.restaurantapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// import java.sql.Connection;

public class RestaurantApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            primaryStage.setTitle("RestaurantApp - Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        // Connection connection = Database.connect();
        // Database.checkTables();
        // System.out.println(connection);  //debug print
        launch(args);
    }
}

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;

public class RestaurantApp extends Application {
    

    @Override
    public void start(Stage primaryStage) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        Connection connection = Database.connect();
        Database.checkTables();
        System.out.println(connection);  //debug print
        launch(args);
    }
}

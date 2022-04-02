package com.team15.restaurantapplication.models;

import java.sql.*;

import com.team15.restaurantapplication.classes.User;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;
import com.team15.restaurantapplication.classes.Customer;

public class UserModel {

    private static final String tableName = "users";
    private static final String idColumn = "id";
    private static final String firstNameColumn = "firstname";
    private static final String lastNameColumn = "lastname";
    private static final String userNameColumn = "username";
    private static final String emailColumn = "email";
    private static final String passwordColumn = "password";
    private static final String isManagerColumn = "isManager";

    public static void createTable(Connection connection){

        try {
   
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                           "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                           " firstname      CHAR(255)    NOT NULL, " + 
                           " lastname       CHAR(255)    NOT NULL, " + 
                           " username       CHAR(255)   UNIQUE    NOT NULL, " + 
                           " password       CHAR(255)    NOT NULL, " +
                           " email          CHAR(255)   UNIQUE    NOT NULL, " +
                           " isManager      BOOLEAN      NOT NULL )"; 
            stmt.executeUpdate(sql);
            stmt.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

         }

    }

    public static User getUser(String userName, String password){

        User user = null;

        String condition = " (" + userNameColumn + "='" + userName +
            "' OR " + emailColumn + "='" + userName + "') " +
            " AND " + passwordColumn + "='" + password + "'";

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition; 
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.getBoolean(isManagerColumn)){
                //user = new Manager();
            } else {
                user = new Customer(rs.getString(firstNameColumn), rs.getString(lastNameColumn),
                        rs.getString(userNameColumn), rs.getString(passwordColumn),
                        rs.getString(emailColumn),
                        rs.getInt(idColumn));
            }

            rs.close();
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
         }
        return user;

    }

    public static User createUser(String firstName, String lastName, 
            String userName, String email, String password, boolean isManager) throws emailExistsException, usernameTakenException {

        //update database
        int id = (int) CRUDHelper.create(
            tableName,
            new String[]{firstNameColumn, lastNameColumn, 
                userNameColumn, emailColumn, passwordColumn, isManagerColumn},
            new Object[]{firstName, lastName, 
                userName, email, password, isManager},
            new int[]{Types.VARCHAR, Types.VARCHAR, 
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN}
        );

        System.out.println("create user: " + id);

        if(id == -1){
            return null;
        } else {
            //update cache
            return new Customer(firstName, lastName, userName, password, email, id);
        }  

    }

    public static boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username IN (\"" + username + "\")";
        try (Connection conn = Database.connect()) {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (rs.getInt(1) == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }
}

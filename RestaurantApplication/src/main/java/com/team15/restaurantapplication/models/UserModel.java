package com.team15.restaurantapplication.models;

import java.sql.*;
import java.time.LocalDate;

import com.team15.restaurantapplication.classes.Manager;
import com.team15.restaurantapplication.classes.User;
import com.team15.restaurantapplication.classes.UserSession;
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
    private static final String dateJoinedColumn = "dateJoined";
    private static final String numOfOrdersColumn = "numOfOrders";
    private static final String rewardPointsColumn = "rewardPoints";

    public static void createTable(Connection connection){

        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                            "(id            INTEGER     PRIMARY KEY AUTOINCREMENT," +
                            " firstname     CHAR(255)   NOT NULL, " +
                            " lastname      CHAR(255)   NOT NULL, " +
                            " username      CHAR(255)   NOT NULL UNIQUE, " +
                            " password      CHAR(255)   NOT NULL, " +
                            " email         CHAR(255)   NOT NULL UNIQUE, " +
                            " isManager     BOOLEAN     NOT NULL, " +
                            " dateJoined    DATE        NOT NULL, " +
                            " numOfOrders   INTEGER     NOT NULL, " +
                            " rewardPoints  INTEGER     NOT NULL)";
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
                user = new Manager(rs.getString(firstNameColumn), rs.getString(lastNameColumn),
                        rs.getString(userNameColumn), rs.getString(passwordColumn), rs.getString(emailColumn),
                        rs.getInt(idColumn), rs.getString(dateJoinedColumn));
            } else {
                user = new Customer(rs.getString(firstNameColumn), rs.getString(lastNameColumn),
                        rs.getString(userNameColumn), rs.getString(passwordColumn), rs.getString(emailColumn),
                        rs.getInt(idColumn), rs.getString(dateJoinedColumn), rs.getInt(numOfOrdersColumn),
                        rs.getInt(rewardPointsColumn));
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

    public static Manager getManager(String userName, String password){

        Manager user = null;

        String condition = " (" + userNameColumn + "='" + userName +
                "' OR " + emailColumn + "='" + userName + "') " +
                " AND " + passwordColumn + "='" + password + "'";

        try(Connection connection = Database.connect()) {

            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition;
            ResultSet rs = stmt.executeQuery(sql);



            //user = new Manager();



            rs.close();
            stmt.close();
            connection.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
        return user;

    }

    public static Customer getCustomer(String userName, String password){

        Customer user = null;

        String condition = " (" + userNameColumn + "='" + userName +
                "' OR " + emailColumn + "='" + userName + "') " +
                " AND " + passwordColumn + "='" + password + "'";

        try(Connection connection = Database.connect()) {

            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition;
            ResultSet rs = stmt.executeQuery(sql);

            user = new Customer(rs.getString(firstNameColumn), rs.getString(lastNameColumn),
                    rs.getString(userNameColumn), rs.getString(passwordColumn), rs.getString(emailColumn),
                    rs.getInt(idColumn), rs.getString(dateJoinedColumn), rs.getInt(numOfOrdersColumn),
                    rs.getInt(rewardPointsColumn));

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
            new String[]{firstNameColumn, lastNameColumn, userNameColumn, emailColumn, passwordColumn, isManagerColumn,
                            dateJoinedColumn, numOfOrdersColumn, rewardPointsColumn},
            new Object[]{firstName, lastName, userName, email, password, isManager, "date()", 0, 0},
            new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN,
                            Types.DATE, Types.INTEGER, Types.INTEGER}
        );
        System.out.println("create user: " + id);

        if(id == -1){
            return null;
        } else {
            //update cache
            return new Customer(firstName, lastName, userName, password, email, id, LocalDate.now().toString(),
                    0, 0);
        }  

    }

    public static int updateUser(String firstName, String lastName, 
            String userName, String email, String password, int userID){

        String condition= String.format("WHERE (%s=%s)", idColumn, userID);

        //update database
        int id = (int) CRUDHelper.update(
            tableName,
            new String[]{
                firstNameColumn,
                lastNameColumn,
                userNameColumn,
                emailColumn,
                passwordColumn
            },
            new Object[]{
                firstName,
                lastName,
                userName,
                email,
                password
            },
            condition
        );

        //update cache
        User updateUser = UserSession.getCurrentUser();
        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setUserName(userName);
        updateUser.setEmail(email);
        updateUser.setPassword(password);
        UserSession.setCurrentUser(updateUser);
            
        return id;

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

    public static void updatePassword(String username, String password) {
        String query = "UPDATE users SET password=\"" + password + "\" WHERE username=\"" + username + "\"";
        try (Connection conn = Database.connect()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



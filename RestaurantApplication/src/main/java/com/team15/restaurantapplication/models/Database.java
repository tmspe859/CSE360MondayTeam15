package com.team15.restaurantapplication.models;

import java.sql.*;

import com.team15.restaurantapplication.RestaurantApplication;

public class Database {

    private static final String location = RestaurantApplication.class.getResource("database/database.db").toExternalForm();

    public static void checkTables(Connection connection){
        //Create tables if not exists
        UserModel.createTable(connection);
        OrderModel.createTable(connection);
        CouponModel.createTable(connection);
        MenuItemModel.createTable(connection);
        DeliveryInfoModel.createTable(connection);
    }

    public static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";

        Connection connection;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbPrefix + location);
            System.out.println("Database Connected.");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.toString());
            System.out.println("Could not connect to Database");
            return null;
        }

        return connection;
    }

}

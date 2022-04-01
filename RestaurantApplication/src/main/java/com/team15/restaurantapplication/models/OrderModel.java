package com.team15.restaurantapplication.models;

import java.sql.*;

import com.team15.restaurantapplication.classes.Order;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;

public class OrderModel {

    private static final String tableName = "orders";
    private static final String idColumn = "id";
    private static final String menuItemsColumn = "menuitems";
    private static final String totalCostColumn = "totalcost";
    private static final String orderStatusColumn = "orderstatus";
    private static final String createdAtColumn = "created_at";
    private static final String customerIdColumn = "customerid";

    public static void createTable(Connection connection){

        try {
   
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS orders " +
                           "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                           " menuitems      VARCHAR(255)    NOT NULL, " +
                           " totalcost      DOUBLE    NOT NULL, " + 
                           " orderstatus    VARCHAR(30), " +
                           " created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " + 
                           " customerid       INTEGER, " + 
                           " FOREIGN KEY(customerid) REFERENCES users(id)) ";
            stmt.executeUpdate(sql);
            stmt.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

         }

    }

    private static Order getOrder(String condition){

        Order order = null;

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition; 
            ResultSet rs = stmt.executeQuery(sql);

            /*order = new Order(rs.getString(menuItemsColumn), rs.getInt(totalCostColumn),
                    rs.getString(orderStatusColumn), rs.getDate(createdAtColumn),
                    rs.getInt(idColumn));*/

            rs.close();
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
         }

         return order;

    }

    public static Order getOrderByCustomer(int customerid){

        String condition = customerIdColumn + "=" + customerid;

        return getOrder(condition);

    }

    public static Order getOrderById(int orderid){

        String condition = idColumn + "=" + orderid;

        return getOrder(condition);

    }

    public static int createOrder(String menuItems, int totalCost, int customerId) throws emailExistsException, usernameTakenException {

        //update database
        int id = (int) CRUDHelper.create(
            tableName,
            new String[]{menuItemsColumn, totalCostColumn, orderStatusColumn, customerIdColumn},
            new Object[]{menuItems, totalCost, Order.Status.open.name(), customerId},
            new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER}
        );

        return id;

        //update cache
        
    }

}

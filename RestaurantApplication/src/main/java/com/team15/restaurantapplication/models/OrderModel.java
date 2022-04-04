package com.team15.restaurantapplication.models;

import java.sql.*;
import java.util.ArrayList;

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

    private static ArrayList<Order> getOrder(String condition){

        ArrayList<Order> orders = new ArrayList<>();

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition; 
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Order order = new Order(rs.getString(menuItemsColumn), rs.getDouble(totalCostColumn),
                    rs.getString(orderStatusColumn), rs.getDate(createdAtColumn).toString(),
                    rs.getInt(idColumn));


                orders.add(order);
            }
            

            rs.close();
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
         }

         return orders;

    }

    public static ArrayList<Order> getOrdersByCustomer(int customerid){

        String condition = customerIdColumn + "=" + customerid;

        return getOrder(condition);

    }

    public static Order getOrderById(int orderid){

        String condition = idColumn + "=" + orderid;

        return getOrder(condition).get(0);

    }

    public static int createOrder(String menuItems, double totalCost, int customerId) throws emailExistsException, usernameTakenException {

        //update database
        int id = (int) CRUDHelper.create(
            tableName,
            new String[]{menuItemsColumn, totalCostColumn, orderStatusColumn, customerIdColumn},
            new Object[]{menuItems, totalCost, Order.Status.open.name(), customerId},
            new int[]{Types.VARCHAR, Types.DOUBLE, Types.VARCHAR, Types.INTEGER}
        );

        return id;

        //update cache
        
    }

}

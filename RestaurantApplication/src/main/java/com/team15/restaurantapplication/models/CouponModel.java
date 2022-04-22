package com.team15.restaurantapplication.models;

import java.sql.*;
import java.util.ArrayList;

import com.team15.restaurantapplication.classes.Coupon;
import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;

public class CouponModel {
    
    private static final String tableName = "coupons";
    private static final String idColumn = "id";
    private static final String titleColumn = "title";
    private static final String percentOffColumn = "percentoff";
    private static final String createdAtColumn = "created_at";
    private static final String customerIdColumn = "customerid";

    public static void createTable(Connection connection){

        try {
   
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS coupons " +
                           "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                           " title      VARCHAR(255)    NOT NULL, " +
                           " percentoff      DOUBLE    NOT NULL, " +
                           " created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " + 
                           " customerid       INTEGER, " + 
                           " FOREIGN KEY(customerid) REFERENCES users(id)) ";
            stmt.executeUpdate(sql);
            stmt.close();
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

         }
    }

    private static ArrayList<Coupon> getCoupon(String condition){

        ArrayList<Coupon> coupons = new ArrayList<>();

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition; 
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Coupon coupon = new Coupon(rs.getString(titleColumn), rs.getDouble(percentOffColumn), rs.getInt(idColumn));
                coupons.add(coupon);
            }
            
            rs.close();
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
         }

         return coupons;

    }

    public static ArrayList<Coupon> getCouponsByCustomer(int customerid){

        String condition = customerIdColumn + "=" + customerid;

        return getCoupon(condition);

    }

    public static Coupon getCouponById(int couponId){

        String condition = idColumn + "=" + couponId;

        return getCoupon(condition).get(0);

    }

    public static int createCoupon(String title, double percentOff, int customerId) throws emailExistsException, usernameTakenException {

        //update database
        int id = (int) CRUDHelper.create(
            tableName,
            new String[]{titleColumn, percentOffColumn, customerIdColumn},
            new Object[]{title, percentOff, customerId},
            new int[]{Types.VARCHAR, Types.DOUBLE, Types.INTEGER}
        );

        return id;

        //update cache
        
    }

    public static double getCouponAmount(int id, String title){
        String condition = idColumn + "=" + id + " AND " +
         titleColumn + "=" + "\"" + title + "\"";

        return getCoupon(condition).get(0).getPercentOff();
    }


}

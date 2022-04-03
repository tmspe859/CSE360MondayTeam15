package com.team15.restaurantapplication.models;

import com.team15.restaurantapplication.classes.*;
import java.sql.*;


public class DeliveryInfoModel {
    public static final String tableName = "deliveryInfo";
    public static final String idColumn = "id";
    public static final String recipientColumn = "recipient";
    public static final String addressColumn = "address";
    public static final String phoneColumn = "phone";
    public static final Integer stringSize = 255;
    public static final String userForeignKey = "userRef";

    public static void createTable(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                    "%s INTEGER PRIMARY KEY NOT NULL, " +
                    "%s CHAR(%d) NOT NULL, " + 
                    "%s CHAR(%d) NOT NULL, " + 
                    "%s CHAR(%d) NOT NULL, " + 
                    "%s INTEGER NOT NULL, " +
                    "FOREIGN KEY (%s) REFERENCES users (id)" +
                    ")", 
                DeliveryInfoModel.tableName,
                DeliveryInfoModel.idColumn,
                DeliveryInfoModel.recipientColumn,
                DeliveryInfoModel.stringSize,
                DeliveryInfoModel.addressColumn,
                DeliveryInfoModel.stringSize,
                DeliveryInfoModel.phoneColumn,
                DeliveryInfoModel.stringSize,
                DeliveryInfoModel.userForeignKey,
                DeliveryInfoModel.userForeignKey
            );
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Integer addDeliveryInfo(DeliveryInfo deliveryInfo, Integer userID) {
        return DeliveryInfoModel.addDeliveryInfo(
            deliveryInfo.getRecipient(),
            deliveryInfo.getAddress(),
            deliveryInfo.getPhone(),
            userID
        );
    }

    public static Integer addDeliveryInfo(String recipient, String address, String phone, Integer userID) {
        try {

            return (int) CRUDHelper.create(
                DeliveryInfoModel.tableName, 
            new String[]{
                DeliveryInfoModel.recipientColumn,
                DeliveryInfoModel.addressColumn,
                DeliveryInfoModel.phoneColumn,
                DeliveryInfoModel.userForeignKey
            }, 
            new Object[]{
                recipient,
                address, 
                phone,
                userID
            }, 
            new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER
            }
            );
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return -1;
    }

    public static DeliveryInfo getDeliveryInfo(Integer userID) {
        DeliveryInfo result = null;
        try (Connection connection = Database.connect()) {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM " + DeliveryInfoModel.tableName +
                " WHERE (" + DeliveryInfoModel.userForeignKey + "=" + Integer.toString(userID) + ")";
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                result = new DeliveryInfo(
                        resultSet.getString(DeliveryInfoModel.recipientColumn),
                        resultSet.getString(DeliveryInfoModel.addressColumn),
                        resultSet.getString(DeliveryInfoModel.phoneColumn)
                );
            } else {
                result = null;
            }

            resultSet.close();
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    public static int updateDeliveryInfo(DeliveryInfo updatedInfo, Integer userID) {
        // Definetly should be optimized in the future!
        DeliveryInfo oldInfo = DeliveryInfoModel.getDeliveryInfo(userID);

        int id = (int) CRUDHelper.update(
            DeliveryInfoModel.tableName,
            new String[]{
                DeliveryInfoModel.recipientColumn,
                DeliveryInfoModel.addressColumn,
                DeliveryInfoModel.phoneColumn,
                DeliveryInfoModel.userForeignKey
            }, 
            new Object[]{
                oldInfo.getRecipient(),
                oldInfo.getAddress(),
                oldInfo.getPhone(),
                userID
            }, 
            new Object[]{
                updatedInfo.getRecipient(),
                updatedInfo.getAddress(),
                updatedInfo.getPhone(),
                userID
            }
        );

        //update cache
        Customer updateUser = (Customer) UserSession.getCurrentUser();
        updateUser.setDeliveryInfo(updatedInfo);
        UserSession.setCurrentUser(updateUser);

        return id;
    }

    public static int updateDeliveryInfo(String[] columnStrings, Object[] previousInfo, Object[] newInfo) {
        return (int) CRUDHelper.update(
            DeliveryInfoModel.tableName, 
            columnStrings, 
            previousInfo, 
            newInfo
        );
    }

}

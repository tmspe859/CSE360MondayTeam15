package com.team15.restaurantapplication.models;

import com.team15.restaurantapplication.classes.*;
import java.sql.*;


public class CardInfoModel {

    public static final String tableName = "cardInfo";
    public static final String idColumn = "id";
    public static final String cardNumberColumn = "cardNumber";
    public static final String cardNameColumn = "cardName";
    public static final String expirationColumn = "expiration";
    public static final String securityCodeColumn = "securityCode";
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
                CardInfoModel.tableName,
                CardInfoModel.idColumn,
                CardInfoModel.cardNumberColumn,
                CardInfoModel.stringSize,
                CardInfoModel.expirationColumn,
                CardInfoModel.stringSize,
                CardInfoModel.securityCodeColumn,
                CardInfoModel.stringSize,
                CardInfoModel.userForeignKey,
                CardInfoModel.userForeignKey
            );
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Integer addCardInfo(CardInfo cardInfo, Integer userID) {
        return CardInfoModel.addCardInfo(
            cardInfo.getCardNumber(),
            cardInfo.getCardName(),
            cardInfo.getExpiration(),
            cardInfo.getSecurityCode(),
            userID
        );
    }

    public static Integer addCardInfo(String cardNumber, String cardName, String expiration, String securityCode, Integer userID) {
        try {

            return (int) CRUDHelper.create(
                CardInfoModel.tableName, 
            new String[]{
                CardInfoModel.cardNumberColumn,
                CardInfoModel.cardNameColumn,
                CardInfoModel.expirationColumn,
                CardInfoModel.securityCodeColumn,
                CardInfoModel.userForeignKey
            }, 
            new Object[]{
                cardNumber,
                cardName, 
                expiration,
                securityCode,
                userID
            }, 
            new int[]{
                Types.VARCHAR,
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

    public static CardInfo getCardInfo(Integer userID) {

        CardInfo result = null;

        try (Connection connection = Database.connect()) {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM " + CardInfoModel.tableName +
                " WHERE (" + CardInfoModel.userForeignKey + "=" + Integer.toString(userID) + ")";
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                result = new CardInfo(
                        resultSet.getString(CardInfoModel.cardNumberColumn),
                        resultSet.getString(CardInfoModel.cardNameColumn),
                        resultSet.getString(CardInfoModel.expirationColumn),
                        resultSet.getString(CardInfoModel.securityCodeColumn)
                );
            } else {
                result = new CardInfo("", "", "", "");
            }

            resultSet.close();
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    public static int updateCardInfo(CardInfo updatedInfo, Integer userID) {
        // Definetly should be optimized in the future!
        CardInfo oldInfo = CardInfoModel.getCardInfo(userID);

        return (int) CRUDHelper.update(
            CardInfoModel.tableName,
            new String[]{
                CardInfoModel.cardNumberColumn,
                CardInfoModel.cardNameColumn,
                CardInfoModel.expirationColumn,
                CardInfoModel.securityCodeColumn,
                CardInfoModel.userForeignKey
            }, 
            new Object[]{
                oldInfo.getCardNumber(),
                oldInfo.getCardName(),
                oldInfo.getExpiration(),
                oldInfo.getSecurityCode(),
                userID
            }, 
            new Object[]{
                updatedInfo.getCardNumber(),
                updatedInfo.getCardName(),
                updatedInfo.getExpiration(),
                updatedInfo.getSecurityCode(),
                userID
            }
        );
    }

    public static int updateCardInfo(String[] columnStrings, Object[] previousInfo, Object[] newInfo) {
        return (int) CRUDHelper.update(
            CardInfoModel.tableName, 
            columnStrings, 
            previousInfo, 
            newInfo
        );
    }

}

package com.team15.restaurantapplication.models;

import java.sql.*;

import com.team15.restaurantapplication.exceptions.emailExistsException;
import com.team15.restaurantapplication.exceptions.usernameTakenException;

class CRUDHelper{

    public static long create(String tableName, String[] columns, Object[] values, int[] types) throws emailExistsException, usernameTakenException {
        int number = Math.min(Math.min(columns.length, values.length), types.length);

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 0; i < number; i++) {
            queryBuilder.append(columns[i]);
            if (i < number - 1) queryBuilder.append(", ");
        }
        queryBuilder.append(") ");
        queryBuilder.append(" VALUES (");
        for (int i = 0; i < number; i++) {
            switch (types[i]) {
                case Types.VARCHAR:
                    queryBuilder.append("'");
                    queryBuilder.append((String) values[i]);
                    queryBuilder.append("'");
                    break;
                case Types.INTEGER:
                    queryBuilder.append((int) values[i]);
                    break;
                case Types.DOUBLE:
                    queryBuilder.append((double) values[i]);
                    break;
                case Types.BOOLEAN:
                    queryBuilder.append((boolean) values[i]);
            }
            if (i < number - 1) queryBuilder.append(", ");
        }
        queryBuilder.append(");");

        try (Connection conn = Database.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Could not add record to database");
            System.err.println(ex.getClass() + " : " +ex.getMessage());
            if(ex.getMessage().contains("users.email")){
                throw new emailExistsException();
            } 
            if(ex.getMessage().contains("users.username")){
                throw new usernameTakenException();
            }
            
        }
        return -1;
    }

}
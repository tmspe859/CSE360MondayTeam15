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
                    break;
                case Types.DATE:
                    queryBuilder.append((String) values[i]);
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

    public static long update(String tableName, String[] columns, Object[] searchVals, Object[] updatedVals) {
        int number = Math.min(Math.min(columns.length, searchVals.length), updatedVals.length);
        Boolean isFirst = true;

        StringBuilder strBuilder = new StringBuilder("UPDATE " + tableName+ " ");
        StringBuilder updateValues = new StringBuilder("SET ");
        StringBuilder conditional = new StringBuilder("WHERE (");

        for (int i = 0; i < number; i++) {
            if (!isFirst) {
                updateValues.append(", ");
                conditional.append(" AND ");
            }

            if (searchVals[i] instanceof String) {
                updateValues.append(
                    String.format("%s=\"%s\"", columns[i], updatedVals[i])
                );
                conditional.append(
                    String.format("%s=\"%s\"", columns[i], searchVals[i])
                );
            } else {
                updateValues.append(
                    String.format("%s=%s", columns[i], updatedVals[i])
                );
                conditional.append(
                    String.format("%s=%s", columns[i], searchVals[i])
                );
            }

            isFirst = false;
        }

        strBuilder.append(updateValues.toString());
        strBuilder.append(" ");
        strBuilder.append(conditional.toString());
        strBuilder.append(");");
        
        try (Connection conn = Database.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(strBuilder.toString());

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
            System.out.println(ex.getMessage());
            System.out.println("Could not update record in database");
        }

        return -1;
    }

    public static long update(String tableName, String[] columns, Object[] values, String condition) {
        int number = Math.min(columns.length, values.length);

        boolean isFirst = true;

        StringBuilder strBuilder = new StringBuilder("UPDATE " + tableName + " ");
        StringBuilder updateValues = new StringBuilder("SET ");

        for (int i = 0; i < number; i++) {
            if (!isFirst) {
                updateValues.append(", ");
            }

            if (values[i] instanceof String) {
                updateValues.append(
                    String.format("%s=\"%s\"", columns[i], values[i])
                );
            } else {
                updateValues.append(
                    String.format("%s=%s", columns[i], values[i])
                );
            }

            isFirst = false;
        }

        strBuilder.append(updateValues.toString());
        strBuilder.append(" ");
        strBuilder.append(condition);
        strBuilder.append(";");
        
        try (Connection conn = Database.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(strBuilder.toString());

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
            System.out.println(ex.getMessage());
            System.out.println("Could not update record in database");
        }

        return -1;
    }
    
    public static long delete(String tableName, String[] columns, Object[] searchVals) {
        int number = Math.min(columns.length, searchVals.length);
        Boolean isFirst = true;

        StringBuilder strBuilder = new StringBuilder("DELETE FROM " + tableName + " ");
        StringBuilder conditional = new StringBuilder("WHERE (");

        for (int i = 0; i < number; i++) {
            if (!isFirst)
                conditional.append(" AND ");

            if (searchVals[i] instanceof String) {
                conditional.append(
                    String.format("%s=\"%s\"", columns[i], searchVals[i])
                );
            } else {
                conditional.append(
                    String.format("%s=%s", columns[i], searchVals[i])
                );
            }

            isFirst = false;
        }

        strBuilder.append(conditional.toString());
        strBuilder.append(");");
        
        try (Connection conn = Database.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(strBuilder.toString());

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
            System.out.println(ex.getMessage());
            System.out.println("Could not delete record in database");
        }

        return -1;
    }

    public static long delete(String tableName, String conditional) {
        StringBuilder strBuilder = new StringBuilder("DELETE FROM " + tableName+ " ");
        strBuilder.append(conditional);
        strBuilder.append(";");
        
        try (Connection conn = Database.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(strBuilder.toString());

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
            System.out.println(ex.getMessage());
            System.out.println("Could not delete record in database");
        }

        return -1;
    }
}

package com.team15.restaurantapplication.models;

import com.team15.restaurantapplication.classes.*;

import java.util.ArrayList;
import java.sql.*;

public class MenuItemModel {
    public static final String tableName = "menuItems";
    public static final String idColumn = "id";
    public static final String nameColumn = "name";
    public static final String priceColumn = "price";
    public static final String descColumn = "desc";
    public static final String ingredientsColumn = "ingredients";
    public static final String tagsColumn = "tags";
    public static final String imgPathColumn = "imgPath";
    public static final Integer stringSize = 255;
    
    public static void createTable(Connection connection) {
        try {
            Statement stmt = connection.createStatement();
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s INTEGER PRIMARY KEY NOT NULL, " +
                        "%s CHAR(%d) NOT NULL, %s CHAR(%d) NOT NULL, " +
                        "%s CHAR(%d) NOT NULL, %s CHAR(%d) NOT NULL, " +
                        "%s CHAR(%d) NOT NULL, %s CHAR(%d) NOT NULL" +
                    ")",
                MenuItemModel.tableName,
                MenuItemModel.idColumn,
                MenuItemModel.nameColumn,
                MenuItemModel.stringSize,
                MenuItemModel.priceColumn,
                MenuItemModel.stringSize,
                MenuItemModel.descColumn,
                MenuItemModel.stringSize,
                MenuItemModel.ingredientsColumn,
                MenuItemModel.stringSize,
                MenuItemModel.tagsColumn,
                MenuItemModel.stringSize,
                MenuItemModel.imgPathColumn,
                MenuItemModel.stringSize
            );
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static ArrayList<MenuItem> getMenu() {
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        try (Connection connection = Database.connect()) {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + MenuItemModel.tableName;
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                ArrayList<String> ingredients = getIngredientsFromString(
                    resultSet.getString(MenuItemModel.ingredientsColumn)
                );

                ArrayList<Boolean> tags = getTagsFromString(
                    resultSet.getString(MenuItemModel.tagsColumn)
                );

                Double price = Double.parseDouble(resultSet.getString(MenuItemModel.priceColumn));

                MenuItem currItem = new MenuItem(
                    resultSet.getString(MenuItemModel.nameColumn),
                    price,
                    resultSet.getString(MenuItemModel.descColumn),
                    ingredients,
                    tags,
                    resultSet.getString(MenuItemModel.imgPathColumn)
                );

                menu.add(currItem);
            }
            resultSet.close();
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return menu;
    }

    public static Integer addItem(MenuItem item) {
        return MenuItemModel.addItem(
            item.getName(),
            item.getPrice(),
            item.getDesc(),
            item.getIngredients(),
            item.getTags(),
            item.getImgPath()
        );
    }

    public static Integer addItem(String name, Double price, String desc, ArrayList<String> ingredients, ArrayList<Boolean> tags, String imgPath) {
        try {
            return (int) CRUDHelper.create(
                MenuItemModel.tableName, 
                new String[]{
                    MenuItemModel.nameColumn,
                    MenuItemModel.priceColumn,
                    MenuItemModel.descColumn,
                    MenuItemModel.ingredientsColumn,
                    MenuItemModel.tagsColumn,
                    MenuItemModel.imgPathColumn
                }, 
                new Object[]{
                    name,
                    String.valueOf(price),
                    desc,
                    MenuItemModel.ingredientsToString(ingredients),
                    MenuItemModel.tagsToString(tags),
                    imgPath
                }, 
                new int[]{
                    Types.VARCHAR,
                    Types.VARCHAR,
                    Types.VARCHAR,
                    Types.VARCHAR,
                    Types.VARCHAR,
                    Types.VARCHAR
                }
                );
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
            return -1;
        }

    private static ArrayList<String> getIngredientsFromString(String ingredientsString) {
        ArrayList<String> ingredients = new ArrayList<String>();
        String[] splitString = ingredientsString.split(",");
        for (String ingredient : splitString)
            ingredients.add(ingredient);
        return ingredients;
    }

    private static ArrayList<Boolean> getTagsFromString(String tagString) {
        ArrayList<Boolean> tags = new ArrayList<Boolean>();
        String[] splitString = tagString.split(",");
        for (String tag : splitString)
            tags.add(tag.compareTo("1") == 0);
        return tags;
    }

    private static String ingredientsToString(ArrayList<String> ingredients) {
        Boolean firstAddition = true;
        StringBuilder strBuilder = new StringBuilder();
        for (String ingredient : ingredients) {
            if (!firstAddition)
                strBuilder.append(',');
            strBuilder.append(ingredient);
            firstAddition = false;
        }
        return strBuilder.toString();
    }

    private static String tagsToString(ArrayList<Boolean> tags) {
        Boolean firstAddition = true;
        StringBuilder strBuilder = new StringBuilder();
        for (Boolean tag : tags) {
            if (!firstAddition)
                strBuilder.append(',');
            strBuilder.append((tag) ? "1" : "0");
            firstAddition = false;
        }
        return strBuilder.toString();
    }
}
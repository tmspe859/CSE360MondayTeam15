package com.team15.restaurantapplication.classes;

import com.team15.restaurantapplication.models.MenuItemModel;
import java.util.ArrayList;

public class Menu {
    private static Menu instance;

    private ArrayList<MenuItem> menuItems;

    private Menu() {
        this.menuItems = MenuItemModel.getMenu();
    }

    public static Menu getInstance() {
        if (Menu.instance == null) 
            Menu.instance = new Menu();
        return Menu.instance;
    }

    public Boolean addItem(MenuItem newItem) {
        if (MenuItemModel.addItem(newItem) == -1) return false;
        return this.menuItems.add(newItem);
    }

    public Boolean updateItem(MenuItem oldItem, MenuItem newItem) {
        if (MenuItemModel.updateItem(oldItem, newItem) == -1) return false;
        int oldIndex = this.menuItems.indexOf(oldItem);
        return (this.menuItems.set(oldIndex, newItem) == oldItem);
    }

    public Boolean updateItem(String searchName, String[] columns, Object[] updatedVals) {
        if (MenuItemModel.updateItem(searchName, columns, updatedVals) == -1) return false;
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem item = this.menuItems.get(i);
            if (item.getName().compareTo(searchName) == 0) {
                MenuItem updatedItem = MenuItemModel.getByName(searchName);
                if (updatedItem == null) return false;
                this.menuItems.set(i, updatedItem);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteItem(MenuItem item) {
        if (MenuItemModel.deleteItem(item) == -1) return false;
        return this.menuItems.remove(item);
    }

    public Boolean deleteItem(String name) {
        if (MenuItemModel.deleteItem(name) == -1) return false;
        for (int i = 0; i < this.menuItems.size(); i++) {
            MenuItem currItem = this.menuItems.get(i);
            if (currItem.getName().compareTo(name) == 0) {
                this.menuItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void refreshListings() {
        this.menuItems = MenuItemModel.getMenu();
    }

    public ArrayList<MenuItem> getMenu() {
        return this.menuItems;
    }

    private Boolean tagArraysMatch(ArrayList<Boolean> itemTags, ArrayList<Boolean> searchTags) {
        for (int i = 0; i < Math.min(itemTags.size(), searchTags.size()); i++) {
            if (searchTags.get(i) && !itemTags.get(i)) return false;
        }
        return true;
    }

    public ArrayList<MenuItem> getFilteredItems(String searchTerm, ArrayList<Boolean> searchTags) {
        Boolean tags = false;
        for (Boolean tag : searchTags) tags |= tag;
        if (searchTerm.equals("") && !tags) return this.menuItems;
        
        ArrayList<MenuItem> results = new ArrayList<MenuItem>();
        for (MenuItem item : this.menuItems) {
            if(
                !item.getName()
                .toLowerCase()
                .contains(
                    searchTerm.toLowerCase()
                )
            ) continue;
            if (!this.tagArraysMatch(item.getTags(), searchTags)) continue;
            results.add(item);
        }
        return results;
    }

}

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

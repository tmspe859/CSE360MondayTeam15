package com.team15.resturantapplication.models;

import java.util.ArrayList;
import com.team15.restaurantapplication.classes.Menu;
import com.team15.restaurantapplication.classes.MenuItem;
import com.team15.restaurantapplication.models.MenuItemModel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MenuTest {

    private static MenuItem testItem;

    @BeforeAll
    public static void setUpTests() {
        System.out.println("Preparing Menu Tests...");
        ArrayList<String> testIngs = new ArrayList<String>();
        testIngs.add("ing1");
        testIngs.add("ing2");
        testIngs.add("ing3");
        testIngs.add("ing4");

        ArrayList<Boolean> testTags = new ArrayList<Boolean>();
        testTags.add(true);
        testTags.add(false);
        testTags.add(true);

        MenuItem itemForTesting = new MenuItem(
            "testItem",
            10.99,
            "This is a test desc",
            testIngs,
            testTags,
            "test/path/dir"
        );
        MenuTest.testItem = itemForTesting;

        Menu menu = Menu.getInstance();
        if (menu.deleteItem(MenuTest.testItem.getName()))
            System.out.println("Test Item Removed Before Testing...");
    }
    
    @Test
    @Order(1)
    public void TestNotPresent() {
        Menu menu = Menu.getInstance();
        ArrayList<MenuItem> items = menu.getMenu();
        assertFalse(items.contains(MenuTest.testItem));
    }

    @Test
    @Order(2)
    public void TestAddition() {
        Menu menu = Menu.getInstance();
        ArrayList<MenuItem> items = menu.getMenu();
        menu.addItem(MenuTest.testItem);
        assertTrue(items.contains(MenuTest.testItem));
    }

    @Test
    @Order(3)
    public void TestNoDuplicated() {
        Menu menu = Menu.getInstance();
        ArrayList<MenuItem> items = menu.getMenu();
        assertTrue(items.contains(MenuTest.testItem));
        assertFalse(menu.addItem(MenuTest.testItem));
    }

    @Test
    @Order(4)
    public void TestFilter() {
        Menu menu = Menu.getInstance();
        ArrayList<MenuItem> testItemArr = new ArrayList<MenuItem>();
        testItemArr.add(MenuTest.testItem);

        assertArrayEquals(
            testItemArr.toArray(),
            menu.getFilteredItems(
                MenuTest.testItem.getName(), 
                MenuTest.testItem.getTags()
            ).toArray()
        );
    }

    @Test
    @Order(5)
    public void TestUpdate() {
        Menu menu = Menu.getInstance();  
        menu.updateItem(
            MenuTest.testItem.getName(), 
            new String[]{
                MenuItemModel.priceColumn
            }, 
            new Object[]{
                120.99
            }
        );
        ArrayList<MenuItem> items = menu.getMenu();
        for (MenuItem item : items)
            if (item.getName().compareTo(MenuTest.testItem.getName()) == 0) {
                assertEquals(120.99, item.getPrice());
                if (120.99 == item.getPrice())
                    MenuTest.testItem = item;
                break;
            }
    }

    @Test
    @Order(6)
    public void TestDeletion() {
        Menu menu = Menu.getInstance();
        ArrayList<MenuItem> items = menu.getMenu();
        assertTrue(items.contains(MenuTest.testItem));
        menu.deleteItem(MenuTest.testItem.getName());
        assertFalse(items.contains(MenuTest.testItem));
    }
}
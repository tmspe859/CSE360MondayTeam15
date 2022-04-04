package com.team15.restaurantapplication.classes;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;
    private DeliveryInfo deliveryInfo;
    private Double totalCost;
    private Status status;
    private String date;
    private String itemString;
    private int id;

    public enum Status {
        open,
        complete
    }

    public Order() {
        this.items = new ArrayList<MenuItem>();
        this.totalCost = 0.0;
    }

    public Order(String itemString, Double totalCost, String status, String date, int id) {
        this.itemString = itemString;
        this.totalCost = totalCost;
        this.date = date;
        this.id = id;
        if(status == "open")
            this.status = Status.open;
        else
            this.status = Status.complete;
    }

    public void addItem(MenuItem newItem) {
        Boolean itemAdded = this.items.add(newItem);
        if (itemAdded)
            this.totalCost += newItem.getPrice();
    }

    public void removeItem(MenuItem item) {
        Boolean itemRemoved = this.items.remove(item);
        if (itemRemoved)
            this.totalCost -= item.getPrice();
    }

    public String generateItemString() {
        Boolean firstAddition = true;
        StringBuilder strBuilder = new StringBuilder();
        for (MenuItem item : items) {
            if (!firstAddition)
                strBuilder.append(',');
            strBuilder.append(item.getName());
            firstAddition = false;
        }
        return strBuilder.toString();
    }

    /*
    public ArrayList<MenuItem> getItemsFromString(String itemString){
        ArrayList<MenuItem> items = new ArrayList<>();
        String[] splitString = itemString.split(",");
        for (String item : splitString){
            items.add(new MenuItem());
        }
        return items;
    }*/

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) { this.deliveryInfo = deliveryInfo; }

    public ArrayList<MenuItem> getItems() { return this.items; }
    public Double getTotalCost() { return this.totalCost; }
    public DeliveryInfo getDeliveryInfo() { return this.deliveryInfo; }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemString() {
        return this.itemString;
    }

    public void setItemString(String itemString) {
        this.itemString = itemString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

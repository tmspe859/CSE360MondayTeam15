package com.team15.restaurantapplication.classes;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;
    private DeliveryInfo deliveryInfo;
    private Double totalCost;

    public enum Status {
        open,
        complete
    }

    public Order() {
        this.items = new ArrayList<MenuItem>();
        this.totalCost = 0.0;
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

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) { this.deliveryInfo = deliveryInfo; }

    public ArrayList<MenuItem> getItems() { return this.items; }
    public Double getTotalCost() { return this.totalCost; }
    public DeliveryInfo getDeliveryInfo() { return this.deliveryInfo; }
}

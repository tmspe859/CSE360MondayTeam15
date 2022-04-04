package com.team15.restaurantapplication.classes;

import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;

public class OrderQueue {
    private static OrderQueue instance = null;
    private Queue<Order> orders;

    public static OrderQueue getInstance() {
        if (OrderQueue.instance == null)
            OrderQueue.instance = new OrderQueue();
        return OrderQueue.instance;
    }

    private OrderQueue() {
        this.orders = new LinkedList<Order>();
    }

    public Boolean addOrder(Order order) {
        return this.orders.add(order);
    }

    public Order popOrder() {
        if (this.orders.isEmpty()) return null;
        return this.orders.remove();        
    }

    public int getOrderPosition(Order order) {
        Iterator<Order> it = this.orders.iterator();
        int index = 0;
        while (it.hasNext()) {
            Order currOrder = it.next();
            if (currOrder == order) return index;
            index++;
        }
        return -1;
    }

    public int getWaitTime() {
        return this.orders.size() * 30;
    }
}

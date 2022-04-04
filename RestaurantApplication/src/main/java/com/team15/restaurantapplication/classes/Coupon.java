package com.team15.restaurantapplication.classes;

public class Coupon {
    private String title;
    private Double percentOff;
    private int id;

    public Coupon(String title, Double percentOff, int id) {
        this.title = title;
        this.percentOff = percentOff;
        this.id = id;
    }

    public String getTitle() { return this.title; }
    public Double getPercentOff() { return this.percentOff; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

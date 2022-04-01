package com.team15.restaurantapplication.classes;

public class DeliveryInfo {

    private String recipient;
    private String address;
    private String phone;

    public DeliveryInfo(String recipient, String address, String phone) {
        this.recipient = recipient;
        this.address = address;
        this.phone = phone;
    }

    public String getRecipient() { return this.recipient; }
    public String getAddress() { return this.address; }
    public String getPhone() { return this.phone; }
}

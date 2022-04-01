package com.team15.restaurantapplication.classes;

public class Manager extends User {
    
    Manager() {
        super();
        this.isManagerBoolean = true;
    }

    Manager(String firstName, String lastName, String userName, String password, String email, Integer accountID) {
        super(firstName, lastName, userName, password, email, accountID);
        this.isManagerBoolean = true;
    }

}

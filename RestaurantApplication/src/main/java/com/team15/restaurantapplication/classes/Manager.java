package com.team15.restaurantapplication.classes;

public class Manager extends User {
    
    public Manager() {
        super();
        this.isManagerBoolean = true;
    }

    public Manager(String firstName, String lastName, String userName, String password, String email, Integer accountID,
                   String dateJoined) {
        super(firstName, lastName, userName, password, email, accountID);
        this.isManagerBoolean = true;
        this.dateJoined = dateJoined;
    }

}

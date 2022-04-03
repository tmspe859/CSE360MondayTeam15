package com.team15.restaurantapplication.classes;

public abstract class User {

    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String email;
    protected Integer accountID;
    protected String dateJoined;
    protected Boolean isManagerBoolean = false;
    
    public User() {
        this.firstName = "";
        this.lastName = "";
        this.userName = "";
        this.password = "";
        this.email = "";
        this.accountID = -1;
    }

    public User(String firstName, String lastName, String userName, String password, String email, Integer accountID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountID = accountID;
    }
    
    public void updateInfo() {}

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserName() { return this.userName; }
    public void setUserName(String userName) { this.userName = userName; }

    // Should be done with a database lookup
    public String getPassword() { return this.password; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public void setAccountID(Integer accountID) {
        if (this.accountID != null) return;
        this.accountID = accountID;
    }

    public Integer getAccountID() { return this.accountID; }

    public Boolean isManager() {
        return this.isManagerBoolean;
    }

    public String getJoinedDate() {return this.dateJoined;}
}

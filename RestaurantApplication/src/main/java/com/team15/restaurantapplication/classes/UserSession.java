package com.team15.restaurantapplication.classes;

public final class UserSession {
    
    private static UserSession instance;

    private User currentUser;

    private UserSession(User user){
        this.currentUser = user;
    }

    public static UserSession getInstance(User user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
        return instance;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public void cleanUserSession() {
        currentUser = null;
    }

}
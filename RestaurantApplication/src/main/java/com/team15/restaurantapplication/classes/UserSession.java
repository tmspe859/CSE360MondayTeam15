package com.team15.restaurantapplication.classes;

public final class UserSession {
    
    private static UserSession instance;
    private static User currentUser;

    private UserSession(User user){
        this.currentUser = user;
    }

    public static UserSession getInstance(User user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
        return instance;
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static boolean isManager(){
        return currentUser.isManager();
    }

    public static CardInfo getCardInfo(){
        Customer customer = (Customer) getCurrentUser();
        return customer.getPaymentInfo();
    }

    public static DeliveryInfo getDeliveryInfo(){
        Customer customer = (Customer) getCurrentUser();
        return customer.getDeliveryInfo();
    }

    public void cleanUserSession() {
        currentUser = null;
    }

}
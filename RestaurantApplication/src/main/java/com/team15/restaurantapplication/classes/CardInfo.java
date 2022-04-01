package com.team15.restaurantapplication.classes;

public class CardInfo {
    private String cardNumber;
    private String cardName;
    private String expiration;
    private String securityCode;

    public CardInfo(String cardNumber, String cardName, String expiration, String securityCode) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.expiration = expiration;
        this.securityCode = securityCode;
    }

    public String getCardNumber() { return this.cardNumber; }
    public String getCardName() { return this.cardName; }
    public String getExpiration() { return this.expiration; }
    public String getSecurityCode() { return this.securityCode; }

}

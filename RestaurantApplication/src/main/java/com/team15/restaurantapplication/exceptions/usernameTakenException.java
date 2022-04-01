package com.team15.restaurantapplication.exceptions;

public class usernameTakenException extends Exception {
    
    public usernameTakenException () {
    }

    public usernameTakenException (String message) {
        super (message);
    }

    public usernameTakenException (Throwable cause) {
        super (cause);
    }

    public usernameTakenException (String message, Throwable cause) {
        super (message, cause);
    }

}

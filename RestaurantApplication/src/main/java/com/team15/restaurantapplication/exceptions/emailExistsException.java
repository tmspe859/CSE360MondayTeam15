package com.team15.restaurantapplication.exceptions;

public class emailExistsException extends Exception {
    
    public emailExistsException () {
    }

    public emailExistsException (String message) {
        super (message);
    }

    public emailExistsException (Throwable cause) {
        super (cause);
    }

    public emailExistsException (String message, Throwable cause) {
        super (message, cause);
    }

}

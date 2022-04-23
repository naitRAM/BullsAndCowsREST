package com.sg.ramimans.bullsandcowsrest.service;

/**
 *
 * @author Rami Mansieh
 * email: rmansieh@gmail.com
 * data: Apr. 23, 2022
 * purpose: 
 */
public class InvalidGameException extends Exception {

    public InvalidGameException(String message) {
        super(message);
    }
    
    public InvalidGameException(String message, Throwable cause) {
        super(message, cause);
    }

}

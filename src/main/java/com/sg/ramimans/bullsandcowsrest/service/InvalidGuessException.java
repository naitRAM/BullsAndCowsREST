package com.sg.ramimans.bullsandcowsrest.service;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 23, 2022 purpose:
 */
public class InvalidGuessException extends Exception {

    public InvalidGuessException(String message) {
        super(message);
    }

    public InvalidGuessException(String message, Throwable cause) {
        super(message, cause);
    }
}

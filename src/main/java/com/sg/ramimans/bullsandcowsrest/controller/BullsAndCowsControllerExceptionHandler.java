package com.sg.ramimans.bullsandcowsrest.controller;




import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sg.ramimans.bullsandcowsrest.entity.Err;
import com.sg.ramimans.bullsandcowsrest.service.InvalidGameException;
import com.sg.ramimans.bullsandcowsrest.service.InvalidGuessException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Rami Mansieh
 * email: rmansieh@gmail.com
 * data: Apr. 23, 2022
 * purpose: 
 */

@ControllerAdvice
@RestController
public class BullsAndCowsControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final String MESSAGE = "Please check your input";
    
    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<Err> handleInvalidFormatException(InvalidFormatException ex, WebRequest request){
        Err err = new Err();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Err> handleNullPointerException(
            NullPointerException ex,
            WebRequest request) {

        Err err = new Err();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Err> handleSQLException(SQLIntegrityConstraintViolationException ex, WebRequest request){
        Err err = new Err();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    } 
    
    @ExceptionHandler(InvalidGameException.class)
    public final ResponseEntity<Err> handleInvalidGameException(InvalidGameException ex, WebRequest request) {
        Err err = new Err();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(InvalidGuessException.class)
    public final ResponseEntity<Err> handleInvalidGuessException(InvalidGuessException ex, WebRequest request) {
        Err err = new Err();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    

}


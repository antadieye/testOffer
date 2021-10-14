package com.app.testoffer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * @author A697004
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceException extends Exception{
/**
 * it is used to trace errors and display them on the console.
 */
    private static final long serialVersionUID = 1L;

    public ResourceException(String message){
        super(message);
    }
}

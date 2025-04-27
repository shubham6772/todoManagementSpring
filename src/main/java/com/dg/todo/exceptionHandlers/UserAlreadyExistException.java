package com.dg.todo.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException{
    private String message;

    public UserAlreadyExistException(String message){
        super(message);
    }
}

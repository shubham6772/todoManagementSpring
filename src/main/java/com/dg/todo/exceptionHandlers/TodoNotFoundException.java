package com.dg.todo.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException{
    private String message;
    public TodoNotFoundException(String message){
        super(message);
    }
}

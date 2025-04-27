package com.dg.todo.exceptionHandlers;

import com.dg.todo.dto.UserClientDto;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TodoAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistException(TodoAlreadyExistException exception, WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "TODO_ALREADY_EXIST"
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistException(UserAlreadyExistException exception, WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_ALREADY_EXIST"
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND_WITH_ID"
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTodoNotFoundException(TodoNotFoundException exception, WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "TODO_NOT_FOUND"
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), errorMessage, request.getDescription(false), "BAD_REQUEST");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> handleInvalidJson(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(LocalDateTime.now(), "Malformed JSON or type mismatch: " + ex.getMostSpecificCause().getMessage(), request.getDescription(false), "BAD_REQUEST");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

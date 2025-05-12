package com.dg.todo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // For 404 or 405 or any error, send blank white page
            if (statusCode == HttpStatus.NOT_FOUND.value() ||
                    statusCode == HttpStatus.METHOD_NOT_ALLOWED.value() ||
                    statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.ok("");
            }
        }

        // default: blank response
        return ResponseEntity.ok("");
    }
}

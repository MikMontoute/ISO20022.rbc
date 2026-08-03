package com.rbc.iso20022.exception;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> validationException(
            ValidationException ex) {

        return ResponseEntity.badRequest()
                .body(Map.of(
                        "status", "FAILED",
                        "message", ex.getMessage()));
    }
}

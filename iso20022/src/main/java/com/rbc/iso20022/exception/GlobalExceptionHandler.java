package com.rbc.iso20022.exception;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateMessageException.class)
	public ResponseEntity<?> handleDuplicate(
	        DuplicateMessageException ex) {

	    return ResponseEntity.status(409)
	            .body(Map.of(
	                    "status", "FAILED",
	                    "message", ex.getMessage()));
	}
}

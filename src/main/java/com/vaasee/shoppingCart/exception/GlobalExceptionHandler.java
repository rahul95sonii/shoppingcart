package com.vaasee.shoppingCart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vaasee.shoppingCart.exception.GlobalExceptions.*;
import com.vaasee.shoppingCart.util.ErrorResponse;

/*
 * 
 * author rahul.soni
 * 
 * */

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoRecordFoundException(NoRecordFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
}

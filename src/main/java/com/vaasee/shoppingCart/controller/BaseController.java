package com.vaasee.shoppingCart.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vaasee.shoppingCart.util.Response;


/*
 * 
 * author rahul.soni
 * 
 * */

public class BaseController {

	// Utility method for sending a success response
	protected ResponseEntity<Object> sendSuccessResponse(String message, Object data) {
		return sendResponse("success", message, data, HttpStatus.OK);
	}

	private ResponseEntity<Object> sendResponse(String status, String message, Object data, HttpStatus httpStatus) {
		// Create the response object with the provided parameters
		Response response = new Response(status, message, data);

		// Return the ResponseEntity with the constructed response and the given HTTP
		// status
		return new ResponseEntity<>(response, httpStatus);
	}

	// Utility method for sending error response
	protected ResponseEntity<Object> sendErrorResponse(String message) {
		return sendResponse("error", message, null, HttpStatus.BAD_REQUEST);
	}

}

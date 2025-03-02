package com.vaasee.shoppingCart.exception;


/*
 * 
 * author rahul.soni
 * 
 * */

public class GlobalExceptions {
	// NoRecordFoundException - For cases where no record is found
	public static class NoRecordFoundException extends RuntimeException {
		public NoRecordFoundException(String message) {
			super(message);
		}
	}

	// InvalidLoginException - For invalid login attempts
	public static class InvalidLoginException extends RuntimeException {
		public InvalidLoginException(String message) {
			super(message);
		}
	}

	// ProductNotFoundException - For product not found situations
	public static class ProductNotFoundException extends RuntimeException {
		public ProductNotFoundException(String message) {
			super(message);
		}
	}

	// OrderProcessingException - For errors during order processing
	public static class OrderProcessingException extends RuntimeException {
		public OrderProcessingException(String message) {
			super(message);
		}
	}

}

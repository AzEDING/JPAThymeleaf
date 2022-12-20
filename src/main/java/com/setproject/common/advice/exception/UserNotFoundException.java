package com.setproject.common.advice.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}

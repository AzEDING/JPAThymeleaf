package com.setproject.common.advice.exception;

public class ObjectNotFoundException extends RuntimeException {
	public ObjectNotFoundException() {
		super();
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
}

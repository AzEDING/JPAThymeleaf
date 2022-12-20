package com.setproject.common.advice.exception;

public class ObjectAlreadyExistException extends RuntimeException {
	public ObjectAlreadyExistException() {
		super();
	}
	
	public ObjectAlreadyExistException(String message) {
		super(message);
	}

}

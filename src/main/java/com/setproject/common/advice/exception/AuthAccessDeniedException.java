package com.setproject.common.advice.exception;

public class AuthAccessDeniedException extends RuntimeException {
	public AuthAccessDeniedException() {
		super();
	}
	
	public AuthAccessDeniedException(String message) {
		super(message);
	}

}

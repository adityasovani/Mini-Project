package com.cg.exception;

public class AuthException extends Exception{
	
	public AuthException(String s) {
		super(s);
	}
	
	public AuthException() {
		super("Authentication Failure");
	}
	
}

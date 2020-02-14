package com.cg.exception;

public class EmployeeException extends Exception {

	public EmployeeException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	public EmployeeException() {
		super("Employee does not exist");
	}

}

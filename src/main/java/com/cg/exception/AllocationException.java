package com.cg.exception;

public class AllocationException extends Exception {
	public AllocationException() {
		super("Allocation Error");
	}

	public AllocationException(String s) {
		super(s);
	}
}

package com.cg.exception;

public class AssetException extends Exception{
	
	public AssetException() {
		super("Asset Error");
	}
	
	public AssetException(String s) {
		super(s);
	}
}

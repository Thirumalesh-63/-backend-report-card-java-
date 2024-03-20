package com.feuji.security.exceptionhandler;

public class useralreadyexist extends RuntimeException{
	
	public useralreadyexist(String msg) {
		super(msg);
	}

}

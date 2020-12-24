package com.capgemini.census.exception;

public class ApplicationException extends Exception{
	private String message;

	public ApplicationException() {
		
	}

	public ApplicationException(String message) {
		super(message);
		this.message=message;
	}
	
	public ApplicationException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "ApplicationException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

}

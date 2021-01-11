package com.capgemini.census.exception;

/**
 * This class is for user defined exception. 
 * It handles the User Exception and displays appropriate message.
 * @author HP
 *
 */
@SuppressWarnings("serial")
public class UserException extends Exception{
	private String message;

	public UserException() {

	}

	public UserException(String message) {
		super(message);
		this.message=message;
	}

	public UserException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "UserException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

}

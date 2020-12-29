package com.capgemini.census.exception;
/**
 * This class is for user defined exception. 
 * It handles the member Information Exception.
 * @author HP
 *
 */
@SuppressWarnings("serial")
public class MemberInformationException extends Exception
{
	private String message;

	public MemberInformationException() {
		
	}

	public MemberInformationException(String message) {
		super(message);
		this.message=message;
	}
	
	public MemberInformationException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "MemberInformationException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

}

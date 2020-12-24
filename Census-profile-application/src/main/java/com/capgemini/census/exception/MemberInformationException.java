package com.capgemini.census.exception;

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

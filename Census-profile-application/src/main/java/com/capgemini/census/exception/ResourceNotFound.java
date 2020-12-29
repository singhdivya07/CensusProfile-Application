package com.capgemini.census.exception;

public class ResourceNotFound extends RuntimeException {

	 /**
	 * This class displays the exception if the resource is not found.
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String message){
	        super(message);
	    }
}
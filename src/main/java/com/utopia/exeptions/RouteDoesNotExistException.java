package com.utopia.exeptions;

public class RouteDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public RouteDoesNotExistException() {};

	public RouteDoesNotExistException(String message) {
		super(message);
	}

}

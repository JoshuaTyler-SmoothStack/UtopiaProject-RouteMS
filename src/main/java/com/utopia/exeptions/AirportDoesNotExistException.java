package com.utopia.exeptions;

public class AirportDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public AirportDoesNotExistException() {};

	public AirportDoesNotExistException(String message) {
		super(message);

	}

}

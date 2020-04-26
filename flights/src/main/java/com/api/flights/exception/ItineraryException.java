package com.api.flights.exception;

public class ItineraryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItineraryException(String errorMessage) {
		super(errorMessage);		
	}

	public ItineraryException(String errorMessage, Throwable e) {
		super(errorMessage, e);		
	}
}

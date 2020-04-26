package com.api.flights.exception;

public class ItineraryNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItineraryNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public ItineraryNotFoundException(String errorMessage, Throwable e) {
		super(errorMessage, e);		
	}
}

package com.api.flights.service;

import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;


public interface FlightService {
	
	Itinerary retrieveFlightItinerary(String id) throws ItineraryException, ItineraryNotFoundException;
	
	String createFlightItinerary(Itinerary itinerary) throws ItineraryException;
}

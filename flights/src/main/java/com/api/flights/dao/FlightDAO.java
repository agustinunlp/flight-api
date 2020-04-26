package com.api.flights.dao;

import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;

public interface FlightDAO {
	
	Itinerary retrieveFlightItinerary(String id) throws ItineraryException, ItineraryNotFoundException;
	
	String createFlightItinerary(Itinerary itinerary) throws ItineraryException;
}

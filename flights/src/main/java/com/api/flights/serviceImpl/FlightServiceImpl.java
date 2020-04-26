package com.api.flights.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.flights.dao.FlightDAO;
import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;
import com.api.flights.service.FlightService;
@Service
public class FlightServiceImpl implements FlightService{
	
	@Autowired
	private FlightDAO dao;

	@Override
	public Itinerary retrieveFlightItinerary(String id) throws ItineraryException, ItineraryNotFoundException {
		return dao.retrieveFlightItinerary(id);
	}

	@Override
	public String createFlightItinerary(Itinerary itinerary) throws ItineraryException {
		return dao.createFlightItinerary(itinerary);
	}
}

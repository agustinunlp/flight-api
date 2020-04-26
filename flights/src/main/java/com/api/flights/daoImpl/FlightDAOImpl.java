package com.api.flights.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.api.flights.dao.FlightDAO;
import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;

@Service
public class FlightDAOImpl implements FlightDAO{

	@Autowired
	@Qualifier("itinerariesTemplate")
	private MongoTemplate itinerariesTemplate;
	
	@Override
	public Itinerary retrieveFlightItinerary(String id) throws ItineraryNotFoundException, ItineraryException {
		Itinerary itinerary = null;
		try {
			itinerary = itinerariesTemplate.findById(id, Itinerary.class);
		} catch (Exception e) {
			throw new ItineraryException(String.format("Itinerary with id: %s throwed an error", id), e);
		}
		if (itinerary == null) {
			throw new ItineraryNotFoundException(String.format("Itinerary with id: %s not found", id));
		}			
		return itinerary;
	}

	@Override
	public String createFlightItinerary(Itinerary itinerary) throws ItineraryException {
		try {
			Itinerary insertedFlightItinerary = itinerariesTemplate.insert(itinerary);			
			return insertedFlightItinerary.getId();
		} catch (Exception e) {
			throw new ItineraryException("Error Creating itinerary", e);		
		}
	}
}

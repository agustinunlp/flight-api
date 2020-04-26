package com.api.flights.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;
import com.api.flights.response.ItineraryIdentifierResponse;
import com.api.flights.response.ItineraryResponse;
import com.api.flights.service.FlightService;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
@RestController
@RequestMapping(path = "/flight")
public class FlightController 
{
    private static final String ITINERARY_NOT_FOUND = "Itinerary not found";

	private static final String ITINERARY_ERROR = "Itinerary Error";

	@Autowired
    private FlightService flightService;

    private Logger logger = LogManager.getLogger(FlightController.class);

    /**
     * Retrieves Itinerary
     * @param id, represents the Itinerary Unique Identifier
     * @return ResponseEntity
     */
    @GetMapping(path="/itinerary/{id}", produces = "application/json")
    public ResponseEntity<ItineraryResponse> retrieve(@PathVariable(value = "id") String id) 
    {
    	logger.info(String.format("Retrieve Itinerary %s", id));
    	
    	Itinerary itinerary = null;
        try {
			itinerary = flightService.retrieveFlightItinerary(id);
		} catch (ItineraryException e) {
			logger.error(ITINERARY_ERROR, e);
			return new ResponseEntity<ItineraryResponse>(new ItineraryResponse(itinerary, ITINERARY_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ItineraryNotFoundException e) {
			logger.error(ITINERARY_NOT_FOUND, e);
			return new ResponseEntity<ItineraryResponse>(new ItineraryResponse(itinerary, ITINERARY_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<ItineraryResponse>(new ItineraryResponse(itinerary), HttpStatus.OK);
    }
    
    /**
     * Create Itinerary
     * @param itinerary, represents the Flight itinerary @see {@link Itinerary}
     * @return ResponseEntity
     */
    @PostMapping(path= "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ItineraryIdentifierResponse> addEmployee(@Valid @RequestBody Itinerary itinerary) 
    {
    	logger.info(String.format("Create Itinerary %s", itinerary.toString()));

    	String flightItineraryId = null;
		try {
			flightItineraryId = flightService.createFlightItinerary(itinerary);
			logger.info(String.format("Itinerary %s was created successfully", flightItineraryId));
		} catch (ItineraryException e) {
			logger.error(ITINERARY_ERROR, e);
			return new ResponseEntity<ItineraryIdentifierResponse>(new ItineraryIdentifierResponse(null, "Itinerary could not be created"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
        return new ResponseEntity<ItineraryIdentifierResponse> (new ItineraryIdentifierResponse(flightItineraryId) ,HttpStatus.CREATED);
    }
}
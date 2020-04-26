package com.api.flights.response;

import com.api.flights.model.Itinerary;

public class ItineraryResponse extends Response {
	private Itinerary itinerary;

	public ItineraryResponse(Itinerary itinerary) {
		super();
		this.itinerary = itinerary;
	}

	public ItineraryResponse(Itinerary itinerary, String error) {
		super(error);
		this.itinerary = itinerary;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

}

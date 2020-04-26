package com.api.flights.response;

public class ItineraryIdentifierResponse extends Response{
	private String itieraryId;

	public ItineraryIdentifierResponse(String itieraryId) {
		this.itieraryId = itieraryId;
	}
	
	public ItineraryIdentifierResponse(String itieraryId, String error) {
		super(error);
		this.itieraryId = itieraryId;
	}

	public String getItieraryId() {
		return itieraryId;
	}

	public void setItieraryId(String itieraryId) {
		this.itieraryId = itieraryId;
	}

}

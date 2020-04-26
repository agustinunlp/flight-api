package com.api.flights.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "itineraries")
public class Itinerary {
	@Id
	private String id;
	private ItineraryDateTime departure;
	private ItineraryDateTime arrival;
	
	@NotNull(message = "Please provide a passenger")
	private Passenger passenger;
	
    @NotNull(message = "Please provide a originCity")
    private String originCity;
    
    @NotNull(message = "Please provide a destinationCity")
    private String destinationCity;

    @NotNull(message = "Please provide a price")
    @DecimalMin("1.00")
    private BigDecimal price;    
    
	public Itinerary() {
		super();
	}
	public Itinerary(ItineraryDateTime departure, ItineraryDateTime arrival, Passenger passenger, String originCity,
			String destinationCity, BigDecimal price) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.passenger = passenger;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.price = price;
	}
	public ItineraryDateTime getDeparture() {
		return departure;
	}
	public void setDeparture(ItineraryDateTime departure) {
		this.departure = departure;
	}
	public ItineraryDateTime getArrival() {
		return arrival;
	}
	public void setArrival(ItineraryDateTime arrival) {
		this.arrival = arrival;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", departure=" + departure.toString() + ", arrival=" + arrival.toString() + ", passenger=" + passenger.toString()
				+ ", originCity=" + originCity + ", destinationCity=" + destinationCity + ", price=" + price + "]";
	}
}

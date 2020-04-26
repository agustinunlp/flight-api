package com.api.flights.model;

import java.util.Date;

public class ItineraryDateTime {
	private Date date;
	private String time;

	public ItineraryDateTime() {
		super();
	}

	public ItineraryDateTime(Date date, String time) {
		super();
		this.date = date;
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ItineraryDateTime [date=" + date + ", time=" + time + "]";
	}
	
	
}

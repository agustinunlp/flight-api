package com.api.flights.model;

public class Passenger {
	private String name;
	private int age;
	private boolean hasLuggage;
	
	public Passenger(String name, int age, boolean hasLuggage) {
		super();
		this.name = name;
		this.age = age;
		this.hasLuggage = hasLuggage;
	}
	
	public Passenger() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isHasLuggage() {
		return hasLuggage;
	}
	public void setHasLuggage(boolean hasLuggage) {
		this.hasLuggage = hasLuggage;
	}

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", age=" + age + ", hasLuggage=" + hasLuggage + "]";
	}
}

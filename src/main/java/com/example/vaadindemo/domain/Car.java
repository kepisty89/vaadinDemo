package com.example.vaadindemo.domain;

/*
 * Car model data.
 */
public class Car {
	
	private String make = "Unknown";	// i.e.: Honda
	private String model = "Unknown";	// i.e.: Civic
	private int yop = 1977;				// Year of production.
	
	public Car(String make, String model, int yop){
		this.setMake(make);
		this.setModel(model);
		this.setYop(yop);
	}
	
	public Car()
	{		
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYop() {
		return yop;
	}

	public void setYop(int yop) {
		this.yop = yop;
	}
	
	public String toString()
	{
		return getMake() + " " 
				+ getModel() 
				+ " " + getYop();
	}
}
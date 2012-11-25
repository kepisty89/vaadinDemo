package com.example.vaadindemo.domain;

/*
 * Car model data.
 */
public class Car {
	
	/*
	 * Fields.
	 */	
	private String make;	// i.e.: Honda
	private String model;	// i.e.: Civic
	private int yop;				// Year of production.
	
	/*
	 * Constructors.
	 */
	public Car(String make, String model, int yop){
		this.setMake(make);
		this.setModel(model);
		this.setYop(yop);
	}
	
	public Car()
	{		
		
	}
	
	/*
	 * Properties.
	 */
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
	
	/*
	 * Public methods.
	 */	
	public String toString()
	{
		return getMake() + " " 
				+ getModel() 
				+ " " + getYop();
	}
}
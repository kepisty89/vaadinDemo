package com.example.vaadindemo.domain;

import java.util.Calendar;
import java.util.Date;

/*
 * Car model data.
 */
public class Car {
	
	/*
	 * Fields.
	 */	
	private String make;	// i.e.: Honda
	private String model;	// i.e.: Civic
	private Date yop;		// Year of production.
	
	/*
	 * Constructors.	
	 */
	public Car(){		
		Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, 1900);
	    calendar.set(Calendar.MONTH, 1);
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    
	    this.make = "Unknown make";
	    this.model = "Unknown model";
	    this.yop = calendar.getTime();
	}
	
	public Car(String make, String model, Date yop){
		super();
		this.make = make;
		this.model = model;
		this.yop = yop;
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

	public Date getYop() {
		return yop;
	}

	public void setYop(Date yop) {
		this.yop = yop;
	}	
}
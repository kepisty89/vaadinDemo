package com.example.vaadindemo.domain;

public class Person {
	
	/*
	 * Fields.
	 */
	private String firstName = "Unknown";
	private String lastName = "Unknown";
	private int birthYear = 1900;

	/*
	 * Constructors.
	 */
	public Person() {
	}

	public Person(String firstName, String lastName, int birthYear) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;		
	}

	/*
	 * Properties.
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getbirthYear() {
		return birthYear;
	}

	public void setbirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/*
	 * Public methods.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + birthYear;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Person other = (Person) obj;
		
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		
		if (birthYear != other.birthYear)
			return false;
		
		return true;
	}
}

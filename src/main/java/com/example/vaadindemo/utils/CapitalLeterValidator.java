package com.example.vaadindemo.utils;

import com.vaadin.data.Validator;

public class CapitalLeterValidator implements Validator {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public CapitalLeterValidator(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
		
		if (!isValid(value))
			throw new InvalidValueException(errorMessage + " must begin with capital letter!");
	}

	@Override
	public boolean isValid(Object value) {
		
		String firstLetter = ((String) value).substring(0, 1);
		String copy = firstLetter;			
		
		if(copy.toUpperCase().equals(firstLetter)){
			return true;
		}
		
		return false;
	}	
}

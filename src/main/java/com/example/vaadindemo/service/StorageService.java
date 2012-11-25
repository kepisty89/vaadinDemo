package com.example.vaadindemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.vaadindemo.domain.Car;
import com.example.vaadindemo.domain.Note;
import com.example.vaadindemo.domain.Person;

public class StorageService {	

	/*
	 * Fields.
	 */	
	private List<Person> personDb = new ArrayList<Person>();	
	private List<Car> carDb = new ArrayList<Car>();
	private List<Note> noteDb = new ArrayList<Note>();

	/*
	 * Public methods for Person.
	 */
	public void addPerson(Person person){
		this.personDb.add(person);
	}

	public void updatePerson(Person person){

		for (Person p : this.personDb) {

			if(p.equals(person)){
				p = person;
			}			
		}
	}

    public void deletePerson(Person person){
    	
    	if(this.personDb.contains(person)){    	
    		this.personDb.remove(person);
    	}
    }

    public List<Person> getAllPersons(){
    	return this.personDb;
    }
          
    
	/*
	 * Public methods for Car.
	 */
    public void addCar(Car car){
    	this.carDb.add(car);
    }
    
    public void updateCar(Car car){
    	
		for (Car c : this.carDb) {

			if(c.equals(car)){
				c = car;
			}			
		}
	}
    
    public void deleteCar(Car car){
    	
    	if(this.carDb.contains(car)){    	
    		this.carDb.remove(car);
    	}
    }

    public List<Car> getAllCars(){
    	return this.carDb;
    }
    
	/*
	 * Public methods for Note.
	 */
    public void addNote(Note note){        
    	this.noteDb.add(note);
    }
    
    public void updateNote(Note note){
    	
		for (Note n : this.noteDb) {

			if(n.equals(note)){
				n = note;
			}			
		}
	}
    
    public void deleteNote(Car car){
    	
    	if(this.carDb.contains(car)){    	
    		this.carDb.remove(car);
    	}
    }

    public List<Car> getAllNotes(){
    	return this.carDb;
    }      
    
    /*
     * Public methods - visible fields. 
     */
	public List<String> getCarVisibleFields(){
		
		List<String> fields = new ArrayList<String>();
		
		fields.add("make");
		fields.add("model");
		fields.add("yop");		
		
		return fields;				
	}
	
	public List<String> getPersonVisibleFields(){
		
		List<String> fields = new ArrayList<String>();
		
		fields.add("firstName");
		fields.add("lastName");
		fields.add("birthYear");		
		
		return fields;				
	}
	
	public List<String> getNoteVisibleFields(){
		
		List<String> fields = new ArrayList<String>();
		
		fields.add("content");
		fields.add("lastModificationDate");
		
		return fields;				
	}

	
}

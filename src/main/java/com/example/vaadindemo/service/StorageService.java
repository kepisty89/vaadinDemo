package com.example.vaadindemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Map<Car, Person> CarPersonDict = new HashMap<Car, Person>();
	
	private Map<Car, Note> CarNoteDict = new HashMap<Car, Note>();
	
	private List<String> currentCarModels = new ArrayList<String>();
	
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
				return;
			}			
		}
		
		this.personDb.add(person);
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
				return;
			}
		}
		
		this.carDb.add(car);
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
				return;
			}			
		}
		
		this.noteDb.add(note);
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
     * Public methods for HashMaps.
     */
    public void updatePersonMatch(Car car, Person person){    	
    	
    	if(this.CarPersonDict.get(car) != null){
    		this.CarPersonDict.remove(car);    		
    		this.CarPersonDict.put(car, person);    		
    	}else{
    		this.CarPersonDict.put(car, person);    		
    	}
    }
    
    public void updateCarPersonMatchKey(Car oldCar, Car newCar){
    	if(this.CarPersonDict.get(oldCar) != null){
    		Person removedPerson = this.CarPersonDict.remove(oldCar);    		    		
    		this.CarPersonDict.put(newCar, removedPerson);    		
    	}
    }
    
    public void removePersonMatch(Car car){    	
		
    	if(this.CarPersonDict.get(car) != null){    		
    		this.deletePerson(this.CarPersonDict.remove(car));	
		}    	    
    }    
    
    public Person getPersonMatchFor(Car car){    	
		
    	Person owner = this.CarPersonDict.get(car);    		
		return owner;
    }
    
public void updateNoteMatch(Car car, Note note){    	
    	
    	if(this.CarNoteDict.get(car) != null){
    		this.CarNoteDict.remove(car);    		
    		this.CarNoteDict.put(car, note);    		
    	}else{
    		this.CarNoteDict.put(car, note);    		
    	}
    }
    
    public void updateCarNoteMatchKey(Car oldCar, Car newCar){
    	if(this.CarNoteDict.get(oldCar) != null){
    		Note removedNote = this.CarNoteDict.remove(oldCar);    		    		
    		this.CarNoteDict.put(newCar, removedNote);    		
    	}
    }
    
    public void removeNoteMatch(Car car){    	
		
    	if(this.CarNoteDict.get(car) != null){
			this.CarNoteDict.remove(car);	
		}    		
    }    
    
    public Note getNoteMatchFor(Car car){    			    
    	
    	Note note = this.CarNoteDict.get(car);
		return note;
    }
    
    /*
     * Public methods - visible fields. 
     */
	public List<String> getCarVisibleFields(){
		
		List<String> fields = new ArrayList<String>();
		
		fields.add("make");
		fields.add("model");
		fields.add("yop");		
		fields.add("hasOwner");
		
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

	/*
	 * Car models - needed for model suggestions.
	 */
	public List<String> getCurrentCarModels() {
		return currentCarModels;
	}

	public void addNewModel(String carModel) {
		this.currentCarModels.add(carModel);
	}

	
}

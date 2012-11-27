package com.example.vaadindemo.factories;

import com.example.vaadindemo.VaadinApp;
import com.example.vaadindemo.domain.Person;
import com.example.vaadindemo.utils.IntegerRangeValidator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class PersonFormWindowFactory extends Form implements FormFieldFactory {
	private static final long serialVersionUID = 1L;
		
	/*
	 * Fields. 
	 */
	VaadinApp vaadinApp;
	BeanItem<Person> beanItem;
	
	/*
	 * Constructor.
	 */
	public PersonFormWindowFactory(BeanItem<Person> beanItem, VaadinApp vaadinApp){		
		
		this.beanItem = beanItem;
		this.vaadinApp = vaadinApp;
		
		// Create and set form layout.
		GridLayout gridLayout = new GridLayout(2,4);
		gridLayout.setSpacing(true);
		setLayout(gridLayout);
		
		setImmediate(true);
		setValidationVisibleOnCommit(true);		

		// 1. Set form factory source.
		setFormFieldFactory(this);
		
		// 2. Set form structure same as BeatItem.
		setItemDataSource(beanItem);
		
		// 3. Set form fields exactly the same as visible fields.
		setVisibleItemProperties(this.vaadinApp.getStorageService().getPersonVisibleFields());			
	}
	
	/*
	 * Form factory override methods.
	 */
	@Override
	protected void attachField(Object propertyId, Field field) {
		
		String propertyName = (String) propertyId;
		GridLayout layout = (GridLayout) getLayout();		
		
		if (propertyName.equals("firstName")) {
			layout.addComponent(field, 0,0);
		} 
		else 
		if (propertyName.equals("lastName")) {			
			layout.addComponent(field, 0,1);
		} 
		else
		if (propertyName.equals("birthYear")) {
			layout.addComponent(field, 0,2);
		}
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		String propertyName = (String) propertyId;
		
		if (propertyName.equals("firstName")) {						
			TextField nameField = new TextField("First name");
			nameField.setInputPrompt("First name...");			
			return nameField;
		} 
		else 
		if (propertyName.equals("lastName")) {			
			TextField lastNameField = new TextField("Last name");
			lastNameField.setInputPrompt("Last name...");
			return lastNameField;			
		} 
		else
		if (propertyName.equals("birthYear")) {
			TextField birthField = new TextField("Birth year");
			birthField.setInputPrompt("1990");
			birthField.addValidator(new IntegerRangeValidator(1900, 2012, "Birth year must be a number ("+ 1900 + " - " + 2012 + ")."));
	        return birthField;
		}
		
		return null;
	}

	public BeanItem<Person> getBeanItem(){
		return this.beanItem;
	}
}

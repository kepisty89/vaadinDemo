package com.example.vaadindemo.utils;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class CheckboxColumnGenerator implements ColumnGenerator {
	
	/*
	 * Fields.
	 */	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Public methods.
	 */
	   
    /**
     * Generates the cell containing the bool value.
     * The column is irrelevant in this use case.
     */
    public Component generateCell(Table source, Object itemId, Object columnId) {
    	
        // Get the object stored in the cell as a property.
        Property prop = source.getItem(itemId).getItemProperty(columnId);
        
        if (prop.getType().equals(Boolean.class)) {
        	
        	// Get property value.
        	Boolean valueToChange = (Boolean)prop.getValue();
        	
        	// Set new label.
            Label label = new Label(valueToChange ? "Yes" : "No");            
            return label;
        }
        
        return null;
    }

}

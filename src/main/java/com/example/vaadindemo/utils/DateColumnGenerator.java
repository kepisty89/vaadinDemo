package com.example.vaadindemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

public class DateColumnGenerator implements ColumnGenerator {
	
	/*
	 * Fields.
	 */	
	private static final long serialVersionUID = 1L;
	
	private String format;
	
	/*
	 * Public methods.
	 */
	
    /**
     * Creates Date value column formatter with the given
     * format string.
     */
    public DateColumnGenerator(String format) {
    	this.format = format;
    }

    /**
     * Generates the cell containing the Date value.
     * The column is irrelevant in this use case.
     */
    public Component generateCell(Table source, Object itemId, Object columnId) {
    	
        // Get the object stored in the cell as a property.
        Property prop = source.getItem(itemId).getItemProperty(columnId);
        
        if (prop.getType().equals(Date.class)) {
        	
        	// Get property value.
        	Date dateToFormat = (Date)prop.getValue();
        	
        	// Set new label.
            Label label = new Label(new SimpleDateFormat(format).format(dateToFormat).toString());
            
            return label;
        }
        
        return null;
    }

}

package com.example.vaadindemo;

import java.util.ArrayList;

import com.example.vaadindemo.domain.Car;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VaadinApp extends Application {

	private static final long serialVersionUID = 1L;
	
	Window mainWindow = new Window();
	Label helloLabel = new Label();
	TextField helloText = new TextField();
	Button button = new Button("Show");
	Button addButton = new Button("Add new");
	Form carForm = new Form();
	
	final HorizontalLayout hl = new HorizontalLayout();

	@Override
	public void init() {
		
		// Create car bean as data source.
		Car carBean = new Car();
		
		// Bean that holds data.
		final BeanItem<Car> carItem = new BeanItem<Car>(carBean);		
		
		// Bind data with form.
		carForm.setItemDataSource(carItem);
		carForm.setImmediate(true);
		
		// Fake Car object from data base.
		Car fromDb = new Car();
		fromDb.setMake("Syrena");
		fromDb.setModel("105");
		fromDb.setYop(1945);
		
		// Change existing data source.
		carBean.setMake(fromDb.getMake());
		carBean.setModel(fromDb.getModel());
				
		button.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				
				// Get data from data holder.
				Car newCar = carItem.getBean();							
				mainWindow.showNotification(newCar.toString());				
			}
		});
		
		hl.addComponent(new Label("Hello Wordl"));
		hl.addComponent(carForm);
		hl.addComponent(button);
				
		/*
		 * Containers. * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 */
		
		// First type.
		final BeanItemContainer<Car> container = new BeanItemContainer<Car>(Car.class);
				
		// Second type.
		// ItemC
		
		Table table = new Table("Samochody", container);
		
		final ArrayList<Car> cars = new ArrayList<Car>();
		
		cars.add(new Car("Honda", "Civic", 2012));
		cars.add(new Car("Ford", "Mondeo", 2003));
		cars.add(new Car("Fiat", "Panda", 1996));
		cars.add(new Car("FSO", "125p", 1956));		
		
		container.addAll(cars);
		
		/*
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
		 */
		
		hl.addComponent(table);
		
		table.setSelectable(true);		
		table.setImmediate(true);
		
		table.addListener(new Table.ValueChangeListener() {
			
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				mainWindow.showNotification("Wybrano: " + event.getProperty().toString());				
			}
		});
		
		table.addListener(new ItemClickListener() {		
			private static final long serialVersionUID = 1L;

			public void itemClick(ItemClickEvent event) {			
			
			}
		});
		
		
		/*
		 * Add button. 
		 */
		addButton.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				Car carToAdd = new Car("Samochod", "Marka", 9999);
				container.addBean(carToAdd);
			}
		});
		
		hl.addComponent(addButton);		
		mainWindow.addComponent(hl);
		setMainWindow(mainWindow);
	}
}

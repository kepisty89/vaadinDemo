package com.example.vaadindemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.example.vaadindemo.domain.Car;
import com.example.vaadindemo.domain.Person;
import com.example.vaadindemo.factories.CarFormWindowFactory;
import com.example.vaadindemo.service.StorageService;
import com.example.vaadindemo.utils.CheckboxColumnGenerator;
import com.example.vaadindemo.utils.DateColumnGenerator;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("unused")
public class VaadinApp extends Application {

	private static final long serialVersionUID = 1L;
	
	/*
	 * Fields.
	 */
	private StorageService storageService;
	
	private BeanItemContainer<Car> carContainer = new BeanItemContainer<Car>(Car.class);
	private BeanItemContainer<Person> personContainer = new BeanItemContainer<Person>(Person.class);	
	
	private final Table carTable = new Table("Cars", carContainer);
	private final Table personTable = new Table("People", personContainer);	
	
	private Button addButton = new Button("Add new car");	
	private Button editButton = new Button("Edit selected car");
	private Button removeButton = new Button("Remove selected car");
	
	private Window mainWindow = new Window();
	private Window addCarFormWindow;
	
	/*
	 * Finals.
	 */
	final HorizontalLayout horizontalLayout = new HorizontalLayout();
	
	/*
	 * Constructor.
	 */
	public VaadinApp(StorageService service) {
		this.storageService = service;
	}

	/*
	 * VaadinApp main initialization.
	 */
	@Override
	public void init() {		
		
		// Fake database methods.
		fillStorageService();
		updateContainers();		
		
		// Car table settings.
		carTable.setSelectable(true);
		carTable.setHeight("600px");
		carTable.setWidth("400px");
		carTable.setColumnHeader("make", "Make");
		carTable.setColumnHeader("model", "Model");
		carTable.setColumnHeader("yop", "Registration date");
		carTable.setColumnHeader("hasOwner", "Owner registered");
		carTable.addGeneratedColumn("yop", new DateColumnGenerator("dd-MMMM-yyyy"));
		carTable.addGeneratedColumn("hasOwner", new CheckboxColumnGenerator());
		carTable.setColumnWidth("yop", 102);
		carTable.setColumnWidth("hasOwner", 105);
		
		// Set columns order.
		Object[] carColumns = new Object[]{"make", "model", "yop", "hasOwner"};		
		carTable.setVisibleColumns(carColumns);			
		
		// Person table settings.
		personTable.setSelectable(true);
		personTable.setHeight("600px");
		personTable.setWidth("400px");		
		personTable.setColumnHeader("firstName", "Name");
		personTable.setColumnHeader("lastName", "Last name");
		personTable.setColumnHeader("birthYear", "Year of birth");
		personTable.setColumnWidth("birthYear", 90);
		
		// Set columns order.
		Object[] personColumns = new Object[]{"firstName", "lastName", "birthYear"};		
		personTable.setVisibleColumns(personColumns);
		
		// Buttons logic.
		addButton.addListener(getAddButtonListener());		
		editButton.addListener(getEditButtonListener());		
		removeButton.addListener(getRemoveButtonListener());
		
		addButton.setIcon(new ThemeResource("../runo/icons/16/document-add.png"));
		editButton.setIcon(new ThemeResource("../runo/icons/16/document.png"));
		removeButton.setIcon(new ThemeResource("../runo/icons/16/document-delete.png"));
		
		// Main window layout settings.
		HorizontalLayout tablesLayout = new HorizontalLayout();
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		VerticalLayout windowLayout = new VerticalLayout();		
		
		tablesLayout.setSpacing(true);
		tablesLayout.addComponent(carTable);
		tablesLayout.addComponent(personTable);
		
		buttonsLayout.setSpacing(true);
		buttonsLayout.setMargin(true);
		buttonsLayout.addComponent(addButton);
		buttonsLayout.addComponent(editButton);
		buttonsLayout.addComponent(removeButton);						
		
		windowLayout.setSpacing(true);
		windowLayout.setMargin(true);		
		
		Embedded logoImage = new Embedded("", new ExternalResource("http://www.enterprisecarshare.com/themes/ecs/img/slide-2.png.pagespeed.ce.Qm3lFWoKAm.png"));		
		
		windowLayout.addComponent(logoImage);
		windowLayout.addComponent(tablesLayout);
		windowLayout.addComponent(buttonsLayout);
				
		windowLayout.setComponentAlignment(logoImage, Alignment.TOP_CENTER);
		windowLayout.setComponentAlignment(tablesLayout, Alignment.TOP_CENTER);		
		windowLayout.setComponentAlignment(buttonsLayout, Alignment.TOP_CENTER);
		
		mainWindow.addComponent(windowLayout);
		
		setMainWindow(mainWindow);
	}

	
	/*
	 * Public methods.
	 */
	public StorageService getStorageService() {
		return this.storageService;
	}
	
	public void updateContainers(){
		carContainer.removeAllItems();
		carContainer.addAll(storageService.getAllCars());
		
		personContainer.removeAllItems();
		personContainer.addAll(storageService.getAllPersons());
	}
	
	/*
	 * Private methods.
	 */
	private void fillStorageService(){
		
		Car car1 = new Car("Fiat", "Punto", new Date(), false);
		Car car2 = new Car("Ford", "Mondeo", new Date(), false);				
		Car car3 = new Car("Nissan", "Quashkai", new Date(), false);				
		Car car4 = new Car("Honda", "Civic VIII", new Date(), true);
		Car car5 = new Car("Nissan", "Micra", new Date(), true);
		Car car6 = new Car("Toyota", "Corolla", new Date(), false);
		Car car7 = new Car("Peugeot", "308", new Date(), true);
		
		Person person1 = new Person("Jacek", "Krajewski", 1987);
		Person person2 = new Person("Jan", "Kowalski", 1985);
		Person person3 = new Person("Adam", "Jabłko", 1968);
				
		storageService.addCar(car1);
		storageService.addCar(car2);
		storageService.addCar(car3);
		storageService.addCar(car4);
		storageService.addCar(car5);
		storageService.addCar(car6);
		storageService.addCar(car7);
		
		storageService.addPerson(person1);
		storageService.addPerson(person2);
		storageService.addPerson(person3);
		
		storageService.updatePersonMatch(car4, person1);
		storageService.updatePersonMatch(car5, person2);
		storageService.updatePersonMatch(car7, person3);
	}	
	
	// Listeners.
	private ClickListener getAddButtonListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void buttonClick(ClickEvent event) {

				if (addCarFormWindow != null) {
					mainWindow.removeWindow(addCarFormWindow);
				}

				BeanItem<Car> beanCarItem = new BeanItem<Car>(new Car());
				BeanItem<Person> beanPersonItem = new BeanItem<Person>(new Person());
				CarFormWindowFactory carFormFactory = new CarFormWindowFactory(beanCarItem, beanPersonItem, VaadinApp.this);

				addCarFormWindow = carFormFactory.createWindow("Adding new car");
				mainWindow.addWindow(addCarFormWindow);
			}
		};
	}
		
	private ClickListener getEditButtonListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void buttonClick(ClickEvent event) {

				if(carTable.getValue() != null){
					
					// Get selected Car element from table.
					Car carToEdit = (Car) carTable.getValue();
					BeanItem<Car> beanCarItem = new BeanItem<Car>(carToEdit);
					BeanItem<Person> beanPersonItem;
					
					// Check car owner.
					if (carToEdit.getHasOwner()){
						Person owner = storageService.getPersonMatchFor(carToEdit);
						beanPersonItem = new BeanItem<Person>(owner);
					}else{
						beanPersonItem = new BeanItem<Person>(new Person());
					}
					
					CarFormWindowFactory carFormFactory = new CarFormWindowFactory(beanCarItem, beanPersonItem, VaadinApp.this);
					
					if (addCarFormWindow != null) {
						mainWindow.removeWindow(addCarFormWindow);
					}
					
					addCarFormWindow = carFormFactory.createWindow("You are editting car - " 
					+ carToEdit.getMake() + " " + carToEdit.getModel() + "   " + new SimpleDateFormat("MM/yyyy").format(carToEdit.getYop()).toString());
					mainWindow.addWindow(addCarFormWindow);				
				}else{
					mainWindow.showNotification("Select car to edit!");
				}
			}
		};
	}
	
	private ClickListener getRemoveButtonListener() {
		return new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void buttonClick(ClickEvent event) {

				if(carTable.getValue() != null){
					
					Car carToRemove = (Car) carTable.getValue();

					// Remove owner if exist.
					if (carToRemove.getHasOwner()){
						Person owner = storageService.getPersonMatchFor(carToRemove);
						storageService.removePersonMatch(carToRemove);
						storageService.deleteCar(carToRemove);
						storageService.deletePerson(owner);
					}else{
						storageService.deleteCar(carToRemove);
					}
					
					updateContainers();
					carTable.select(null);
					
					mainWindow.showNotification("Car has been removed!");
									
				}else{
					mainWindow.showNotification("Select car to remove!");
				}
			}
		};
	}
}

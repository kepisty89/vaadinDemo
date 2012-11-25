package com.example.vaadindemo;

import com.example.vaadindemo.domain.Car;
import com.example.vaadindemo.factories.CarFormWindowFactory;
import com.example.vaadindemo.service.StorageService;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class VaadinApp extends Application {

	private static final long serialVersionUID = 1L;
	
	/*
	 * Fields.
	 */
	private StorageService storageService;
	
	private BeanItemContainer<Car> carContainer = new BeanItemContainer<Car>(Car.class);
	
	private Table carTable = new Table("Cars", carContainer);		
	private Button addButton = new Button("Add new car");
	
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
		
		fillStorageService();
		updateCarContainer();
		
		mainWindow.addComponent(carTable);
		
		carTable.addListener(new Table.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				if (carTable.getValue() != null) {
					mainWindow.showNotification("" + carTable.getValue());
				}
			}
		});
		addButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
			
			public void buttonClick(ClickEvent event) {

				if (addCarFormWindow != null) {
					mainWindow.removeWindow(addCarFormWindow);
				}

				BeanItem<Car> beanCarItem = new BeanItem<Car>(new Car());
				CarFormWindowFactory carFormFactory  = new CarFormWindowFactory(beanCarItem, VaadinApp.this);

				addCarFormWindow = carFormFactory.createWindow();
				mainWindow.addWindow(addCarFormWindow);
			}
		});
		
		mainWindow.addComponent(addButton);
		setMainWindow(mainWindow);
	}
	
	/*
	 * Public methods.
	 */
	public StorageService getStorageService() {
		return this.storageService;
	}
	
	public void updateCarContainer(){
		carContainer.removeAllItems();		
		carContainer.addAll(storageService.getAllCars());				
	}
	
	/*
	 * Private methods.
	 */
	private void fillStorageService(){
		
		Car car1 = new Car();		
		car1.setMake("Fiat");
		car1.setModel("Punto");
		car1.setYop(1999);		

		Car car2 = new Car();		
		car2.setMake("Ford");
		car2.setModel("Mondeo");
		car2.setYop(2004);		
		
		Car car3 = new Car();
		car3.setMake("Syrena");
		car3.setModel("105");
		car3.setYop(1945);
		
		storageService.addCar(car1);
		storageService.addCar(car2);
		storageService.addCar(car3);
	}	
}

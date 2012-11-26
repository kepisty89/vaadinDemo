package com.example.vaadindemo;

import java.util.Date;

import utils.DateColumnGenerator;

import com.example.vaadindemo.domain.Car;
import com.example.vaadindemo.domain.Person;
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
//	{
//		
//		@Override
//        protected String formatPropertyValue(Object rowId, Object colId,
//                Property property) {
//            Object v = property.getValue();
//            if (v instanceof Date) {
//                Date dateValue = (Date) v;
//                return new SimpleDateFormat("yyyy-MMMM-dd").format(dateValue);
//            }
//            return super.formatPropertyValue(rowId, colId, property);
//        }
//	};	
		
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
		
		carTable.setSelectable(true);
		carTable.setColumnHeader("make", "Make");
		carTable.setColumnHeader("model", "Model");
		carTable.setColumnHeader("yop", "Registration date");
		carTable.addGeneratedColumn("yop", new DateColumnGenerator("dd-MMMM-yyyy"));
		
		carTable.addListener(new Table.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (carTable.getValue() != null) {
					mainWindow.showNotification("" + carTable.getValue());
				}
			}
		});
		
		addButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void buttonClick(ClickEvent event) {

				if (addCarFormWindow != null) {
					mainWindow.removeWindow(addCarFormWindow);
				}

				BeanItem<Car> beanCarItem = new BeanItem<Car>(new Car());
				CarFormWindowFactory carFormFactory = new CarFormWindowFactory(beanCarItem, VaadinApp.this);

				addCarFormWindow = carFormFactory.createWindow();
				mainWindow.addWindow(addCarFormWindow);
			}
		});
		
		mainWindow.addComponent(carTable);	
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
		
		Car car1 = new Car("Fiat", "Punto", new Date());				
		Car car2 = new Car("Ford", "Mondeo", new Date());				
		Car car3 = new Car("Nissan", "Quashkai", new Date());				
		Car car4 = new Car("Honda", "Civic", new Date());
		
		Person person1 = new Person("Lukasz", "Kepinski", 1989);
		
		
		storageService.addCar(car1);
		storageService.addCar(car2);
		storageService.addCar(car3);
		
		storageService.addPerson(person1);
		
		storageService.createMatch(car4, person1);		
	}	
}

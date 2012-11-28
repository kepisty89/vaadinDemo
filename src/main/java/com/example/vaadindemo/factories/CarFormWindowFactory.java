package com.example.vaadindemo.factories;

import com.example.vaadindemo.VaadinApp;
import com.example.vaadindemo.domain.Car;
import com.example.vaadindemo.domain.Note;
import com.example.vaadindemo.domain.Person;
import com.example.vaadindemo.utils.CapitalLeterValidator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractSelect.NewItemHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CarFormWindowFactory extends Form implements FormFieldFactory {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Constants.
	 */
	String[] MAKES = { "ABT", "AC Schnitzer", "Acura", "Alfa Romeo", "Alpina", "Arrinera", "Artega", "Ascari", "Aston Martin", "Audi",
		"BMW", "Bentley", "Bertone", "Brabus", "Breckland", "Bugatti", "Buick", "Cadillac", "Caparo", "Carlsson", "Caterham", "Chevrolet",
		"Chrysler", "Citroen", "Covini", "Dacia", "Daewoo", "Daihatsu", "Daimler", "De Tomaso", "Devon", "Dodge", "Donkervoort", "EDAG", "Edo",
		"Elfin", "Eterniti", "FM Auto", "FPV", "Farbio", "Ferrari", "Fiat", "Fisker", "Ford", "GM", "GMC", "GTA", "Gordon Murray", "Gumpert", "HSV",
		"Hamann", "Hennessey", "Holden", "Honda", "Hummer", "Hyundai", "Infiniti", "Isuzu", "Italdesign", "Iveco", "Jaguar", "Jeep", "KTM", "Kia", 
		"Kleemann", "Koenigsegg", "LCC", "Lada", "Lamborghini", "Lancia", "Land Rover", "Leblanc", "Lexus", "Lincoln", "Lobini", "Loremo", "MG",
		"Mansory", "Marcos", "Maserati", "Maybach", "Mazda", "Mazel", "McLaren", "Mercedes-Benz", "Mercury", "Mindset", "Mini", "Mitsubishi",
		"Mitsuoka", "Morgan", "Nismo", "Nissan", "Noble", "ORCA", "Oldsmobile", "Opel", "PGO", "Pagani", "Panoz", "Peugeot", "Pininfarina",
		"Plymouth", "Pontiac", "Porsche", "Proton", "Renault", "Rinspeed", "Rolls-Royce", "Rover", "Saab", "Saleen", "Saturn", "Scion", "Seat",
		"Singer", "Skoda", "Smart", "Spada", "Spyker", "SsangYong", "Startech", "Stola", "Strosek", "StudioTorino", "Subaru", "Suzuki", "TVR",
		"TechArt", "Tesla", "Think", "Toyota", "Tramontana", "Valmet", "Vauxhall", "Venturi", "Volkswagen", "Volvo", "Wald", "Wiesmann", "Yes",
		"Zagato", "Zenvo" };
	
	/*
	 * Fields. 
	 */
	VaadinApp vaadinApp;
	BeanItem<Car> carBeanItem;
	BeanItem<Car> oldCarBeanItem;
	BeanItem<Person> personBeanItem;	
	PersonFormWindowFactory pfwf;
	
	// Window that will be created.
	Window window;	
	
	// Additional buttons.	
	Button saveButton = new Button("Save");
	Button exitButton = new Button("Cancel");
	final RichTextArea richTextArea = new RichTextArea();
	final TabSheet tabSheet = new TabSheet();
	VerticalLayout tabLayout1 = new VerticalLayout();
	final VerticalLayout tabLayout2 = new VerticalLayout();
	VerticalLayout tabLayout3 = new VerticalLayout();
	
	/*
	 * Constructor.
	 */
	public CarFormWindowFactory(BeanItem<Car> carBeanItem, BeanItem<Person> personBeanItem, VaadinApp vaadinApp){		
		
		this.carBeanItem = carBeanItem;
		this.oldCarBeanItem = carBeanItem;
		this.personBeanItem = personBeanItem;		
		this.vaadinApp = vaadinApp;
		
		this.pfwf = new PersonFormWindowFactory(this.personBeanItem, this.vaadinApp);		
		
		// Create and set form layout.
		GridLayout gridLayout = new GridLayout(2,5);
		gridLayout.setSpacing(true);
		setLayout(gridLayout);
		
		setImmediate(true);
		setValidationVisibleOnCommit(true);		

		// 1. Set form factory source.
		setFormFieldFactory(this);
		
		// 2. Set form structure same as BeatItem.
		setItemDataSource(carBeanItem);
		
		// 3. Set form fields exactly the same as visible fields.
		setVisibleItemProperties(this.vaadinApp.getStorageService().getCarVisibleFields());
		
		// Bind buttons with actions.
		saveButton.addListener(ClickEvent.class, this, "saveButtonAction");
		exitButton.addListener(ClickEvent.class, this, "exitButtonAction");
		
		// Set buttons layout.
		saveButton.setIcon(new ThemeResource("../runo/icons/16/ok.png"));
		exitButton.setIcon(new ThemeResource("../runo/icons/16/cancel.png"));		
	}
	
	/*
	 * Form factory override methods.
	 */
	@Override
	protected void attachField(Object propertyId, Field field) {
		
		String propertyName = (String) propertyId;
		GridLayout layout = (GridLayout) getLayout();		
		
		if (propertyName.equals("make")) {
			layout.addComponent(field, 0,0);
		} 
		else 
		if (propertyName.equals("model")) {			
			layout.addComponent(field, 0,1);
		} 
		else
		if (propertyName.equals("yop")) {
			layout.addComponent(field, 0,2);
		}
		else
		if(propertyName.equals("hasOwner")){
			layout.addComponent(field, 0,3);
		}			
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		String propertyName = (String) propertyId;
		
		if (propertyName.equals("make")) {
			
			final ComboBox makeComboBox = new ComboBox("Choose make");
			makeComboBox.setInputPrompt("Start typing");
			makeComboBox.setNewItemsAllowed(true);
			makeComboBox.setRequired(true);
			makeComboBox.addValidator(new CapitalLeterValidator("Car make"));

			for(String make : MAKES) {
				makeComboBox.addItem(make);
			}
			
			return makeComboBox;
		} 
		else 
		if (propertyName.equals("model")) {
			
			final ComboBox modelComboBox = new ComboBox("Type model");								
			modelComboBox.setInputPrompt("Car model");
			modelComboBox.setNewItemsAllowed(true);
			modelComboBox.setRequired(true);			
			modelComboBox.addValidator(new CapitalLeterValidator("Car model"));			
			modelComboBox.setNewItemHandler(modelComboNewItemHandler(modelComboBox));
			
			for(String model : this.vaadinApp.getStorageService().getCurrentCarModels()) {
				modelComboBox.addItem(model);
			}
			
			return modelComboBox;
		} 
		else
		if (propertyName.equals("yop")) {

			final PopupDateField popupDate = new PopupDateField("Registration date");
								
			popupDate.setInputPrompt("Registration date");
			popupDate.setResolution(PopupDateField.RESOLUTION_DAY);
	        popupDate.setImmediate(true);
	        popupDate.setRequired(true);
	        
	        return popupDate;
		}
		else
		if(propertyName.equals("hasOwner")){
			
			final CheckBox ownerCheckBox = new CheckBox("This car have owner");
		
			ownerCheckBox.setImmediate(true);
			ownerCheckBox.addListener(ClickEvent.class, this, "ownerCheckBoxAction");
			
			return ownerCheckBox;
		}
		
		return null;
	}

	private NewItemHandler modelComboNewItemHandler(final ComboBox modelComboBox) {
		return new NewItemHandler() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void addNewItem(String newItemCaption) {									       				       
		        modelComboBox.addItem(newItemCaption);
		        modelComboBox.setItemCaption(newItemCaption, newItemCaption);
		        modelComboBox.select(newItemCaption);
									
				vaadinApp.getStorageService().addNewModel(newItemCaption);
			}				
		};
	}
	
	public Window createWindow(String caption){
		
		// Car tab content.        
        this.tabLayout1.setMargin(true);
        this.tabLayout1.addComponent(this);
        this.tabLayout1.setComponentAlignment(this, Alignment.MIDDLE_RIGHT);    
        this.tabLayout1.setSpacing(true);
        
        // Person tab content.        
        this.tabLayout2.setMargin(true);                       
        this.tabLayout2.addComponent(pfwf);
        this.tabLayout2.setComponentAlignment(pfwf, Alignment.MIDDLE_RIGHT);           
        this.tabLayout2.setSpacing(true);
        
        // Notes tab content.        
        this.tabLayout3.setMargin(true, false, false, false);
        this.tabLayout3.setSpacing(false);
                
        richTextArea.setHeight("230px");
        richTextArea.setWidth("350px");        
        richTextArea.setValue(this.getCarNote().getContent());
        
        this.tabLayout3.addComponent(richTextArea);
        this.tabLayout3.setComponentAlignment(richTextArea, Alignment.MIDDLE_CENTER);

        // TabSheet itself.                
        tabSheet.setImmediate(true);
        tabSheet.setHeight("300px");
        tabSheet.setWidth("400px");        
        
        tabSheet.addTab(this.tabLayout1, "Car data");
        tabSheet.addTab(this.tabLayout2, "Owner data");
        tabSheet.addTab(this.tabLayout3, "Additional notes");
        tabSheet.getTab(1).setEnabled(this.carBeanItem.getBean().getHasOwner());               
		
		this.window = new Window();
		
		// Buttons layout settings.
		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setSpacing(true);
		buttonsLayout.setMargin(true);		
		saveButton.setWidth("80px");
		exitButton.setWidth("80px");
		buttonsLayout.addComponent(saveButton);
		buttonsLayout.addComponent(exitButton);
		
		// Window layout settings.
		VerticalLayout windowLayout = new VerticalLayout();
		windowLayout.addComponent(tabSheet);
		windowLayout.addComponent(buttonsLayout);
		windowLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);
		
		// Window settings.
		this.window.setModal(true);
		this.window.setClosable(false);
		this.window.setResizable(false);		
		this.window.setCaption(caption);
		this.window.addComponent(windowLayout);	
		
		((VerticalLayout) this.window.getContent()).setSizeUndefined();
		((VerticalLayout) this.window.getContent()).setMargin(true);
		((VerticalLayout) this.window.getContent()).setSpacing(true);

		return this.window;
	}

	public void saveButtonAction(ClickEvent event){
		
		this.vaadinApp.getStorageService().updateCar(this.carBeanItem.getBean());
		
		Boolean hasOwner = (Boolean)this.getField("hasOwner").getValue();
		
		if(hasOwner){
			Person owner = this.pfwf.getBeanItem().getBean();
			this.vaadinApp.getStorageService().updatePerson(owner);
			
			this.vaadinApp.getStorageService().updateCarPersonMatchKey(this.oldCarBeanItem.getBean(), this.carBeanItem.getBean());
			this.vaadinApp.getStorageService().updatePersonMatch(this.carBeanItem.getBean(), owner);
		}else{			
			this.vaadinApp.getStorageService().removePersonMatch(this.carBeanItem.getBean());			
		}
		
		this.saveNote();
		
		commit();
		this.closeFormWindow();
		this.vaadinApp.updateContainers();
	}
	
	public void exitButtonAction(ClickEvent event){
		this.closeFormWindow();
		this.vaadinApp.updateContainers();	
	}
	
	public void ownerCheckBoxAction(ClickEvent event) {
        boolean enabled = event.getButton().booleanValue();
        this.tabSheet.getTab(1).setEnabled(enabled);
    }	
	
	/*
	 * Private methods.
	 */
	private void closeFormWindow(){
		Window mainWindow = window.getParent();
		mainWindow.removeWindow(window);
	}
	
	private void saveNote(){
		
		Note note = new Note(this.richTextArea.getValue().toString());
		
		this.vaadinApp.getStorageService().updateNote(note);
		
		this.vaadinApp.getStorageService().updateCarNoteMatchKey(this.oldCarBeanItem.getBean(), this.carBeanItem.getBean());
		this.vaadinApp.getStorageService().updateNoteMatch(this.carBeanItem.getBean(), note);
		
	}
	
	private Note getCarNote(){
		
		Note note = this.vaadinApp.getStorageService().getNoteMatchFor(this.carBeanItem.getBean());
		
		if(note == null){
			note = new Note("");
		}
		
		return note;
	}
}

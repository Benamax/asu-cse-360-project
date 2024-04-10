package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PatientVisitsView extends View {
	Label lblTitle;
	
	GridPane calendarGrid;
	
	Label lblInfo;
	TextField tfInfo;
	
	Button btnNew;
	Button btnEdit;
	Button btnExit;
	
	Button btnAddEvent;
	Button btnEditEvent;
	
	ObservableList<String> appointments;
	ListView<String> listOfAppointments;
	
	TextField inputTField;
		
	private Perspective perspective;
	public enum Perspective {
		PATIENT,
		STAFF
	}
	
	
	@Override
	public Parent generate() {
		appointments = FXCollections.observableArrayList();
		
		StackPane stackPane = new StackPane();
		stackPane.setMinSize(1100, 700);
		stackPane.setPrefSize(1100, 700);
		stackPane.setMaxSize(1100, 700);
		stackPane.setPadding(new Insets(50));
		
		lblTitle = new Label("Prior Visits");
		lblTitle.setFont(Font.font("Arial", 36));
		
		VBox infoBox = new VBox(5);
		lblInfo = new Label("Information");
		lblInfo.setFont(Font.font("Arial", 26));
		tfInfo = new TextField();
		tfInfo.setEditable(false);
		tfInfo.setMinSize(250, 300);
		tfInfo.setPrefSize(250, 300);
		tfInfo.setMaxSize(250, 300);
		infoBox.getChildren().addAll(lblInfo, tfInfo);
		infoBox.setAlignment(Pos.CENTER_RIGHT);
				
		inputTField = new TextField();
		inputTField.setMaxWidth(750);
		inputTField.setMaxHeight(50);
		
		listOfAppointments = new ListView<>(appointments);
		listOfAppointments.setMaxSize(750, 350);
		
		btnNew = CommonControls.createButton("New Visit", Views.EDIT_VISIT);
		btnEdit = CommonControls.createButton("Edit Visit", Views.EDIT_VISIT);
		btnExit = CommonControls.createButton("Back", Views.PATIENT_PORTAL);
		
		btnAddEvent = CommonControls.createButton("Add Event", e -> addEvent());
		btnEditEvent = CommonControls.createButton("Edit Event", e -> editEvent());
		
		stackPane.getChildren().addAll(lblTitle, btnAddEvent, btnEditEvent, btnExit, inputTField,listOfAppointments);
		StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
		StackPane.setAlignment(btnAddEvent, Pos.CENTER_LEFT);
		StackPane.setAlignment(btnEditEvent, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(btnExit, Pos.TOP_LEFT);
		StackPane.setAlignment(inputTField, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(listOfAppointments, Pos.CENTER_RIGHT);

	
		stackPane.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #ffe485 30%, #ffe5c4 47%);");
		
		root = stackPane;
		return root;
	}

	@Override
	public void reset() {

	}
	
	private void addEvent() {
		String newEvent = inputTField.getText().trim();
		if(!inputTField.getText().isEmpty()){
			appointments.add(newEvent);
			inputTField.clear();
		}
	}
	
	private void editEvent() {
		String selectedEvent = listOfAppointments.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			int selectedEventIndex = listOfAppointments.getSelectionModel().getSelectedIndex();
			String editedEvent = inputTField.getText().trim();
			appointments.set(selectedEventIndex, editedEvent);
			inputTField.clear();
		}
	}
	
	
	public void changePerspective(Perspective p) {
		if(p == Perspective.PATIENT) {
			tfInfo.setMinHeight(300);
			tfInfo.setPrefHeight(300);
			tfInfo.setMaxHeight(300);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_PORTAL));
			
			// TODO: Remove buttons
		} else if(p == Perspective.STAFF){
			tfInfo.setMinHeight(150);
			tfInfo.setPrefHeight(150);
			tfInfo.setMaxHeight(150);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_INFO));
			
			// TODO: Add buttons
		}
		
		perspective = p;
	}

	public class DatePane {
		StackPane stackPane;
		Label lblDate;
		
		public DatePane(int size, String text) {
			stackPane = new StackPane();
			stackPane.setMinSize(size, size);
			lblDate = new Label(text);
			
			stackPane.getChildren().add(lblDate);
		}
		
		public StackPane getPane() {
			return stackPane;
		}
	}
}

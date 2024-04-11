package application;

import java.util.List;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PatientVisitsView extends View {
	StackPane stackPane;
	Label lblTitle;
	
	GridPane calendarGrid;
	
	VBox infoBox;
	Label lblInfo;
	TextArea tfInfo;
	
	Button btnAddVisit;
	Button btnEditVisit;
	Button btnExit;
	
	ObservableList<String> visits;
	ListView<String> visitListView;
	
	TextField inputTField;
		
	private Perspective perspective;
	public enum Perspective {
		PATIENT,
		STAFF
	}
	
	
	@Override
	public Parent generate() {
		visits = FXCollections.observableArrayList();
		
		stackPane = new StackPane();
		stackPane.setMinSize(1100, 700);
		stackPane.setPrefSize(1100, 700);
		stackPane.setMaxSize(1100, 700);
		stackPane.setPadding(new Insets(50));
		
		lblTitle = new Label("Prior Visits");
		lblTitle.setFont(Font.font("Arial", 36));
		
		infoBox = new VBox(5);
		lblInfo = new Label("Information");
		lblInfo.setFont(Font.font("Arial", 26));
		tfInfo = new TextArea();
		tfInfo.setEditable(false);
		tfInfo.setWrapText(true);
		tfInfo.setMinSize(200, 500);
		tfInfo.setPrefSize(200, 500);
		tfInfo.setMaxSize(200, 500);
		infoBox.getChildren().addAll(lblInfo, tfInfo);
		infoBox.setAlignment(Pos.CENTER_LEFT);
				
		inputTField = new TextField();
		inputTField.setMaxWidth(750);
		inputTField.setMaxHeight(50);
		
		visitListView = new ListView<>(visits);
		visitListView.setMaxSize(750, 350);
		visitListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				List<Visit> allVisits = VisitSystem.loadVisits(PatientSystem.currentPatient.getPatientID());
				int index = visitListView.getSelectionModel().getSelectedIndex();
				Visit selectedVisit = allVisits.get(index);
				tfInfo.setText(String.format("Reason for Appointment:\n%s\n\nDoctor's Notes:\n%s", selectedVisit.reasonFor, selectedVisit.notes));
			}
	    });
		
		btnExit = CommonControls.createButton("Back", Views.PATIENT_PORTAL);
		btnAddVisit = CommonControls.createButton("Add Visit", e -> addVisit());
		btnEditVisit = CommonControls.createButton("Edit Visit", e -> editVisit());
		
		stackPane.getChildren().addAll(lblTitle, btnExit, inputTField, visitListView);
		StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
		StackPane.setAlignment(btnAddVisit, Pos.CENTER_LEFT);
		StackPane.setAlignment(btnEditVisit, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(btnExit, Pos.TOP_LEFT);
		StackPane.setAlignment(inputTField, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(visitListView, Pos.CENTER_RIGHT);
		StackPane.setAlignment(infoBox, Pos.CENTER_LEFT);

	
		stackPane.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #ffe485 30%, #ffe5c4 47%);");
		
		root = stackPane;
		return root;
	}
	
	@Override
	public void onEnter() {
		loadVisits();
	}

	@Override
	public void reset() {
		
	}
	
	private void loadVisits() {
		System.out.println("Loading visits from file system");
		List<String> visitNames = VisitSystem.loadVisitNames(PatientSystem.currentPatient.getPatientID());
		if(visitNames.size() > 0) {
			System.out.println("Found visits, adding to view");
			visits = FXCollections.observableArrayList(visitNames);
			for(String visit : visits) {
				System.out.println(visit);
			}
			visitListView.setItems(visits);
			visitListView.refresh();
		}
	}
	
	private void addVisit() {
		String newEvent = inputTField.getText().trim();
		if(!inputTField.getText().isEmpty()) {
			Visit newVisit = new Visit(newEvent);
			newVisit.patientID = PatientSystem.currentPatient.getPatientID();
			newVisit.save();
			
			loadVisits();
			inputTField.clear();
		}
	}
	
	private void editVisit() {
		String selectedEvent = visitListView.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			int selectedEventIndex = visitListView.getSelectionModel().getSelectedIndex();
			List<Visit> visitObjs = VisitSystem.loadVisits(PatientSystem.currentPatient.getPatientID());
			Visit visitToEdit = visitObjs.get(selectedEventIndex);
			PatientSystem.currentVisit = visitToEdit;
			ViewController.switchView(Views.EDIT_VISIT);
			
			//String editedEvent = inputTField.getText().trim();
			//visits.set(selectedEventIndex, editedEvent);
			//inputTField.clear();
		}
	}
	
	
	public void changePerspective(Perspective p) {
		if(p == Perspective.PATIENT) {
			tfInfo.setMinHeight(300);
			tfInfo.setPrefHeight(300);
			tfInfo.setMaxHeight(300);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_PORTAL));
			
			if(stackPane.getChildren().contains(btnAddVisit)) {
				stackPane.getChildren().removeAll(btnAddVisit, btnEditVisit);
			}
			
			if(!stackPane.getChildren().contains(infoBox)) {
				stackPane.getChildren().addFirst(infoBox);
			}
		} else if(p == Perspective.STAFF) {
			tfInfo.setMinHeight(150);
			tfInfo.setPrefHeight(150);
			tfInfo.setMaxHeight(150);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_INFO));
			
			if(!stackPane.getChildren().contains(btnAddVisit)) {
				stackPane.getChildren().addFirst(btnAddVisit);
			}
			
			if(!stackPane.getChildren().contains(btnEditVisit)) {
				stackPane.getChildren().addFirst(btnEditVisit);
			}
			
			if(stackPane.getChildren().contains(infoBox)) {
				stackPane.getChildren().remove(infoBox);
			}
		}
		
		perspective = p;
	}
}

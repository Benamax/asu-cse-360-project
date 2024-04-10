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
	StackPane stackPane;
	Label lblTitle;
	
	GridPane calendarGrid;
	
	Label lblInfo;
	TextField tfInfo;
	
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
		
		visitListView = new ListView<>(visits);
		visitListView.setMaxSize(750, 350);
		
		btnExit = CommonControls.createButton("Back", Views.PATIENT_PORTAL);
		btnAddVisit = CommonControls.createButton("Add Event", e -> addVisit());
		btnEditVisit = CommonControls.createButton("Edit Event", e -> editVisit());
		
		stackPane.getChildren().addAll(lblTitle, btnAddVisit, btnEditVisit, btnExit, inputTField, visitListView);
		StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
		StackPane.setAlignment(btnAddVisit, Pos.CENTER_LEFT);
		StackPane.setAlignment(btnEditVisit, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(btnExit, Pos.TOP_LEFT);
		StackPane.setAlignment(inputTField, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(visitListView, Pos.CENTER_RIGHT);

	
		stackPane.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #ffe485 30%, #ffe5c4 47%);");
		
		root = stackPane;
		return root;
	}

	@Override
	public void reset() {
		
	}
	
	private void addVisit() {
		String newEvent = inputTField.getText().trim();
		if(!inputTField.getText().isEmpty()) {
			visits.add(newEvent);
			inputTField.clear();
		}
	}
	
	private void editVisit() {
		String selectedEvent = visitListView.getSelectionModel().getSelectedItem();
		if (selectedEvent != null) {
			int selectedEventIndex = visitListView.getSelectionModel().getSelectedIndex();
			String editedEvent = inputTField.getText().trim();
			visits.set(selectedEventIndex, editedEvent);
			inputTField.clear();
		}
	}
	
	
	public void changePerspective(Perspective p) {
		if(p == Perspective.PATIENT) {
			tfInfo.setMinHeight(300);
			tfInfo.setPrefHeight(300);
			tfInfo.setMaxHeight(300);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_PORTAL));
			
			stackPane.getChildren().removeAll(btnAddVisit, btnEditVisit);
		} else if(p == Perspective.STAFF) {
			tfInfo.setMinHeight(150);
			tfInfo.setPrefHeight(150);
			tfInfo.setMaxHeight(150);
			
			btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_INFO));
			
			stackPane.getChildren().addFirst(btnAddVisit);
			stackPane.getChildren().addFirst(btnEditVisit);
		}
		
		perspective = p;
	}
}

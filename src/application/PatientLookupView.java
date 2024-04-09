package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import common_controls.LabeledTextField;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PatientLookupView extends View {
	VBox vLayout;
	HBox hButton;
	
	LabeledTextField firstNameField;
	LabeledTextField lastNameField;
	LabeledTextField dobField;
	
	Button searchButton;
	Button backButton;
		
	Label title;
		
	@Override
	public Parent generate() {
		vLayout = new VBox(25);
		hButton = new HBox(20);
		
		title = new Label("Patient Lookup");
		title.setFont(Font.font("Arial", 26));
		
		
		// Text fields
		firstNameField = new LabeledTextField("FIRST NAME");
		lastNameField = new LabeledTextField("LAST NAME");
		dobField = new LabeledTextField("DATE OF BIRTH");
		
		firstNameField.setOnAction(e -> lastNameField.requestFocus());
		lastNameField.setOnAction(e -> dobField.requestFocus());
		dobField.setOnAction(e -> attemptLookup());
		
		
		// Buttons
		searchButton = CommonControls.createButton("Search", e -> attemptLookup());
		backButton = CommonControls.createButton("Back", Views.STAFF_PORTAL);
		
		hButton.getChildren().addAll(backButton, searchButton);
		hButton.setAlignment(Pos.CENTER);
		
		
		// Finalize layout
		vLayout.getChildren().addAll(title, firstNameField.getRoot(), lastNameField.getRoot(), dobField.getRoot(), hButton);
		vLayout.setAlignment(Pos.CENTER);
		
		root = vLayout;
		root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
	}

	@Override
	public void reset() {
		firstNameField.clear();
		lastNameField.clear();
		dobField.clear();
	}
	
	private void attemptLookup() {
		// TODO: Check if patient actually exists
		
		ViewController.switchView(Views.PATIENT_INFO);
	}
}

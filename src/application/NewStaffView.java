package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import common_controls.LabeledTextField;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class NewStaffView extends View {
	
	StackPane stackRoot;
	Patient currentPatient;
	
	// VIEW 1
	VBox vLayout;
	VBox fields;
	HBox hBtns;
	
	Label welcomeTxt;
	
	LabeledTextField firstNameField;
	LabeledTextField lastNameField;
	LabeledTextField dobField;
	
	Button backButton;
	Button nextButton;
	
	// VIEW 2
	VBox vLayout2;
	VBox fields2;
	HBox hBtns2;
	
	Label welcomeTxt2;
	
	LabeledTextField userField;
	LabeledTextField passField;
	
	Button createButton;
	Button backButton2;
	
	@Override
	public Parent generate() {
		stackRoot = new StackPane();
		
		vLayout = new VBox(35);
		vLayout2 = new VBox(35);
		fields = new VBox(20);
		fields2 = new VBox(20);
		hBtns = new HBox(20);
		hBtns2 = new HBox(20);
		
		welcomeTxt = new Label("Create a Staff Account");
		welcomeTxt.setFont(Font.font("Arial", 26));
		
		welcomeTxt2 = new Label("Create a Staff Account");
		welcomeTxt2.setFont(Font.font("Arial", 26));
		
		
		// Text fields
		firstNameField = new LabeledTextField("FIRST NAME");
		lastNameField = new LabeledTextField("LAST NAME");
		dobField = new LabeledTextField("DATE OF BIRTH");
		
		userField = new LabeledTextField("USERNAME");
		passField = new LabeledTextField("PASSWORD");
		
		
		// Buttons
		nextButton = CommonControls.createButton("Next", e -> {
			String patientID = Patient.generateID(
					firstNameField.getText(),
					lastNameField.getText(),
					dobField.getText()
			);
			
			Patient newPatient = new Patient();
			newPatient.firstName = firstNameField.getText();
			newPatient.lastName = lastNameField.getText();
			newPatient.dob = dobField.getText();
			newPatient.save(patientID);
			
			currentPatient = newPatient;
			
			stackRoot.getChildren().clear();
			stackRoot.getChildren().add(vLayout2);
		});
		backButton = CommonControls.createButton("Back", Views.STAFF_PORTAL);
		
		createButton = CommonControls.createButton("Create", e -> {
			Login newLogin = new Login(userField.getText(), passField.getText(), currentPatient.getPatientID(), true);
			newLogin.save(newLogin.username);
			ViewController.switchView(Views.STAFF_PORTAL);
		});
		backButton2 = CommonControls.createButton("Back", e -> reset());
		
		// Finalize layout
		fields.getChildren().addAll(firstNameField.getRoot(), lastNameField.getRoot(), dobField.getRoot());
		fields.setAlignment(Pos.CENTER);
		
		hBtns.getChildren().addAll(backButton, nextButton);
		hBtns.setAlignment(Pos.CENTER);
		
		vLayout.getChildren().addAll(welcomeTxt, fields, hBtns);
		vLayout.setAlignment(Pos.CENTER);
		
		fields2.getChildren().addAll(userField.getRoot(), passField.getRoot());
		fields2.setAlignment(Pos.CENTER);
		
		hBtns2.getChildren().addAll(backButton2, createButton);
		hBtns2.setAlignment(Pos.CENTER);
		
		vLayout2.getChildren().addAll(welcomeTxt2, fields2, hBtns2);
		vLayout2.setAlignment(Pos.CENTER);
		
		stackRoot.getChildren().add(vLayout);
		stackRoot.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		root = stackRoot;
		return root;
	}
	
	@Override
	public void onEnter() {
		
	}
	
	@Override
	public void reset() {
		currentPatient = null;
		
		firstNameField.clear();
		lastNameField.clear();
		dobField.clear();
		
		stackRoot.getChildren().clear();
		stackRoot.getChildren().add(vLayout);
	}

}

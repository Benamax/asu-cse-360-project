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

public class CreateLoginView extends View {
	
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
		
		welcomeTxt = new Label("Create an Account");
		welcomeTxt.setFont(Font.font("Arial", 26));
		
		welcomeTxt2 = new Label("Create an Account");
		welcomeTxt2.setFont(Font.font("Arial", 26));
		
		
		// Text fields
		firstNameField = new LabeledTextField("FIRST NAME");
		lastNameField = new LabeledTextField("LAST NAME");
		dobField = new LabeledTextField("DATE OF BIRTH");
		
		userField = new LabeledTextField("USERNAME");
		passField = new LabeledTextField("PASSWORD");
		
		
		// Buttons
		nextButton = CommonControls.createButton("Next", e -> {
            if(dobField.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
                String patientID = Patient.generateID(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        dobField.getText()
                );

                if(Patient.exists(patientID)) {
                    Patient newPatient = new Patient();
                    newPatient.load(patientID);
                    currentPatient = newPatient;
                    stackRoot.getChildren().clear();
                    stackRoot.getChildren().add(vLayout2);
                } else {
                    Alert patientAlert = new Alert(AlertType.WARNING);
                    patientAlert.setHeaderText("Invalid information");
                    patientAlert.setContentText("There is no patient with that information.");
                    patientAlert.showAndWait();
                }
            } else {
                Alert patientAlert = new Alert(AlertType.WARNING);
                patientAlert.setHeaderText("Invalid DOB Format");
                patientAlert.setContentText("Format for DOB is dd/mm/yyyy.");
                patientAlert.showAndWait();
            }
        });
		backButton = CommonControls.createButton("Back", Views.INITIAL);
		
		createButton = CommonControls.createButton("Create", e -> {
			Login newLogin = new Login(userField.getText(), passField.getText(), currentPatient.getPatientID(), false);
			newLogin.save(newLogin.username);
			ViewController.switchView(Views.INITIAL);
		});
		backButton2 = CommonControls.createButton("Back", e -> {
			currentPatient = null;
			firstNameField.clear();
			lastNameField.clear();
			dobField.clear();
			stackRoot.getChildren().clear();
			stackRoot.getChildren().add(vLayout);
		});
		
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
		
	}

}

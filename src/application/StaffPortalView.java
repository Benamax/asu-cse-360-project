package application;

import application.ViewController.Views;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;


public class StaffPortalView extends View {
	@Override
	public Parent generate() {
		VBox vLayout;
		Label welcomeLabel = new Label("Staff Portal");
		Button patientLookup = new Button("Patient Lookup");
		patientLookup.setFont(Font.font("Arial", 16));
		patientLookup.setOnAction(e -> ViewController.switchView(Views.PATIENT_LOOKUP));
		Button addNewPatient = new Button("Add New Patient");
		addNewPatient.setFont(Font.font("Arial", 16));
		addNewPatient.setOnAction(e -> ViewController.switchView(Views.ADD_PATIENT));
		Button inboxButton = new Button("Inbox");
		inboxButton.setFont(Font.font("Arial", 16));
		Button viewSchedule = new Button("View Schedule");
		viewSchedule.setFont(Font.font("Arial", 16));
		Button signOut = new Button("Sign Out");
		signOut.setFont(Font.font("Arial", 16));
		signOut.setOnAction(e -> ViewController.switchView(Views.INITIAL));
		
		welcomeLabel.setFont(Font.font("Arial", 36));
		vLayout = new VBox(25);
		vLayout.getChildren().addAll(welcomeLabel, patientLookup, addNewPatient, inboxButton, viewSchedule, signOut);
		
		inboxButton.setOnAction(e -> ViewController.switchView(Views.INBOX) );
		
		vLayout.setAlignment(Pos.CENTER);
		
		root = vLayout;
		root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
	}

	@Override
	public void reset() {
		
	}
	
}

package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Label;


public class StaffPortalView extends View {
	@Override
	public Parent generate() {
		Label welcomeLabel = new Label("Staff Portal");
		welcomeLabel.setFont(Font.font("Arial", 36));
		
		Button patientLookup 	= CommonControls.createButton("Patient Lookup", Views.PATIENT_LOOKUP);
		Button addNewPatient 	= CommonControls.createButton("Add New Patient", Views.ADD_PATIENT);
		Button addNewStaff		= CommonControls.createButton("Add New Staff", Views.NEW_STAFF);
		Button inboxButton 		= CommonControls.createButton("Inbox", Views.INBOX);
		Button signOut 			= CommonControls.createButton("Sign Out", Views.INITIAL);	// TODO: Call logout method?
		
		VBox vLayout = new VBox(25);
		vLayout.getChildren().addAll(welcomeLabel, patientLookup, addNewPatient, addNewStaff, inboxButton, signOut);
		vLayout.setAlignment(Pos.CENTER);
		
		root = vLayout;
		root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public void reset() {
		
	}
}

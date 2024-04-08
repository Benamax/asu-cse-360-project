package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PatientLookupView extends View {
	VBox vLayout;
	HBox hButton;
	
	LabeledField firstNameField;
	LabeledField lastNameField;
	LabeledField dobField;
	
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
		firstNameField = new LabeledField("FIRST NAME");
		lastNameField = new LabeledField("LAST NAME");
		dobField = new LabeledField("DATE OF BIRTH");
		
		
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
	
	private class LabeledField {
		VBox root;
		Label label;
		TextField textField;
		
		public LabeledField(String text) {
			root = new VBox(5);
			label = new Label(text);
			label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			textField = new TextField();
			
			root.getChildren().addAll(label, textField);
			root.setAlignment(Pos.CENTER_LEFT);
			root.setMaxWidth(200);
		}
		
		public VBox getRoot() {
			return root;
		}
		
		public String getText() {
			return textField.getText();
		}
		
		public void setText(String text) {
			textField.setText(text);
		}
		
		public void clear() {
			textField.clear();
		}
	}
	
	private Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
		Button newBtn = new Button(text);
		newBtn.setPrefSize(150, 50);
		newBtn.setFont(Font.font("Arial", 14));
		newBtn.setOnAction(eventHandler);
		return newBtn;
	}
	
	private void attemptLookup() {
		// TODO: Check if patient actually exists
		
		ViewController.switchView(Views.PATIENT_INFO);
	}
}

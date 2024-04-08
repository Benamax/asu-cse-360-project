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

public class NewPatientView  extends View {

	VBox vLayout;
	VBox fields;
	HBox hBtns;
	
	Label welcomeTxt;
	
	Button backButton;
	Button addButton;
	
	@Override
	public Parent generate() {
		vLayout = new VBox(35);
		fields = new VBox(20);
		hBtns = new HBox(20);
		
		welcomeTxt = new Label("Create an Account");
		welcomeTxt.setFont(Font.font("Arial", 26));
		
		
		// Text fields
		LabeledTextField firstNameField = new LabeledTextField("FIRST NAME");
		LabeledTextField lastNameField = new LabeledTextField("LAST NAME");
		LabeledTextField dobField = new LabeledTextField("DATE OF BIRTH");
		
		
		// Buttons
		addButton = CommonControls.createButton("Add", Views.INITIAL);
		backButton = CommonControls.createButton("Back", Views.INITIAL);
		
		
		// Finalize layout
		fields.getChildren().addAll(firstNameField.getRoot(), lastNameField.getRoot(), dobField.getRoot());
		fields.setAlignment(Pos.CENTER);
		
		hBtns.getChildren().addAll(backButton, addButton);
		hBtns.setAlignment(Pos.CENTER);
		
		vLayout.getChildren().addAll(welcomeTxt, fields, hBtns);
		vLayout.setAlignment(Pos.CENTER);
		
		vLayout.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		root = vLayout;
		return root;
	}

	@Override
	public void reset() {
		
	}

}

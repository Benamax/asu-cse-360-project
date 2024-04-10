package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import common_controls.LabeledTextArea;
import common_controls.LabeledTextField;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EditVisitView extends View {
	Label lblTitle;
	
	Label lblDate;
	TextField tfDate;
	
	Button btnSave;
	Button btnBack;
	
	@Override
	public Parent generate() {
		lblTitle = new Label("Visits");
		lblTitle.setFont(Font.font("Arial", 26));
		
		VBox smallFields = new VBox(25);
		String[] smallFieldNames = {
				"Patient",
				"Weight",
				"Height",
				"Body Temperature",
				"Blood Pressure"
		};
		
		
		for(String smallFieldName : smallFieldNames) {
			LabeledTextField field = new LabeledTextField(smallFieldName);
			smallFields.getChildren().add(field.getRoot());
		}
		
		// TODO: Make check box bigger with CSS
		CheckBox cbUnderAge = new CheckBox("Under 12");
		cbUnderAge.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		HBox cbAlign = new HBox();		// Create an HBox to center align the check box
		cbAlign.setAlignment(Pos.CENTER);
		cbAlign.getChildren().add(cbUnderAge);
		smallFields.getChildren().add(cbAlign);
		
		VBox bigFields = new VBox(25);
		String[] bigFieldNames = {
				"Reason for Appointment",
				"Physical Examination",
				"Notes to Patient"
		};
		
		for(String bigFieldName : bigFieldNames) {
			LabeledTextArea field = new LabeledTextArea(bigFieldName);
			bigFields.getChildren().add(field.getRoot());
		}
		
		lblDate = new Label("Date");
		lblDate.setFont(Font.font("Arial", 18));
		tfDate = new TextField();
		tfDate.setEditable(false);
		tfDate.setMinWidth(175);
		tfDate.setPrefWidth(175);
		tfDate.setMaxWidth(175);
		VBox dateBox = new VBox(5);
		dateBox.getChildren().addAll(lblDate, tfDate);
		
		btnSave = CommonControls.createButton("Save", e -> System.out.println("TODO: Create \"Save\" functionality"));
		btnBack = CommonControls.createButton("Back", e -> System.out.println("TODO: Create \"Back\" functionality"));
		
		HBox fields = new HBox(10);
		fields.getChildren().addAll(smallFields, bigFields, dateBox);
		fields.getChildren().forEach(child -> HBox.setHgrow(child, Priority.ALWAYS));
		
		StackPane top = new StackPane();
		top.getChildren().addAll(btnBack, lblTitle);
		StackPane.setAlignment(btnBack, Pos.CENTER_LEFT);
		
		VBox main = new VBox(30);
		main.getChildren().addAll(top, fields);
		main.setPadding(new Insets(50, 20, 20, 20));
		
		root = main;
		return root;
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public void reset() {
		
	}
}

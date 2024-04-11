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
	CheckBox cbUnderAge;
	
	LabeledTextField[] smallFields;
	LabeledTextArea[] bigFields;
	
	Button btnSave;
	Button btnBack;
	
	@Override
	public Parent generate() {
		lblTitle = new Label("Visits");
		lblTitle.setFont(Font.font("Arial", 26));
		
		VBox smallFieldBox = new VBox(25);
		String[] smallFieldNames = {
				"Patient",
				"Weight",
				"Height",
				"Body Temperature",
				"Blood Pressure"
		};
		smallFields = new LabeledTextField[smallFieldNames.length];
		
		
		for(int i = 0; i < smallFieldNames.length; i++) {
			String smallFieldName = smallFieldNames[i];
			LabeledTextField field = new LabeledTextField(smallFieldName);
			smallFields[i] = field;
			smallFieldBox.getChildren().add(field.getRoot());
		}
		/*for(String smallFieldName : smallFieldNames) {
			LabeledTextField field = new LabeledTextField(smallFieldName);
			smallFieldBox.getChildren().add(field.getRoot());
		}*/
		
		// TODO: Make check box bigger with CSS
		cbUnderAge = new CheckBox("Under 12");
		cbUnderAge.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		HBox cbAlign = new HBox();		// Create an HBox to center align the check box
		cbAlign.setAlignment(Pos.CENTER);
		cbAlign.getChildren().add(cbUnderAge);
		smallFieldBox.getChildren().add(cbAlign);
		
		VBox bigFieldBox = new VBox(25);
		String[] bigFieldNames = {
				"Reason for Appointment",
				"Physical Examination",
				"Notes to Patient"
		};
		bigFields = new LabeledTextArea[bigFieldNames.length];
		
		for(int i = 0; i < bigFieldNames.length; i++) {
			String bigFieldName = bigFieldNames[i];
			LabeledTextArea field = new LabeledTextArea(bigFieldName);
			bigFields[i] = field;
			bigFieldBox.getChildren().add(field.getRoot());
		}
		/*for(String bigFieldName : bigFieldNames) {
			LabeledTextArea field = new LabeledTextArea(bigFieldName);
			bigFieldBox.getChildren().add(field.getRoot());
		}*/
		
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
		btnBack = CommonControls.createButton("Back", e -> goBack());
		
		HBox fields = new HBox(10);
		fields.getChildren().addAll(smallFieldBox, bigFieldBox, dateBox);
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
		loadVisit();
	}

	@Override
	public void reset() {
		
	}
	
	private void loadVisit() {
		Visit visitToEdit = PatientSystem.currentVisit;
		
		System.out.println(visitToEdit.patientName);
		System.out.println(visitToEdit.weight);
		System.out.println(visitToEdit.height);
		
		smallFields[0].setText(visitToEdit.patientName);
		smallFields[1].setText(visitToEdit.weight);
		smallFields[2].setText(visitToEdit.height);
		smallFields[3].setText(visitToEdit.bodyTemperature);
		smallFields[4].setText(visitToEdit.bloodPressure);
		cbUnderAge.setSelected(visitToEdit.under12);
		
		bigFields[0].setText(visitToEdit.reasonFor);
		bigFields[1].setText(visitToEdit.phyExam);
		bigFields[2].setText(visitToEdit.notes);
		
		tfDate.setText(visitToEdit.date.toLocalDate().toString());
	}
	
	private void saveVisit() {
		Visit visitToSave = PatientSystem.currentVisit;
		
		visitToSave.patientName = smallFields[0].getText();
		visitToSave.weight = smallFields[1].getText();
		visitToSave.height = smallFields[2].getText();
		visitToSave.bodyTemperature	= smallFields[3].getText();
		visitToSave.bloodPressure = smallFields[4].getText();
		visitToSave.under12 = cbUnderAge.isSelected();
		
		visitToSave.reasonFor = bigFields[0].getText();
		visitToSave.phyExam = bigFields[1].getText();
		visitToSave.notes = bigFields[2].getText();
		
		visitToSave.save(visitToSave.number);
	}
	
	private void goBack() {
		saveVisit();
		
		if(LoginSystem.currentLogin.isStaff) {
			ViewController.switchView(Views.PATIENT_VISITS_STAFF);
		} else {
			ViewController.switchView(Views.PATIENT_VISITS);
		}
	}
}

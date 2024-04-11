package application;

import java.util.ArrayList;
import java.util.List;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class PatientPortalView extends View {
	Label title;
	
	// Doctor information
	Label lblDoctor;
	ImageView imgDoctor;
	Label lblDoctorName;
	
	// Inbox
	Label lblMail;
	ListView<String> lstMail;
	Button btnNewMsg;
	
	// Contact information
	Label lblContact;
	Label lblHomeAddr;
	TextField tfHomeAddr;
	Button btnHomeAddr;
	Label lblPhone;
	TextField tfPhone;
	Button btnPhone;
	Label lblEmail;
	TextField tfEmail;
	Button btnEmail;
	
	// Right column (previous visits)
	Label lblLastVisit;
	TextArea tfLastVisit;
	Button btnVisits;
	
	Button btnSignOut;
	/*MessageSystem logs = new MessageSystem();
	LoginSystem check = new LoginSystem();
	String user = LoginSystem.getCurrentUsername();


	private void startBackgroundUpdate() {
	    Timeline timeline = new Timeline(
	        new KeyFrame(Duration.seconds(1), event -> {
	    		user = LoginSystem.getCurrentUsername();
	    		if (user != "") {
	        		if (new MessageSystem().loadMessages(user) != null) {
	        			ArrayList<String> checker = new MessageSystem().loadMessages(user);
	        			ObservableList<String> mail = FXCollections.observableArrayList(checker); 
	        			lstMail.setItems(mail);	
	        		}
	    		}
	
	        })
	    );
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}*/
	
	
	@Override
	public Parent generate() {
		//startBackgroundUpdate();	
		title = new Label("Patient Portal");
		title.setFont(Font.font("Arial", 30));
		
		GridPane columns = new GridPane();
		columns.setHgap(20);
		columns.setVgap(50);
		
		VBox column1 = new VBox(75);
		VBox doctorLayout = new VBox(0);
		lblDoctor = new Label("Primary Care Physician");
		lblDoctor.setAlignment(Pos.CENTER_LEFT);
		
		imgDoctor = new ImageView("doctor_img.jpg");
		imgDoctor.setFitWidth(200);
		imgDoctor.setPreserveRatio(true);
		// TODO: Add image border
		
		lblDoctorName = new Label("Dr. Chadworth M.D.");
		lblDoctorName.setAlignment(Pos.CENTER_RIGHT);
		
		doctorLayout.getChildren().addAll(lblDoctor, imgDoctor, lblDoctorName);
		doctorLayout.setAlignment(Pos.CENTER);
		
		btnVisits = CommonControls.createButton("Prior Visits", Views.PATIENT_VISITS);
		btnSignOut = CommonControls.createButton("Sign Out", Views.INITIAL);	// TODO: Add sign-out from login system?
		column1.getChildren().addAll(doctorLayout, btnSignOut);
		column1.setAlignment(Pos.TOP_CENTER);
		
		
		VBox column2 = new VBox(50);
		
		VBox mailLayout = new VBox(5);
		lblMail = new Label("All Mail");
		lstMail = new ListView<>();
		//ObservableList<String> mail = FXCollections.observableArrayList(
				//"Msg 1", "Msg 2", "Msg 3", "Msg 4", "Msg 5", "Msg 6", "Msg 7");

										
		btnNewMsg = CommonControls.createButton("Open Inbox", Views.PATIENT_INBOX);
		CommonControls.changeControlSize(btnNewMsg, 100, 40);
		mailLayout.getChildren().addAll(lblMail, lstMail, btnNewMsg);
		
		VBox contactLayout = new VBox(5);
		lblContact = new Label("Contact Information");
		
		HBox homeAddr = new HBox(0);
		lblHomeAddr = new Label("Home Address");
		tfHomeAddr = new TextField();
		tfHomeAddr.setPrefWidth(250);
		//btnHomeAddr = new Button("Update");
		btnHomeAddr = CommonControls.createUnsizedButton("Update", e -> updateHomeAddr());
		homeAddr.getChildren().addAll(tfHomeAddr, btnHomeAddr);
		
		HBox phone = new HBox(0);
		lblPhone = new Label("Phone");
		tfPhone = new TextField();
		tfPhone.setPrefWidth(250);
		//btnPhone = new Button("Update");
		btnPhone = CommonControls.createUnsizedButton("Update", e -> updatePhone());
		phone.getChildren().addAll(tfPhone, btnPhone);
		
		HBox email = new HBox(0);
		lblEmail = new Label("Email");
		tfEmail = new TextField();
		tfEmail.setPrefWidth(250);
		//btnEmail = new Button("Update");
		btnEmail = CommonControls.createUnsizedButton("Update", e -> updateEmail());
		email.getChildren().addAll(tfEmail, btnEmail);
		
		contactLayout.getChildren().addAll(lblContact, lblHomeAddr, homeAddr, lblPhone, phone, lblEmail, email);
		column2.getChildren().addAll(mailLayout, contactLayout);
		
		
		VBox column3 = new VBox(5);
		lblLastVisit = new Label("Last Visit");
		tfLastVisit = new TextArea();
		tfLastVisit.setEditable(false);
		tfLastVisit.setWrapText(true);
		tfLastVisit.setPrefSize(200, 350);
		
		column3.getChildren().addAll(lblLastVisit, tfLastVisit, btnVisits);
		//column3.setAlignment(Pos.CENTER);
		
		
		columns.add(title, 1, 0);
		columns.addRow(1, column1, column2, column3);
		columns.setAlignment(Pos.CENTER);
		ColumnConstraints cc1 = new ColumnConstraints();
		ColumnConstraints cc2 = new ColumnConstraints();
		ColumnConstraints cc3 = new ColumnConstraints();
		cc1.setPercentWidth(33);
		cc2.setPercentWidth(34);
		cc3.setPercentWidth(33);
		columns.getColumnConstraints().addAll(cc1, cc2, cc3);
		columns.setPadding(new Insets(50, 50, 50, 50));
		GridPane.setHalignment(title, HPos.CENTER);
		GridPane.setValignment(title, VPos.TOP);
		
		//column1.setAlignment(Pos.CENTER);
		
		
		
		root = columns;
		return root;
	}
	
	@Override
	public void onEnter() {
		Patient patient = new Patient();
		String patientID = LoginSystem.currentLogin.patientID;
		patient.load(patientID);
		
		tfHomeAddr.setText(patient.homeAddress);
		tfPhone.setText(patient.phoneNumber);
		tfEmail.setText(patient.email);
		
		// Get messages
		List<String> msgNames = MessageSystem.loadMessageNames(LoginSystem.currentLogin.username);
		ObservableList<String> msgObsList = FXCollections.observableArrayList(msgNames);
		lstMail.setItems(msgObsList);
		
		// Get last visit
		List<Visit> visits = VisitSystem.loadVisits(patientID);
		if(visits.size() > 0) {
			Visit lastVisit = visits.get(0);
			tfLastVisit.setText(String.format("Reason for Appointment:\n%s\n\nDoctor's Notes:\n%s", lastVisit.reasonFor, lastVisit.notes));
		}
	}

	@Override
	public void reset() {
		
	}
	
	private void updateHomeAddr() {
		Patient patient = new Patient();
		String patientID = LoginSystem.currentLogin.patientID;
		patient.load(patientID);
		
		patient.homeAddress = tfHomeAddr.getText();
		patient.save(patientID);
	}
	
	private void updatePhone() {
		Patient patient = new Patient();
		String patientID = LoginSystem.currentLogin.patientID;
		patient.load(patientID);
		
		patient.phoneNumber = tfPhone.getText();
		patient.save(patientID);
	}
	
	private void updateEmail() {
		Patient patient = new Patient();
		String patientID = LoginSystem.currentLogin.patientID;
		patient.load(patientID);
		
		patient.email = tfEmail.getText();
		patient.save(patientID);
	}

}

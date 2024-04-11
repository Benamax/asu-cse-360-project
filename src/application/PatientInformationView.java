package application;

import java.util.List;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PatientInformationView extends View {

		Button priorVisitsButton;
		Button finishButton;
		
		Label titleLabel;
		Text phoneNumber;
		InfoPane[] infoPanes;
		String[] infoTitles;
		StackPane stackPane;
		Patient patient = PatientSystem.currentPatient;
		private void startBackgroundUpdate() {
		    Timeline timeline = new Timeline(
		        new KeyFrame(Duration.seconds(1), event -> {
					if (PatientSystem.currentPatient != null) {
						Patient patient = PatientSystem.currentPatient;
						if (patient.phoneNumber != null) {
							phoneNumber.setText("Patient Phone #: " + PatientSystem.currentPatient.phoneNumber);
							stackPane.getChildren().remove(phoneNumber);
							stackPane.getChildren().add(phoneNumber);
						}
					}
		        })
		    );
		    timeline.setCycleCount(Timeline.INDEFINITE);
		    timeline.play();
		}
		
		
		
		@Override
		public Parent generate() {
			stackPane = new StackPane();
			phoneNumber = new Text("Patient Phone #: ");
			startBackgroundUpdate();
			

			stackPane.setMinSize(1100, 700);
			stackPane.setPrefSize(1100, 700);
			stackPane.setMaxSize(1100, 700);
			stackPane.setPadding(new Insets(50));
			
			titleLabel = new Label("Patient Information");
			titleLabel.setFont(Font.font("Arial", 36));
			
			phoneNumber.setFont(Font.font("Arial", 16));
			
			infoTitles = new String[] {
					"Known Allergies",
					"Health Concerns",
					"Prior Health Issues",
					"Prescribed Medications",
					"Immunization Records"
			};
			
			GridPane infoGrid = new GridPane();
			infoPanes = new InfoPane[infoTitles.length];
			infoGrid.setHgap(20);
			infoGrid.setVgap(10);
			infoGrid.setAlignment(Pos.CENTER_LEFT);
			infoGrid.setTranslateX(60);
			for(int i = 0; i < infoTitles.length; i++) {
				InfoPane newPane = new InfoPane(infoTitles[i]);
				infoPanes[i] = newPane;
				infoGrid.add(newPane.getPane(), i % 3, i / 3);
			}
			
			priorVisitsButton = CommonControls.createButton("Visits", Views.PATIENT_VISITS_STAFF);
			finishButton = CommonControls.createButton("Finish", Views.PATIENT_LOOKUP);
			
			stackPane.getChildren().addAll(titleLabel, infoGrid, phoneNumber, priorVisitsButton, finishButton);
			StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
			StackPane.setAlignment(priorVisitsButton, Pos.CENTER_RIGHT);
			StackPane.setAlignment(finishButton, Pos.TOP_LEFT);
			StackPane.setAlignment(phoneNumber, Pos.BOTTOM_RIGHT);
			
			root = stackPane;
			return root;
		}
		
		public class InfoPane {
			StackPane stackPane;
			
			Label lblName;
			TextArea tfInfo;
			Button btnToggle;
			
			public InfoPane(String name) {
				int paneWidth = 180;
				int paneHeight = 150;
				
				stackPane = new StackPane();
				stackPane.setMinSize(paneWidth, paneHeight);
				
				lblName = new Label(name);
				StackPane.setAlignment(lblName, Pos.TOP_LEFT);
				
				tfInfo = new TextArea();
				tfInfo.setEditable(false);
				tfInfo.setWrapText(true);
				tfInfo.setMinSize(paneWidth, paneHeight - 16);
				tfInfo.setPrefSize(paneWidth, paneHeight - 16);
				tfInfo.setMaxSize(paneWidth, paneHeight - 16);
				tfInfo.setTranslateY(16);
				StackPane.setAlignment(tfInfo, Pos.TOP_LEFT);
				
				btnToggle = CommonControls.createButton("edit", e -> switchState(), 50, 25);
				/*btnToggle = new Button("edit");
				btnToggle.setOnAction(e -> switchState());
				btnToggle.setMinWidth(40);*/
				StackPane.setAlignment(btnToggle, Pos.BOTTOM_RIGHT);
				
				stackPane.getChildren().addAll(tfInfo, btnToggle, lblName);
			}
			
			public StackPane getPane() {
				return stackPane;
			}
			
			public String getText() {
				return tfInfo.getText();
			}
			
			public void setText(String text) {
				tfInfo.setText(text);
			}
			
			public void switchState() {
				if(!tfInfo.isEditable()) {	// Switches to edit state is currently in view state
					tfInfo.setEditable(true);	// Make text field editable
					tfInfo.requestFocus();		// Focuses the text field, so the user can start typing without selecting it
					tfInfo.positionCaret(tfInfo.getText().length());	// Put caret (text cursor) at end of text
					btnToggle.setText("save");	// Switch button text
				} else {					// Switches to view state if currently in edit state
					tfInfo.setEditable(false);
					btnToggle.setText("edit");
				}
			}
		}
		
		@Override
		public void onEnter() {
			Patient patient = PatientSystem.currentPatient;
			findPane("Known Allergies").setText(patient.allergies);
			findPane("Health Concerns").setText(patient.healthConcerns);
			findPane("Prior Health Issues").setText(patient.priorIssues);
			findPane("Prescribed Medications").setText(patient.medications);
			findPane("Immunization Records").setText(patient.records);
		}
		
		@Override
		public void reset() {
			Patient patient = PatientSystem.currentPatient;
			
			patient.allergies = infoPanes[0].getText();
			patient.healthConcerns = infoPanes[1].getText();
			patient.priorIssues = infoPanes[2].getText();
			patient.medications = infoPanes[3].getText();
			patient.records = infoPanes[4].getText();
			
			patient.save(patient.getPatientID());
		}
		
		
		public InfoPane findPane(String title) {
			int i = 0;
			for(i = 0; i < infoTitles.length; i++) {
				if(infoTitles[i].equals(title))
					break;
				
				// If on last title without success, return null
				if(i == infoTitles.length)
					return null;
			}
			
			return infoPanes[i];
		}
	}

package application;

import application.ViewController.Views;
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

public class PatientInformationView extends View {

		Button priorVisitsButton;
		Button finishButton;
		
		Label titleLabel;
			
		@Override
		public Parent generate() {
			
			StackPane stackPane = new StackPane();
			stackPane.setMinSize(1100, 700);
			stackPane.setPrefSize(1100, 700);
			stackPane.setMaxSize(1100, 700);
			stackPane.setPadding(new Insets(50));
			
			titleLabel = new Label("Patient Information");
			titleLabel.setFont(Font.font("Arial", 36));
			
			String[] infoTitles = {
					"Known Allergies",
					"Health Concerns",
					"Prior Health Issues",
					"Prescribed Medications",
					"Immunization Records"
			};
			
			GridPane infoGrid = new GridPane();
			infoGrid.setHgap(20);
			infoGrid.setVgap(10);
			infoGrid.setAlignment(Pos.CENTER_LEFT);
			infoGrid.setTranslateX(60);
			for(int i = 0; i < infoTitles.length; i++) {
				InfoPane newPane = new InfoPane(infoTitles[i]);
				infoGrid.add(newPane.getPane(), i % 3, i / 3);
			}
			
			priorVisitsButton = new Button("Visits");
			priorVisitsButton.setPrefSize(150, 50);
			priorVisitsButton.setOnAction(e -> ViewController.switchView(Views.PATIENT_VISITS_STAFF));
			
			finishButton = new Button("Finish");
			finishButton.setPrefSize(150, 50);
			finishButton.setOnAction(e -> ViewController.switchView(Views.PATIENT_LOOKUP));
			
			stackPane.getChildren().addAll(titleLabel, infoGrid, priorVisitsButton, finishButton);
			StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
			StackPane.setAlignment(priorVisitsButton, Pos.CENTER_RIGHT);
			StackPane.setAlignment(finishButton, Pos.TOP_LEFT);
			
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
				
				btnToggle = new Button("edit");
				btnToggle.setOnAction(e -> switchState());
				btnToggle.setMinWidth(40);
				StackPane.setAlignment(btnToggle, Pos.BOTTOM_RIGHT);
				
				stackPane.getChildren().addAll(tfInfo, btnToggle, lblName);
			}
			
			public StackPane getPane() {
				return stackPane;
			}
			
			private void switchState() {
				if(!tfInfo.isEditable()) {	// Switches to edit state is currently in view state
					tfInfo.setEditable(true);	// Make text field editable
					tfInfo.requestFocus();		// Focuses the text field, so the user can start typing without selecting it
					btnToggle.setText("save");	// Switch button text
				} else {					// Switches to view state if currently in edit state
					tfInfo.setEditable(false);
					btnToggle.setText("edit");
				}
			}
		}
	@Override
		public void reset() {
			// TODO Auto-generated method stub

		}
	
	}

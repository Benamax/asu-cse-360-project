package application;

import application.ViewController.Views;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PatientInformationView extends View {

		Button priorVisitsButton;
		Button finishButton;
		
		Label titleLabel;
		Text firstNameText;
		Text lastNameText;
		Text dobText;
			
		@Override
		public Parent generate() {
			
			StackPane stackPane = new StackPane();
			stackPane.setMinSize(1100, 700);
			stackPane.setPrefSize(1100, 700);
			stackPane.setMaxSize(1100, 700);
			stackPane.setPadding(new Insets(50));
			
			titleLabel = new Label("Patient Information");
			titleLabel.setFont(Font.font("Arial", 36));
						
			finishButton = new Button("Finish");
			finishButton.setPrefSize(150, 50);
			finishButton.setOnAction(e -> ViewController.switchView(Views.PATIENT_LOOKUP));
			
			stackPane.getChildren().addAll(titleLabel, finishButton);
			StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
			StackPane.setAlignment(finishButton, Pos.TOP_LEFT);
			
			root = stackPane;
			return root;
		}
		
		public class DatePane {
			StackPane stackPane;
			Button editButton;
			TextField enterTextTField;
			Label textFieldNameLabel;
			
			public DatePane(int size, String text) {
				stackPane = new StackPane();
				stackPane.setMinSize(size, size);
				editButton = new Button("edit");
				enterTextTField = new TextField();
				textFieldNameLabel = new Label(text);
				
				stackPane.getChildren().addAll(editButton, enterTextTField, textFieldNameLabel);
			}
			
			public StackPane getPane() {
				return stackPane;
			}
		}
	@Override
		public void reset() {
			// TODO Auto-generated method stub

		}
	
	}

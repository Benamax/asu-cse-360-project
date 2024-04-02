package application;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PatientLookupView extends View {
		
	VBox vLayout;
	HBox hButton;
		
	TextField firstNameTField;
	TextField lastNameTField;
	TextField dobTField;
	
	Button searchButton;
		
	Text title;
	Text firstNameText;
	Text lastNameText;
	Text dobText;
		
	@Override
	public Parent generate() {
		
		vLayout = new VBox (25);
		hButton = new HBox (20);
		
		title = new Text("Patient Lookup");
		title.setFont(Font.font("Arial", 26));
		
		firstNameText = new Text("First Name");
		lastNameText = new Text("Last Name");
		dobText = new Text("Date of Birth");
		
		firstNameTField = new TextField();
		firstNameTField.setMaxSize(200,50);

		lastNameTField = new TextField();
		lastNameTField.setMaxSize(200,50);

		dobTField = new TextField();
		dobTField.setMaxSize(200,50);
		
		searchButton = new Button("Search");
		Font searchButtonFont = Font.font("Arial", 14);
		searchButton.setFont(searchButtonFont);
		
		vLayout.getChildren().addAll(title, firstNameText, firstNameTField, lastNameText, lastNameTField, dobText, dobTField, searchButton);
		vLayout.setAlignment(Pos.CENTER);
		
		root = vLayout;
		root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
			}

	@Override
	public void reset() {

	}

}

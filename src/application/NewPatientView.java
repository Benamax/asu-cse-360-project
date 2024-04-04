package application;

import application.ViewController.Views;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class NewPatientView  extends View{

	VBox vLayout;
	VBox firstNameLayout;
	VBox lastNameLayout;
	VBox dOBLayout;
	HBox middleLayout;
	HBox hBtns;
	
	Text welcomeTxt;
	Text enterFirst;
	Text enterLast;
	Text enterBirth;
	
	TextField firstName;
	TextField lastName;
	TextField dateOfBirth;
	
	Button backButton;
	Button addButton;
	
	@Override
	public Parent generate() {
		
		vLayout = new VBox(35);
		firstNameLayout = new VBox(10);
		lastNameLayout = new VBox(10);
		dOBLayout = new VBox(10);
		middleLayout = new HBox(10);
		hBtns = new HBox(20);
		
		welcomeTxt = new Text("Add New Patient");
		welcomeTxt.setFont(Font.font("Arial", 26));
		
		enterFirst = new Text("First Name");
		enterFirst.setTextAlignment(TextAlignment.CENTER);
		
		enterLast = new Text("Last Name");
		enterLast.setTextAlignment(TextAlignment.CENTER);
		
		enterBirth = new Text("Day of Birth");
		enterBirth.setTextAlignment(TextAlignment.CENTER);
		
		firstName = new TextField();
		firstName.setMinSize(200, 50);
		firstName.setMaxWidth(100);
		
		lastName = new TextField();
		lastName.setMinSize(200, 50);
		lastName.setMaxWidth(100);
		
		dateOfBirth = new TextField();
		dateOfBirth.setMinSize(200, 50);
		dateOfBirth.setMaxWidth(100);

		backButton = new Button("Back");
		addButton = new Button("Add");
		
		backButton.setPrefSize(150, 50);
		addButton.setPrefSize(150, 50);
		
		Font btnFont = Font.font("Arial", 14);
		backButton.setFont(btnFont);
		addButton.setFont(btnFont);
		
		backButton.setOnAction(e -> ViewController.switchView(Views.STAFF_PORTAL));
		addButton.setOnAction(e -> ViewController.switchView(Views.STAFF_PORTAL));
		
		firstNameLayout.getChildren().addAll(enterFirst, firstName);
		lastNameLayout.getChildren().addAll(enterLast, lastName);
		dOBLayout.getChildren().addAll(enterBirth, dateOfBirth);
		
		VBox temp = new VBox(20);
		temp.getChildren().addAll(firstNameLayout, lastNameLayout, dOBLayout);
		temp.setAlignment(Pos.CENTER);
		
		middleLayout.getChildren().add(temp);
		middleLayout.setAlignment(Pos.CENTER);
		
		hBtns.getChildren().addAll(backButton, addButton);
		hBtns.setAlignment(Pos.CENTER);
		
		vLayout.getChildren().addAll(welcomeTxt, middleLayout, hBtns);
		vLayout.setAlignment(Pos.CENTER);
		
		vLayout.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		root = vLayout;
		return root;
	}

	@Override
	public void reset() {
		
	}

}

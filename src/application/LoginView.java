package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import application.ViewController.Views;

public class LoginView extends View {
	@Override
	public Parent generate() {
			BorderPane borderRoot = new BorderPane();
			Label welcome = new Label("Login");
			Label welcome2 = new Label("Not registered?");
			Label userLabel = new Label("USER");
			TextField userField = new TextField();
			Label passLabel = new Label("PASS");
			TextField passField = new TextField();
			Button loginButton = new Button("Login");
			Button createButton = new Button("Create an account");
			Button backButton = new Button("Back");
			
			
			
			HBox miniHBox = new HBox();
			miniHBox.setMinSize(200, 100);
			miniHBox.setMaxSize(200, 100);
			//miniHBox.setAlignment(Pos.CENTER);
			

			welcome.setAlignment(Pos.CENTER);
			welcome.setMinSize(200, 120);
			welcome2.setMinSize(100, 50);
			welcome2.setAlignment(Pos.CENTER);
			createButton.setMinSize(150, 50);
			createButton.setAlignment(Pos.CENTER);
			userLabel.setMinSize(200, 50);
			userLabel.setAlignment(Pos.CENTER);
			passLabel.setMinSize(200, 50);
			passLabel.setAlignment(Pos.CENTER);
			userField.setMinSize(200,50);
			//userField.setAlignment(Pos.CENTER);
			userField.setLayoutX(500);
			passField.setMinSize(200, 50);
			//passField.setAlignment(Pos.CENTER);
			welcome.setFont(Font.font("Lucida Sans Unicode", 50));
			
			loginButton.setStyle("-fx-background-color: #000000; -fx-border-color: #02114f; -fx-text-fill: #ffffff;");
			loginButton.setAlignment(Pos.CENTER);
			loginButton.setMinSize(200, 100);
			VBox topBottom = new VBox(10);
			topBottom.setStyle("-fx-border-color: #02114f;");
			topBottom.setLayoutX(400);
			//topBottom.setAlignment(Pos.CENTER);
			
			backButton.setAlignment(Pos.CENTER);
			backButton.setMinSize(200, 25);
			backButton.setOnAction(e -> {
				userField.setText("");
				passField.setText("");
				ViewController.switchView(Views.INITIAL);
			});
			
			loginButton.setOnAction(e -> {
				
				if(userField.getText().compareTo("staff") == 0) {
					ViewController.switchView(Views.STAFF_PORTAL);
				}
				
			});
			
			createButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: blue; -fx-font-size: 14px; -fx-underline: true;");
			topBottom.setMinSize(200, 200);
			miniHBox.getChildren().addAll(welcome2, createButton);
			topBottom.getChildren().addAll(welcome, miniHBox, userLabel, userField, passLabel, passField, loginButton, backButton);
			borderRoot.getChildren().add(topBottom);
			borderRoot.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #ffe485 30%, #ffe5c4 47%);");
			
			root = borderRoot;
			return root;
	}
	
	public void reset() {
		
	}
}

package application;
	
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import application.ViewController.Views;
import common_controls.CommonControls;
import common_controls.LabeledTextField;

public class LoginView extends View {
	LabeledTextField userField;
	LabeledTextField passField;
	
	private LoginType loginType;
	public enum LoginType {
		PATIENT,
		STAFF
	}
	
	@Override
	public Parent generate() {
			StackPane stackRoot = new StackPane();
			Label lblWelcome = new Label("Login");
			Label lblRegistered = new Label("Not registered?");
			Button createButton = new Button("Create an account");
			
			HBox miniHBox = new HBox();
			miniHBox.setAlignment(Pos.CENTER);
			
			// Labels
			lblWelcome.setAlignment(Pos.CENTER);
			lblWelcome.setMinSize(200, 120);
			lblWelcome.setFont(Font.font("Lucida Sans Unicode", 50));
			lblRegistered.setAlignment(Pos.CENTER);
			
			
			// Text fields
			userField = new LabeledTextField("USER");
			passField = new LabeledTextField("PASSWORD");
			userField.setOnAction(e -> passField.requestFocus());
			passField.setOnAction(e -> attemptLogin(userField.getText(), passField.getText()));
			
			// TODO: REMOVE DEBUG
			//userField.setText("ncorwins");
			//passField.setText("cmud77");
			
			
			// Buttons
			Button loginButton = CommonControls.createButton("Login", e -> attemptLogin(userField.getText(), passField.getText()));
			Button backButton = CommonControls.createButton("Back", Views.INITIAL);
			
			createButton.setOnAction(e -> ViewController.switchView(Views.CREATE_LOGIN));
			createButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: blue; -fx-font-size: 14px; -fx-underline: true;");
			
			
			VBox topBottom = new VBox(10);
			topBottom.setAlignment(Pos.CENTER);
			
			
			miniHBox.getChildren().addAll(lblRegistered, createButton);
			topBottom.getChildren().addAll(lblWelcome, miniHBox, userField.getRoot(), passField.getRoot(), loginButton, backButton);
			stackRoot.getChildren().add(topBottom);
			stackRoot.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #ffe485 30%, #ffe5c4 47%);");
			
			root = stackRoot;
			return root;
	}
	
	@Override
	public void onEnter() {
		
	}
	
	@Override
	public void reset() {
		userField.clear();
		passField.clear();
	}
	
	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
	
	private void attemptLogin(String username, String password) {
		boolean isLoginSuccess = LoginSystem.attemptLogin(username, password);
		
		if(!isLoginSuccess) {
			Alert loginAlert = new Alert(AlertType.WARNING);
			loginAlert.setHeaderText("Login unsuccessful");
			loginAlert.setContentText("Your username and/or password is incorrect.");
			loginAlert.showAndWait();
			return;
		}
		
		boolean isStaffLogin = LoginSystem.currentLogin.isStaff;
		boolean isStaffType = (loginType == LoginType.STAFF);
		
		if(isStaffLogin != isStaffType) {
			Alert loginAlert = new Alert(AlertType.WARNING);
			loginAlert.setHeaderText("Wrong login type");
			loginAlert.setContentText("You are logging in as a patient/staff member incorrectly.");
			loginAlert.showAndWait();
			
			LoginSystem.signOut();
			return;
		}
		
		System.out.println("Login successful");
		
		if(isStaffLogin) {
			ViewController.switchView(Views.STAFF_PORTAL);
		} else {
			ViewController.switchView(Views.PATIENT_PORTAL);
		}
		
		// TODO: Interface with login system to confirm credentials.
		/*LoginSystem login = new LoginSystem();
		if(loginType == LoginType.PATIENT) {
			if (login.LoadInfo(username, password).size() != 0) {
				ViewController.switchView(Views.PATIENT_PORTAL);
				System.out.println("Login Successful");
			}
			else {
				System.out.println("Login Failed");
			}

		} else if(loginType == LoginType.STAFF) {
			if (login.LoadInfo(username, password).size() != 0) {
				ViewController.switchView(Views.STAFF_PORTAL);
				System.out.println("Login Successful");
			}
			else {
				System.out.println("Login Failed");
			}
		}*/
	}
}

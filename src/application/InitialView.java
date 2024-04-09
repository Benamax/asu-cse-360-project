package application;

import application.ViewController.Views;
import common_controls.CommonControls;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InitialView extends View {
	VBox vLayout;
	HBox hBtns;
	
	Text welcomeTxt;
	
	Button btnPatient;
	Button btnStaff;
	
	@Override
	public Parent generate() {
		vLayout = new VBox(25);
		hBtns = new HBox(20);
		
		welcomeTxt = new Text("Welcome");
		welcomeTxt.setFont(Font.font("Arial", 26));
		
		btnPatient = CommonControls.createButton("Patient Login", Views.PATIENT_LOGIN);
		btnStaff = CommonControls.createButton("Staff Login", Views.STAFF_LOGIN);
		
		hBtns.getChildren().addAll(btnPatient, btnStaff);
		hBtns.setAlignment(Pos.CENTER);
		
		vLayout.getChildren().addAll(welcomeTxt, hBtns);
		vLayout.setAlignment(Pos.CENTER);
		
		root = vLayout;
		return root;
	}

	@Override
	public void reset() {
		
	}
	
}

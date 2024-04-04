package application;

import application.ViewController.Views;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PatientVisitsView extends View {
	Label lblTitle;
	
	GridPane calendarGrid;
	
	Label lblInfo;
	TextField tfInfo;
	
	Button btnExit;
	
	@Override
	public Parent generate() {
		StackPane stackPane = new StackPane();
		stackPane.setMinSize(1100, 700);
		stackPane.setPrefSize(1100, 700);
		stackPane.setMaxSize(1100, 700);
		stackPane.setPadding(new Insets(50));
		
		lblTitle = new Label("Prior Visits");
		lblTitle.setFont(Font.font("Arial", 36));
		
		calendarGrid = createCalendar();
		calendarGrid.setAlignment(Pos.BOTTOM_LEFT);
		calendarGrid.setPadding(new Insets(0, 0, 50, 50));
		
		VBox infoBox = new VBox(5);
		lblInfo = new Label("Information");
		lblInfo.setFont(Font.font("Arial", 26));
		tfInfo = new TextField();
		tfInfo.setEditable(false);
		tfInfo.setMinSize(250, 300);
		tfInfo.setPrefSize(250, 300);
		tfInfo.setMaxSize(250, 300);
		infoBox.getChildren().addAll(lblInfo, tfInfo);
		infoBox.setAlignment(Pos.CENTER_RIGHT);
		
		btnExit = new Button("Back");
		btnExit.setPrefSize(150, 50);
		btnExit.setOnAction(e -> ViewController.switchView(Views.PATIENT_PORTAL));
		
		stackPane.getChildren().addAll(lblTitle, calendarGrid, infoBox, btnExit);
		StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
		StackPane.setAlignment(infoBox, Pos.CENTER_RIGHT);
		StackPane.setAlignment(btnExit, Pos.TOP_LEFT);
		
		root = stackPane;
		return root;
	}

	@Override
	public void reset() {

	}
	
	private GridPane createCalendar() {
		GridPane calGrid = new GridPane();
		calGrid.setGridLinesVisible(true);
		
		int day = 1;
		for(int row = 0; row < 5; row++) {
			for(int column = 0; column < 7; column++) {
				DatePane datePane = new DatePane(90, Integer.toString(day++));
				calGrid.add(datePane.getPane(), column, row);
			}
		}
		
		return calGrid;
	}
	
	public class DatePane {
		StackPane stackPane;
		Label lblDate;
		
		public DatePane(int size, String text) {
			stackPane = new StackPane();
			stackPane.setMinSize(size, size);
			lblDate = new Label(text);
			
			stackPane.getChildren().add(lblDate);
		}
		
		public StackPane getPane() {
			return stackPane;
		}
	}
}

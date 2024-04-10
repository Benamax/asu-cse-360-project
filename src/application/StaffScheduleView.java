package application;

import application.ViewController.Views;
import common_controls.CommonControls;
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

public class StaffScheduleView extends View {

	Label lblTitle;
	
	GridPane calendarGrid;
	
	Label lblInfo;
	TextField tfInfo;
	
	VBox buttonsOnRight;
	
	Button btnAddEvent;
	Button btnEditEvent;
	Button btnBack;
	
	@Override
	public Parent generate() {
		StackPane stackPane = new StackPane();
		stackPane.setMinSize(1100, 700);
		stackPane.setPrefSize(1100, 700);
		stackPane.setMaxSize(1100, 700);
		stackPane.setPadding(new Insets(50));
		
		lblTitle = new Label("Schedule");
		lblTitle.setFont(Font.font("Arial", 36));
		
		
		// Calendar
		calendarGrid = createCalendar();
		calendarGrid.setAlignment(Pos.BOTTOM_LEFT);
		calendarGrid.setPadding(new Insets(0, 0, 50, 50));
		
		
		// Buttons
		btnAddEvent = CommonControls.createButton("Add Event", e -> System.out.println("TODO: Add \"AddEvent\" functionality"));
		btnEditEvent = CommonControls.createButton("Edit Event", e -> System.out.println("TODO: Add \"EditEvent\" functionality"));
		btnBack = CommonControls.createButton("Back", Views.STAFF_PORTAL);
		
		buttonsOnRight = new VBox(btnAddEvent, btnEditEvent);
		buttonsOnRight.setSpacing(10);
		buttonsOnRight.setAlignment(Pos.CENTER_RIGHT);
		
        
		StackPane.setMargin(buttonsOnRight, new Insets(15));
		
		stackPane.getChildren().addAll(lblTitle, calendarGrid, btnBack, buttonsOnRight);
		StackPane.setAlignment(lblTitle, Pos.TOP_CENTER);
		StackPane.setAlignment(btnBack, Pos.TOP_LEFT);
		
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

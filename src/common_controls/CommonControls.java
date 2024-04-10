package common_controls;

import application.ViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class CommonControls {
	public static final int BUTTON_WIDTH = 150;
	public static final int BUTTON_HEIGHT = 50;
	public static final Font BUTTON_FONT = Font.font("Arial", 14);
	
	public static Button createButton(String text, ViewController.Views view) {
		return createButton(text, e -> ViewController.switchView(view));
	}
	
	public static Button createButton(String text, ViewController.Views view, int width, int height) {
		return createButton(text, e -> ViewController.switchView(view), width, height);
	}
	
	public static Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
		return createButton(text, eventHandler, BUTTON_WIDTH, BUTTON_HEIGHT);
	}
	
	public static Button createButton(String text, EventHandler<ActionEvent> eventHandler, int width, int height) {
		Button newBtn = new Button(text);
		
		changeControlSize(newBtn, width, height);
		newBtn.setFont(BUTTON_FONT);
		newBtn.setOnAction(eventHandler);
		newBtn.setStyle("-fx-background-color: #000000; -fx-border-color: #02114f; -fx-text-fill: #ffffff;");
		
		return newBtn;
	}
	
	public static Button createUnsizedButton(String text, EventHandler<ActionEvent> eventHandler) {
		Button newBtn = new Button(text);
		
		newBtn.setFont(BUTTON_FONT);
		newBtn.setOnAction(eventHandler);
		newBtn.setStyle("-fx-background-color: #000000; -fx-border-color: #02114f; -fx-text-fill: #ffffff;");
		
		return newBtn;
	}
	
	public static void changeControlSize(Region control, int width, int height) {
		control.setMinSize(width, height);
		control.setPrefSize(width, height);
		control.setMaxSize(width, height);
	}
}

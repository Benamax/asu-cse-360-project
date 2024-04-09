package common_controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabeledTextField {
	VBox root;
	Label label;
	TextField textField;
	
	public LabeledTextField(String text) {
		this(text, 200);
	}
	
	public LabeledTextField(String text, int width) {
		root = new VBox(5);
		
		label = new Label(text);
		label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		textField = new TextField();
		textField.setMinWidth(width);
		
		root.getChildren().addAll(label, textField);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setMaxWidth(width);
	}
	
	public LabeledTextField(String text, int width, int height) {
		this(text, width);
		
		textField.setMinHeight(height);
		textField.setPrefHeight(height);
		textField.setMaxHeight(height);
	}
	
	public VBox getRoot() {
		return root;
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
	
	public void setOnAction(EventHandler<ActionEvent> eventHandler) {
		textField.setOnAction(eventHandler);
	}
	
	public void requestFocus() {
		textField.requestFocus();
	}
	
	public void clear() {
		textField.clear();
	}
}

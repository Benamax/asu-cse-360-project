package common_controls;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabeledTextArea {
	VBox root;
	Label label;
	TextArea textArea;
	
	public LabeledTextArea(String text) {
		this(text, 400, 100);
	}
	
	public LabeledTextArea(String text, int width) {
		root = new VBox(5);
		
		label = new Label(text);
		label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		textArea = new TextArea();	// TODO: Set text area size
		textArea.setMinWidth(width);
		textArea.setPrefWidth(width);
		textArea.setMaxWidth(width);
		
		root.getChildren().addAll(label, textArea);
		root.setAlignment(Pos.CENTER_LEFT);
		root.setMaxWidth(200);
	}
	
	public LabeledTextArea(String text, int width, int height) {
		this(text, width);
		
		textArea.setMinHeight(height);
		textArea.setPrefHeight(height);
		textArea.setMaxHeight(height);
	}
	
	public VBox getRoot() {
		return root;
	}
	
	public String getText() {
		return textArea.getText();
	}
	
	public void setText(String text) {
		textArea.setText(text);
	}
	
	public void clear() {
		textArea.clear();
	}
}

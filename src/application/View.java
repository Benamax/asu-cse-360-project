package application;

import javafx.scene.Parent;

public abstract class View {
	protected Parent root;
	
	public abstract Parent generate();
	public abstract void onEnter();
	public abstract void reset();
	
	public View() {
		generate();
	}
	
	public Parent getRoot() {
		return root;
	}
}

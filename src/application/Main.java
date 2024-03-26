package application;
	
import application.ViewController.Views;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static int WINDOW_WIDTH = 1100;
	private static int WINDOW_HEIGHT = 700;
	
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Project");
		
		mainScene = new Scene(new Pane(), WINDOW_WIDTH, WINDOW_HEIGHT);
		ViewController.switchView(Views.INITIAL);
		
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

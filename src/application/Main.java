package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1200,700);
			Label welcome = new Label("Welcome");
			welcome.setMinSize(1180, 100);
			welcome.setLayoutX(600);
			welcome.setLayoutY(200);
			welcome.setFont(Font.font("Lucida Sans Unicode", 50));
			Button patientLoginButton = new Button("Patient Login");
			patientLoginButton.setStyle("-fx-background-color: #000000; -fx-border-color: #02114f; -fx-text-fill: #ffffff;");

			patientLoginButton.setMinSize(200, 75);
			patientLoginButton.setFont(Font.font("Lucida Sans Unicode", 25));
			Button staffLoginButton = new Button("Staff Login");
			staffLoginButton.setStyle("-fx-background-color: #000000; -fx-border-color: #02114f; -fx-text-fill: #ffffff;");

			staffLoginButton.setMinSize(200, 75);
			staffLoginButton.setFont(Font.font("Lucida Sans Unicode", 25));
			VBox topBottom = new VBox(250);
			topBottom.setStyle("-fx-border-color: #02114f;");

			welcome.setAlignment(Pos.CENTER);
			topBottom.setMinSize(1200, 200);
			HBox splitButtons = new HBox(200);
			splitButtons.setStyle("-fx-border-color: #02114f;");
			splitButtons.setMinSize(1200, 0);
			splitButtons.setAlignment(Pos.CENTER);
			topBottom.getChildren().addAll(welcome, splitButtons);
			splitButtons.getChildren().addAll(patientLoginButton, staffLoginButton);
			root.getChildren().add(topBottom);
			root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #e9cfff 30%, #fdebff 47%);");
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

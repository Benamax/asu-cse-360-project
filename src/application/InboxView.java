package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InboxView extends View{
	
	BorderPane main;
	
	Button backToPortal;
	Button reply;
	Button newMessage;
	Button forward;
	Button backwards;
	
	Text title;
	
	int numOfMail;
	
	public Parent generate() {
		
		main = new BorderPane();
		
		title = new Text("Inbox");
		title.setFont(Font.font("Arial", FontWeight.BOLD , 26));
		
		VBox titleBox = new VBox();
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		main.setTop(titleBox);
		BorderPane.setMargin(titleBox, new Insets(50));
		
		HBox returnAndReply = new HBox(700);
		backToPortal = new Button("Back");
		reply = new Button("reply");
		backToPortal.setPrefSize(150, 50);
		reply.setPrefSize(150, 50);
		returnAndReply.getChildren().addAll(backToPortal, reply);
		returnAndReply.setPadding(new Insets(100));
		
		HBox scrollingButtons = new HBox(10);
		forward = new Button(" > ");
		backwards = new Button(" < ");
		forward.setPrefSize(150, 50);
		backwards.setPrefSize(150, 50);
		scrollingButtons.getChildren().addAll(backwards, forward);
		scrollingButtons.setPadding(new Insets(10));
		BorderPane.setMargin(scrollingButtons, new Insets(50));
		
		VBox sideButtonAlign = new VBox();
		newMessage = new Button("Compose New Message");
		newMessage.setPrefSize(125, 50);
		newMessage.setMinSize(200, 50);
		sideButtonAlign.getChildren().addAll(newMessage);
		sideButtonAlign.setPadding(new Insets(10));
		
		numOfMail = 5;
		
		ObservableList<String> items = FXCollections.observableArrayList();
		for(int i = 0; i <= numOfMail; i++) {
			items.add("Item " + i);
		}
		
		ListView<String> listView = new ListView<>(items);
        // Set selection mode to SINGLE (default)
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);

        TextField messageContents = new TextField();
        messageContents.setEditable(false);
        //messageContents.setVisible(false);
        
        // Add event handler for item selection
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Handle selection here
            System.out.println("Selected: " + newValue);
            
            if(newValue != null) {
            
            	main.setCenter(messageContents);
                messageContents.setVisible(true);
                messageContents.setText("This is item: " + newValue);
            
            } else {
            	messageContents.setVisible(false);
            }
        });
        
		
		ScrollPane mailScroller = new ScrollPane();
		mailScroller.setContent(listView) ;
		mailScroller.setFitToWidth(true);
		mailScroller.setFitToHeight(true);
		BorderPane.setMargin(mailScroller, new Insets(30));
		
        messageContents.setOnMouseClicked(event ->  {
        	main.setCenter(mailScroller);
        	messageContents.setVisible(false);
        });
		
		main.setRight(sideButtonAlign);
		main.setCenter(mailScroller);
		main.getChildren().add(messageContents);
		main.setBottom(returnAndReply);

        root = main;
        root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
		
	}
	
	public void reset() {
		
	}

}

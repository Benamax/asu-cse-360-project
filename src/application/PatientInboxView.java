package application;

import java.util.ArrayList;

import application.ViewController.Views;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PatientInboxView extends View{
	
	BorderPane main;
	ScrollPane mailScroller;
	
	VBox titleBox;
	VBox sideButtonAlign;
	VBox sendLayout;
	VBox viewingLayout;
	HBox returnAndReply;
	HBox scrollingButtons;
	
	Button backToPortal;
	Button reply;
	Button newMessage;
	Button forward;
	Button backwards;
	Button send;
	
	Text title;
	Text senderOfMail;
	Text sendTo;
	
	ListView<String> listView;
	
	TextArea messageContents;
	TextArea composeMessage;
	TextField senderEmail;
	
	int numOfMail;
	
	LoginSystem check = new LoginSystem();
	String user = check.getUsername();

	ObservableList<String> mail = FXCollections.observableArrayList();
	
private void startBackgroundUpdate() {
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), event -> {
        	user = check.getUsername();
    		if (user != "") {
        		if (new MessageSystem().loadMessages(user) != null) {
        			ArrayList<String> checker = new MessageSystem().loadMessages(user);
        			mail = FXCollections.observableArrayList(checker); 
        			listView.setItems(mail);
        		}
    		}
        })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}
	
	
	

	
	public Parent generate() {
		startBackgroundUpdate();
		main = new BorderPane();
		
		title = new Text("Inbox");
		title.setFont(Font.font("Arial", FontWeight.BOLD , 26));
		
		titleBox = new VBox();
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		main.setTop(titleBox);
		BorderPane.setMargin(titleBox, new Insets(50));
		
		returnAndReply = new HBox(300);
		backToPortal = new Button("Back");
		backToPortal.setPrefSize(150, 50);
		reply = new Button("reply");
		reply.setPrefSize(150, 50);
		send = new Button("Send");
		send.setPrefSize(150, 50);
		send.setVisible(false);
		returnAndReply.getChildren().addAll(backToPortal, send, reply);
		returnAndReply.setPadding(new Insets(50));
		
		scrollingButtons = new HBox(10);
		forward = new Button(" > ");
		backwards = new Button(" < ");
		forward.setPrefSize(150, 50);
		backwards.setPrefSize(150, 50);
		scrollingButtons.getChildren().addAll(backwards, forward);
		scrollingButtons.setPadding(new Insets(10));
		BorderPane.setMargin(scrollingButtons, new Insets(50));
		
		sideButtonAlign = new VBox();
		newMessage = new Button("Compose New Message");
		newMessage.setPrefSize(125, 50);
		newMessage.setMinSize(200, 50);
		sideButtonAlign.getChildren().addAll(newMessage);
		sideButtonAlign.setPadding(new Insets(10));

        messageContents = new TextArea();
        messageContents.setEditable(false);
		messageContents.setPrefHeight(400);
        messageContents.setPrefWidth(200); 
		BorderPane.setMargin(messageContents, new Insets(30));
		
		composeMessage = new TextArea();
		composeMessage.setEditable(true);
		composeMessage.setPrefHeight(400);
		composeMessage.setPrefWidth(200);
		BorderPane.setMargin(composeMessage, new Insets(30));

		mailScroller = new ScrollPane();             
        mailScroller.setFitToWidth(true);
		mailScroller.setFitToHeight(true);
		BorderPane.setMargin(mailScroller, new Insets(30));
		
		sendLayout = new VBox();
		BorderPane.setMargin(sendLayout, new Insets(30));
		viewingLayout = new VBox();
		BorderPane.setMargin(viewingLayout, new Insets(30));
		
		sendTo = new Text();
		senderEmail = new TextField("Example email");
		
		numOfMail = 25;

		listView = new ListView<>(mail);

        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
		mailScroller.setContent(listView);  
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            if(newValue != null) {
            	
                messageContents.setText("This is item: " + newValue);
                messageContents.setVisible(true);
                main.setCenter(messageContents);
            	
            	reply.setOnAction(e -> {	
                	send.setVisible(true);
            		
            		composeMessage.setText("This reply is to items : " + newValue);
                  	main.setCenter(sendLayout);
                  	
                	send.setOnAction(event -> {
                		if (new MessageSystem().loadMessages(user) != null && new MessageSystem().loadMessages(senderEmail.getText()) != null) {
                			// SENDER
                    		ArrayList<String> logListSender = new MessageSystem().loadMessages(user);
                    		logListSender.add(user + ": " + composeMessage.getText());
                    		new MessageSystem().addMessage(user, logListSender);
                    		
                    		// RECEIVER
                    		ArrayList<String> logListReceiever = new MessageSystem().loadMessages(senderEmail.getText());
                    		logListReceiever.add(user + ": " + composeMessage.getText());
                    		new MessageSystem().addMessage(senderEmail.getText(), logListReceiever);
                		}
                		
                		
                    	send.setVisible(false);
                    	messageContents.setVisible(true);
                    	
                    	main.setCenter(messageContents);
                	});
                  	
                });
            	
                mailScroller.setVisible(true);
 
            
            } else {
            	messageContents.setVisible(false);
            }
		
        });



        
    	sendLayout.getChildren().addAll(senderEmail, composeMessage);
    	//viewingLayout.getChildren().addAll(senderEmail, messageContents);
		

        messageContents.setOnMouseClicked(event ->  {
        	mailScroller.setVisible(true);
        	messageContents.setVisible(false);
        	main.setCenter(mailScroller);
        });
		
        newMessage.setOnAction(e -> {
        	mailScroller.setVisible(false);
        	send.setVisible(true);
        	composeMessage.setText("New message");
        	main.setCenter(sendLayout);
        	
        	send.setOnAction(event -> {
        		if (new MessageSystem().loadMessages(user) != null && new MessageSystem().loadMessages(senderEmail.getText()) != null) {
        			// SENDER
            		ArrayList<String> logListSender = new MessageSystem().loadMessages(user);
            		logListSender.add(user + ": " + composeMessage.getText());
            		new MessageSystem().addMessage(user, logListSender);
            		System.out.println(logListSender);
            		System.out.println(user);
            		// RECEIVER
            		ArrayList<String> logListReceiever = new MessageSystem().loadMessages(senderEmail.getText());
            		logListReceiever.add(user + ": " + composeMessage.getText());
            		new MessageSystem().addMessage(senderEmail.getText(), logListReceiever);
        		}
            	send.setVisible(false);
            	main.setCenter(mailScroller);
        	});
        	 mailScroller.setVisible(true);
        	
        });
        
        backToPortal.setOnAction(e -> ViewController.switchView(Views.PATIENT_PORTAL));
        
        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(mailScroller, messageContents);
        BorderPane.setMargin(centerPane, new Insets(30));
        
		main.setRight(sideButtonAlign);
		main.setCenter(centerPane);
		main.setBottom(returnAndReply);

        root = main;
        root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
		return root;
		
	}
	
	public void reset() {
		
	}

}
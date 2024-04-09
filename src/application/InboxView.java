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

public class InboxView extends View{
	
	BorderPane main;
	StackPane centerPane;
	ScrollPane mailScroller;
	
	VBox titleBox;
	VBox sideButtonAlign;
	VBox sendLayout;
	VBox viewingLayout;
	HBox returnAndReply;
	HBox scrollingButtons;
	
	Button backToPortal;
	Button back;
	Button reply;
	Button newMessage;
	Button forward;
	Button backwards;
	Button send;
	
	Text title;
	Text senderOfMail;
	Text sendTo;
	Text email;
	Text contents;
	
	ListView<String> listView;
	
	TextArea messageContents;
	TextArea composeMessage;
	TextField senderEmail;
	
	String user = LoginSystem.getCurrentUsername();
	
	ObservableList<String> mail = FXCollections.observableArrayList();
	ObservableList<String> checking = FXCollections.observableArrayList();
	
	String message;
	int switchTo = 0;

	private void startBackgroundUpdate() {
	    Timeline timeline = new Timeline(
	        new KeyFrame(Duration.seconds(1), event -> {
	    		user = LoginSystem.getCurrentUsername();
	    		if (user != "") {
	        		if (new MessageSystem().loadMessages(user) != null) {
	        			ArrayList<String> checker = new MessageSystem().loadMessages(user);
	        			checking = FXCollections.observableArrayList(checker);
	        			if(checking.equals(mail) == false) {
	        				mail = checking;
	        			}
	        			listView.setItems(mail);
	        		}
	    		}
	        })
	    );
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}
	
	public Parent generate() {
		main = new BorderPane();
		startBackgroundUpdate();
		initializeUIComponents();
		
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(listView.getSelectionModel() != null) {
	            message = outputMessage(listView.getSelectionModel().getSelectedIndex());
	            
	            if(newValue != null) {
	                newValue = null;
	                messageContents.setText(message);
	                main.setCenter(messageContents);
	            	reply.setOnAction(e -> replyFunction());      	
	            } else {
	            	main.setCenter(mailScroller); 
	            } } } );
		
		messageContents.setOnMouseClicked(event -> main.setCenter(mailScroller));
		
		newMessage.setOnAction(e -> {
	    	returnAndReply.getChildren().removeAll(backToPortal, send, reply);
	    	returnAndReply.getChildren().addAll(back, send, reply);
	    	
			send.setVisible(true);
			title.setText("Send New Message");
			composeMessage.setText("");
			main.setCenter(sendLayout);
        	
			back.setOnAction(event ->{;
		        	send.setVisible(false);
		        	title.setText("Staff Inbox");
		        	main.setCenter(messageContents);
		        	returnAndReply.getChildren().removeAll(back, send, reply);
		        	returnAndReply.getChildren().addAll(backToPortal, send, reply);
			});
			
			send.setOnAction(event -> { 
				sendButtonMethod();
				main.setCenter(mailScroller);
			});
			mailScroller.setVisible(true);
		});
        
		backToPortal.setOnAction(event ->{
			listView.getSelectionModel().clearSelection();
			mailScroller.setContent(listView);
			ViewController.switchView(Views.STAFF_PORTAL);
		});
		
		
     	centerPane = new StackPane();
     	centerPane.getChildren().addAll(mailScroller, messageContents);
     	BorderPane.setMargin(centerPane, new Insets(30));
        
	 	main.setRight(sideButtonAlign);
	 	main.setCenter(centerPane);
	 	main.setBottom(returnAndReply);

     	root = main;
     	root.setStyle("-fx-background-color: linear-gradient(from 41px 34px to 50px 50px, reflect,  #a1ffd3 30%, #ffe5c4 47%);");
	 	return root;
		
	}
	
	private void replyFunction() {
		title.setText("Replying");
    	send.setVisible(true);
    	
    	senderEmail.setText(getSender(message));
    	
    	returnAndReply.getChildren().removeAll(backToPortal, send, reply);
    	returnAndReply.getChildren().addAll(back, send, reply);
    	
		composeMessage.setText("");
      	main.setCenter(sendLayout);
      	
    	send.setOnAction(event -> {
    		
    		sendButtonMethod();
        	title.setText("Staff Inbox");
        	main.setCenter(messageContents); });
    	
    	back.setOnAction(event ->{
    		//backButtonHandler("REMAIN");
        	send.setVisible(false);
        	title.setText("Staff Inbox");
        	main.setCenter(messageContents);
        	returnAndReply.getChildren().removeAll(back, send, reply);
        	returnAndReply.getChildren().addAll(backToPortal, send, reply);
    		});
	}
	
	private String getMail(String messageString) {
		
		int colonIndex = messageString.indexOf(':');
		String message = new String();
		
		if(colonIndex != -1) {
			message = messageString.substring(colonIndex + 1);
			message = message.trim();
		}
		
		return message;
	}
	
	private String getSender(String messageString) {
		
		int colonIndex = messageString.indexOf(':');
		String sender = new String();
		
		if(colonIndex != -1) {
			sender = messageString.substring(0, colonIndex);
		}
		return sender;
	}
	
	private void sendButtonMethod() {
		if (new MessageSystem().loadMessages(user) != null && new MessageSystem().loadMessages(senderEmail.getText()) != null) {
			// SENDER
    		ArrayList<String> logListSender = new MessageSystem().loadMessages(user);
    		logListSender.add(user + ": " + composeMessage.getText());
    		new MessageSystem().addMessage(user, logListSender);
    		
    		// RECEIVER
    		ArrayList<String> logListReceiever = new MessageSystem().loadMessages(senderEmail.getText());
    		logListReceiever.add(user + ": " + composeMessage.getText());
    		new MessageSystem().addMessage(senderEmail.getText(), logListSender);
		}
    	send.setVisible(false);
	}
	
	private String outputMessage(int index) {
		
		if(index < 0) {
			return "Err";
		}
		
		String messageToSend = new String();
		messageToSend = mail.get(index);
		return messageToSend;
		
	}
	
	private void initializeUIComponents() {
		title = new Text("Staff Inbox");
		title.setFont(Font.font("Arial", FontWeight.BOLD , 26));
		
		titleBox = new VBox();
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		main.setTop(titleBox);
		BorderPane.setMargin(titleBox, new Insets(50));
		
		returnAndReply = new HBox(300);
		backToPortal = new Button("Back");
		backToPortal.setPrefSize(150, 50);
		back = new Button("Back");
		back.setPrefSize(150, 50);
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
		senderEmail = new TextField("");
		email = new Text("Email: ");
		contents = new Text("Write Your Message: ");
		
		listView = new ListView<>(mail);
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    	
        sendLayout.getChildren().addAll(email, senderEmail, contents, composeMessage);
        mailScroller.setContent(listView);
		
	}
	
	
	public void reset() {
		
	}

}

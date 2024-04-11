package application;

import java.util.ArrayList;
import java.util.List;

import application.ViewController.Views;
import common_controls.CommonControls;
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
	StackPane centerPane;
	
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
	TextArea messageTitle;
	TextField senderEmail;
	
	LoginSystem check = new LoginSystem();
	String user = LoginSystem.getCurrentUsername();

	ObservableList<Message> mail = FXCollections.observableArrayList();
	ObservableList<Message> checking = FXCollections.observableArrayList();
	ObservableList<String> parseMail = FXCollections.observableArrayList();
	String message;
	int switchTo = 0;
	
	private void startBackgroundUpdate() {
	    Timeline timeline = new Timeline(
	        new KeyFrame(Duration.seconds(1), event -> {
	        	user = LoginSystem.getCurrentUsername();
	    		if (user != "") {
	    			if(!MessageSystem.loadMessages(user).isEmpty()) {
	    				List<Message> msgContents = MessageSystem.loadMessages(user);
	    				List<String> msgNames = MessageSystem.loadMessageNames(user);
	    				checking = FXCollections.observableArrayList(msgContents);
	    				parseMail = FXCollections.observableArrayList();

	    				for (int i = 0; i < msgContents.size(); i++) {
	    					String parse = "";
	    					
	    					parse += ("Sender: \t" + msgContents.get(i).sender + "\n");
	    					parse += ("Receiver: \t" + msgContents.get(i).recepient + "\n");
	    					parse += ("Title: \t" + msgContents.get(i).title + "\n");
	    					parse += ("Message: \n" + msgContents.get(i).content + "\n");
	    					
	    					parseMail.add(parse);
	    				}

	    				listView.setItems(parseMail);
	    			}
	        		/*if (new MessageSystem().loadMessages(user) != null) {
	        			ArrayList<String> checker = new MessageSystem().loadMessages(user);
	        			checking = FXCollections.observableArrayList(checker);
	        			if(checking.equals(mail) == false) {
	        				mail = checking;
	        			}
	        			listView.setItems(mail);
	        		}*/
	    		}
	        })
	    );
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}

	public Parent generate() {
		messageTitle = new TextArea(" ");
		main = new BorderPane();
		startBackgroundUpdate();
		initializeUIComponents();
	
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			message = new String(outputMessage(listView.getSelectionModel().getSelectedIndex()));
		           
	        if(newValue != null) {
	        	newValue = null;
	            messageContents.setText(message);
	            main.setCenter(messageContents);
	            reply.setOnAction(e -> replyFunction());
	            //mailScroller.setVisible(true);
	            
	          } else {
	        	//backToPortal.messageContents.setVisible(false);
	        	  main.setCenter(mailScroller);
	          }
	    });
	
		messageContents.setOnMouseClicked(event ->  {
			//mailScroller.setVisible(true);
			//messageContents.setVisible(false);
			main.setCenter(mailScroller);
	    });
			
		newMessage.setOnAction(e -> {
	    	returnAndReply.getChildren().removeAll(backToPortal, send, reply);
	    	returnAndReply.getChildren().addAll(back, send, reply);
	    	
			//mailScroller.setVisible(false);
			title.setText("Send New Message");
			send.setVisible(true);
			composeMessage.setText("");
			messageTitle.setText("");
			main.setCenter(sendLayout);
	        
			back.setOnAction(event ->{
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
			ViewController.switchView(Views.PATIENT_PORTAL);
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
	
	private void sendButtonMethod() {
		MessageSystem.sendMessage(messageTitle.getText(), composeMessage.getText(), senderEmail.getText());
		
		/*if (new MessageSystem().loadMessages(user) != null && new MessageSystem().loadMessages(senderEmail.getText()) != null) {
			// SENDER
    		ArrayList<String> logListSender = new MessageSystem().loadMessages(user);
    		logListSender.add(user + ": " + composeMessage.getText());
    		new MessageSystem().addMessage(user, logListSender);
    		
    		// RECEIVER
    		ArrayList<String> logListReceiever = new MessageSystem().loadMessages(senderEmail.getText());
    		logListReceiever.add(user + ": " + composeMessage.getText());
    		new MessageSystem().addMessage(senderEmail.getText(), logListSender);
		}*/
    	send.setVisible(false);
	}
	
	private void replyFunction() {
		title.setText("Replying");
    	send.setVisible(true);
    	
    	returnAndReply.getChildren().removeAll(backToPortal, send, reply);
    	returnAndReply.getChildren().addAll(back, send, reply);
    	
    	senderEmail.setText(getSender(message));
		composeMessage.setText("");
		messageTitle.setText("");
      	main.setCenter(sendLayout);
      	
    	send.setOnAction(event -> {
    		sendButtonMethod();
        	title.setText("Patient Inbox");
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
	
	private String getSender(String messageString) {
		
		int colonIndex = messageString.indexOf(':');
		String sender = new String();
		
		if(colonIndex != -1) {
			sender = messageString.substring(0, colonIndex);
		}
		return sender;
	}
	
	private String outputMessage(int index) {
		
		if(index < 0) {
			return "Err";
		}
		
		String messageToSend = new String();
		
		messageToSend = parseMail.get(index);
		
		return messageToSend;
		
	}
	
	private void initializeUIComponents() {
		
		title = new Text("Patient Inbox");
		title.setFont(Font.font("Arial", FontWeight.BOLD , 26));
		
		titleBox = new VBox();
		titleBox.getChildren().add(title);
		titleBox.setAlignment(Pos.CENTER);
		main.setTop(titleBox);
		BorderPane.setMargin(titleBox, new Insets(50));
		
		returnAndReply = new HBox(300);
		backToPortal = CommonControls.createButton("Back", e ->{
			listView.getSelectionModel().clearSelection();
			mailScroller.setContent(listView);
			ViewController.switchView(Views.PATIENT_PORTAL);
		});
		back = CommonControls.createButton("Back", e -> {
			send.setVisible(false);
			title.setText("Staff Inbox");
    		main.setCenter(messageContents);
    		returnAndReply.getChildren().removeAll(back, send, reply);
    		returnAndReply.getChildren().addAll(backToPortal, send, reply);
		});
		reply = CommonControls.createButton("Reply", e -> replyFunction());
		send = CommonControls.createButton("Send", e -> sendButtonMethod());
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
		newMessage = CommonControls.createButton("Compose New Message", e -> System.out.println());
		//newMessage = new Button("Compose New Message");
		//newMessage.setPrefSize(125, 50);
		//newMessage.setMinSize(200, 50);
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
		
		messageTitle = new TextArea();
		messageTitle.setEditable(true);
		messageTitle.setPrefHeight(100);
		messageTitle.setPrefWidth(200);
		BorderPane.setMargin(messageTitle, new Insets(30));
		
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

		listView = new ListView<>(parseMail);
	    listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
	    
		sendLayout.getChildren().addAll(email, senderEmail, contents, messageTitle, composeMessage);
		mailScroller.setContent(listView);  
		
	}
	
	@Override
	public void onEnter() {
		
	}
	
	public void reset() {
		
	}

}

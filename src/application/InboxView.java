package application;

import java.util.ArrayList;

import application.ViewController.Views;
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

public class InboxView extends View{
	
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
	ObservableList<String> items;
	
	TextArea messageContents;
	TextArea composeMessage;
	TextField senderEmail;
	
	int numOfMail;
	
	public Parent generate() {
		
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
		
		items = FXCollections.observableArrayList();
		for(int i = 0; i <= numOfMail; i++) {
			items.add("Item " + i);
		}
		
		listView = new ListView<>(items);
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
		mailScroller.setContent(listView);  
        
    	sendLayout.getChildren().addAll(senderEmail, composeMessage);
    	//viewingLayout.getChildren().addAll(senderEmail, messageContents);
		
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected: " + newValue);
            
            if(newValue != null) {
            	
                messageContents.setText("This is item: " + newValue);
                messageContents.setVisible(true);
                main.setCenter(messageContents);
            	
            	reply.setOnAction(e -> {	
                	send.setVisible(true);
            		
            		composeMessage.setText("This reply is to items : " + newValue);
                  	main.setCenter(sendLayout);
                  	
                	send.setOnAction(event -> {
                		MessageSystem sendmsg = new MessageSystem();
                		ArrayList<String> logs = sendmsg.loadMessages(senderEmail.getText());
                		logs.add("D: " + composeMessage.getText());
                		System.out.print(logs);
                		sendmsg.addMessage(senderEmail.getText(), logs);
                		
                		
                		
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
        		MessageSystem sendmsg = new MessageSystem();
        		ArrayList<String> logs = sendmsg.loadMessages(senderEmail.getText());
        		logs.add("D: " + composeMessage.getText());
        		System.out.print(logs);
        		sendmsg.addMessage(senderEmail.getText(), logs);
            	send.setVisible(false);
            	main.setCenter(mailScroller);
        	});
        	 mailScroller.setVisible(true);
        	
        });
        
        backToPortal.setOnAction(e -> ViewController.switchView(Views.STAFF_PORTAL));
        
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

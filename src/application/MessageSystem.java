package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageSystem {
	public static void sendMessage(String title, String content, String recepient) {
		Message newMsg = new Message();
		newMsg.title = title;
		newMsg.content = content;
		newMsg.sender = LoginSystem.currentLogin.username;
		newMsg.recepient = recepient;
		
		newMsg.save();
	}
	
	public static List<Message> loadMessages(String user) {
		File msgFolder = new File("src\\Messages");
		File[] msgFiles = msgFolder.listFiles();
		
		System.out.println("MessageSystem: Trying to find messages for user " + user);
		
		List<Message> acceptedMsgs = new ArrayList<Message>();
		for(File msgFile : msgFiles) {
			String[] nameSegments = msgFile.getName().split("_");
			
			if(nameSegments.length != 3)
				continue;
			
			String sender = nameSegments[0];
			String recepient = nameSegments[1];
			int number = Integer.parseInt(nameSegments[2].substring(0, nameSegments[2].indexOf('.')));
			if(sender.equals(user) || recepient.equals(user)) {
				Message newMsg = new Message(sender, recepient);
				newMsg.load(number);
				acceptedMsgs.add(newMsg);
				//System.out.printf("Message %s -> %s #%d: %s, %s\n", sender, recepient, number, newMsg.title, newMsg.content);
			}
		}
		
		acceptedMsgs.sort((o1, o2) -> Long.compare(o1.epochTimeSent, o2.epochTimeSent));
		return acceptedMsgs.reversed();
	}
	
	public static List<String> loadMessageNames(String user) {
		List<Message> acceptedMsgs = loadMessages(user);
		List<String> acceptedMsgNames = acceptedMsgs.stream().map((msg) -> msg.title).collect(Collectors.toList());
		return acceptedMsgNames;
	}
}

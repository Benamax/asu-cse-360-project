package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Message {
	String title;
	String content;
	String sender;
	String recepient;
	long epochTimeSent;
	
	public Message() {
		this("", "");
	}
	
	public Message(String sender, String recepient) {
		this.title = "";
		this.content = "";
		this.sender = sender;
		this.recepient = recepient;
		this.epochTimeSent = 0;
	}
	
	public void save() {
		int i = 0;
		String filepath;
		File file;
		
		while(true) {
			filepath = String.format("src\\Messages\\%s_%s_%d.txt", sender, recepient, i++);
			file = new File(filepath);
			
			if(!file.exists())
				break;
		}
		
		try {
			if(file.createNewFile()) {
				System.out.println("Message: Wrote new file at " + filepath);
			} else {
				System.out.println("Message: Overwrote existing file at " + filepath);
			}
		} catch(IOException e) {
			System.err.println("Message: Unable to save message to " + filepath);
			return;
		}
		
		try {
			FileWriter fw = new FileWriter(filepath);
			
			fw.write(this.title + "\n");
			fw.write(encodeNewline(this.content) + "\n");
			
			this.epochTimeSent = file.lastModified();
			fw.write(this.epochTimeSent + "\n");
			
			fw.close();
		} catch(IOException e) {
			System.err.println("Message: Unable to save message to " + filepath);
		}
	}
	
	public void load(int number) {
		String filepath = String.format("src\\Messages\\%s_%s_%d.txt", sender, recepient, number);
		File file = new File(filepath);
		
		try {
			Scanner scnr = new Scanner(file);
			
			this.title = scnr.nextLine();
			this.content = decodeNewline(scnr.nextLine());
			this.epochTimeSent = scnr.nextLong();
			
			scnr.close();
		} catch(FileNotFoundException e) {
			System.err.println("Message: Failed to load message at " + filepath);
			return;
		}
	}
	
	private String encodeNewline(String in) {
		return in.replace("\n", "\\n");
	}
	
	private String decodeNewline(String in) {
		return in.replace("\\n", "\n");
	}
}

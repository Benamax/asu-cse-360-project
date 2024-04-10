package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javafx.scene.control.TextField;

public class MessageSystem {
	public static String username = "";
	public static void addMessage(String filename, ArrayList<String> chatLogs) {
		try {													   
			String filepath = "src\\Messages\\" + filename + ".txt";
			File f = new File(filepath);
			if(f.createNewFile()) {
				System.out.println("\n\n\n\n\nFile created: " + f.getName() + "\n\n\n\n");
			} else {
				System.out.println("File already exists.");
			}
			FileWriter writer = new FileWriter(filepath);
			for (int i = 0; i < chatLogs.size(); i++) {
				writer.write(chatLogs.get(i) + "\n");
			}
			/* this is for converting the calendar value to a date
			if (apptDate.getValue() != null) {
				LocalDate localDate = apptDate.getValue(); 
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())); 
				Date date = Date.from(instant);
				writer.write(date.toString()); 
			}*/
			
			writer.close();
		} catch(FileNotFoundException e) {
			System.out.println("No file found.");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IO exception.");
			e.printStackTrace();
		}
	}
	public static ArrayList<String> loadMessages(String filename) {
		ArrayList<String> allData = new ArrayList<String>();
		try {
			String filepath = "src\\Messages\\" + filename + ".txt";
			File f = new File(filepath);
			allData = new ArrayList<String>();
			Scanner scnr = new Scanner(f);
			while (scnr.hasNextLine()) {
				String str = scnr.nextLine();
				allData.add(str);
			}
			/*
				for (int i = 0; i < 6; i++) {
					String str = scnr.nextLine(); // loading in the patient info
					if (str != "" && str != null) {
						allData.add(str); 
					}
				}
				for (int i = 0; i < 6; i++) {
					System.out.println(allData.get(i)); 
				}*/
			scnr.close();
		} catch(FileNotFoundException e) {
			System.out.println("No file found.");
			//e.printStackTrace();
			return allData;
		}
		return allData;
	}
	
}

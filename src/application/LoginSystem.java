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

public class LoginSystem {
	public static String username = "";
	public void SaveInfo(String filename, String[] fieldText) {
		try {													   
			String filepath = "src\\Logins\\" + filename + ".txt";
			File f = new File(filepath);
			if(f.createNewFile()) {
				System.out.println("\n\n\n\n\nFile created: " + f.getName() + "\n\n\n\n");
				MessageSystem newMSG = new MessageSystem();
				ArrayList<String> new_person = new ArrayList<String>();
				new_person.add("Welcome!");
				newMSG.addMessage(filename, new_person);
			} else {
				System.out.println("File already exists.");
			}
			FileWriter writer = new FileWriter(filepath);
			for (int i = 0; i < fieldText.length; i++) {
				writer.write(fieldText[i] + "\n");
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
	public ArrayList LoadInfo(String filename, String password) {
		ArrayList<String> allData = new ArrayList<String>();
		try {
			String filepath = "src\\Logins\\" + filename + ".txt";
			File f = new File(filepath);
			allData = new ArrayList<String>();
			Scanner scnr = new Scanner(f);
			String str = scnr.nextLine();
			if (!str.equals(password)) {
				scnr.close();
				return new ArrayList<String>();
			}
			allData.add(str);
			username = filename;
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
			e.printStackTrace();
			return allData;
		}
		return allData;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String str) {
		username = str;
	}
	
}

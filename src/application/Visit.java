package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Visit {
	String title;
	LocalDateTime date;
	
	String patientID;
	
	String patientName;
	String weight;
	String height;
	String bodyTemperature;
	String bloodPressure;
	
	String reasonFor;
	String phyExam;
	String notes;
	
	public Visit(String title) {
		this.title = title;
		date = LocalDateTime.now();
		
		patientName = "";
		weight = "";
		height = "";
		bodyTemperature = "";
		bloodPressure = "";
		
		reasonFor = "";
		phyExam = "";
		notes = "";
	}
	
	public void save() {
		int i = 0;
		String filepath;
		File file;
		
		while(true) {
			filepath = String.format("src\\Visits\\%s_%d.txt", patientID, i++);
			file = new File(filepath);
			
			if(!file.exists())
				break;
		}
		
		try {
			if(file.createNewFile()) {
				System.out.println("Visit: Created new file at " + filepath);
			} else {
				System.out.println("Visit: Saving to existing file at " + filepath);
			}
		} catch(IOException e) {
			System.err.println("Visit: Unable to save file to " + filepath);
			e.printStackTrace();
		}
		
		try {
			FileWriter fw = new FileWriter(filepath);
			
			fw.write(this.date.toString() + "\n");
			
			fw.write(this.patientName + "\n");
			fw.write(this.weight + "\n");
			fw.write(this.height + "\n");
			fw.write(this.bodyTemperature + "\n");
			fw.write(this.bloodPressure + "\n");
			
			fw.write(encodeNewline(this.reasonFor) + "\n");
			fw.write(encodeNewline(this.phyExam) + "\n");
			fw.write(encodeNewline(this.notes) + "\n");
			
			fw.close();
		} catch(IOException e) {
			System.err.println("Visit: Unable to save file to " + filepath);
			e.printStackTrace();
		}
	}
	
	public void load(int number) {
		String filepath = String.format("src\\Visits\\%s_%d.txt", patientID, number);
		File file = new File(filepath);
		
		try {
			Scanner scnr = new Scanner(file);
			
			this.date = LocalDateTime.parse(scnr.nextLine());
			
			this.patientName = scnr.nextLine();
			this.weight = scnr.nextLine();
			this.height = scnr.nextLine();
			this.bodyTemperature = scnr.nextLine();
			this.bloodPressure = scnr.nextLine();
			
			this.reasonFor = decodeNewline(scnr.nextLine());
			this.phyExam = decodeNewline(scnr.nextLine());
			this.notes = decodeNewline(scnr.nextLine());
			
			scnr.close();
		} catch(FileNotFoundException e) {
			System.err.println("Patient: Unable to load file from " + filepath);
			e.printStackTrace();
		}
	}
	
	private String encodeNewline(String in) {
		return in.replace("\n", "\\n");
	}
	
	private String decodeNewline(String in) {
		return in.replace("\\n", "\n");
	}
}

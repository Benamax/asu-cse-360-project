package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HexFormat;
import java.util.Scanner;

public class Patient {
	String firstName;
	String lastName;
	String dob;
	
	String homeAddress;
	String phoneNumber;
	String email;
	
	String allergies;
	String healthConcerns;
	String priorIssues;
	String medications;
	String records;
	
	public Patient() {
		this("", "", "");
	}
	
	public Patient(String first, String last, String dob) {
		this.firstName = first;
		this.lastName = last;
		this.dob = dob;
		this.homeAddress = "";
		this.phoneNumber = "";
		this.email = "";
		this.allergies = "";
		this.healthConcerns = "";
		this.priorIssues = "";
		this.medications = "";
		this.records = "";
	}
	
	public static String generateID(String first, String last, String dob) {
		String userID = "";
		userID += first.toUpperCase().substring(0, 3);
		userID += last.toUpperCase().substring(0, 2);
		
		String[] dobNumbers = dob.split("/");
		HexFormat hex = HexFormat.of();
		userID += hex.toHexDigits(Byte.parseByte(dobNumbers[0])).toUpperCase().substring(1, 2);
		userID += hex.toHexDigits(Byte.parseByte(dobNumbers[1])).toUpperCase();
		userID += hex.toHexDigits((byte)(Integer.parseInt(dobNumbers[2]) % 100)).toUpperCase();
		
		return userID;
	}
	
	public static boolean exists(String patientID) {
		String filepath = "src\\Patients\\" + patientID + ".txt";
		File file = new File(filepath);
		return file.exists();
	}
	
	public String getPatientID() {
		return generateID(this.firstName, this.lastName, this.dob);
	}
	
	public void save(String patientID) {
		String filepath = "src\\Patients\\" + patientID + ".txt";
		File file = new File(filepath);
		
		try {
			if(file.createNewFile()) {
				System.out.println("Patient: Created new file at " + filepath);
			} else {
				System.out.println("Patient: Saving to existing file at " + filepath);
			}
		} catch(IOException e) {
			System.err.println("Patient: Unable to save file to " + filepath);
			e.printStackTrace();
		}
		
		try {
			FileWriter fw = new FileWriter(filepath);
			
			fw.write(this.firstName + "\n");
			fw.write(this.lastName + "\n");
			fw.write(this.dob + "\n");
			
			fw.write(this.homeAddress + "\n");
			fw.write(this.phoneNumber + "\n");
			fw.write(this.email + "\n");
			
			fw.write(encodeNewline(this.allergies) + "\n");
			//fw.write(this.allergies + "\n");
			fw.write(encodeNewline(this.healthConcerns) + "\n");
			fw.write(encodeNewline(this.priorIssues) + "\n");
			fw.write(encodeNewline(this.medications) + "\n");
			fw.write(encodeNewline(this.records) + "\n");
			
			fw.close();
		} catch(IOException e) {
			System.err.println("Patient: Unable to save file to " + filepath);
			e.printStackTrace();
		}
	}
	
	public void load(String patientID) {
		String filepath = "src\\Patients\\" + patientID + ".txt";
		File file = new File(filepath);
		
		try {
			Scanner scnr = new Scanner(file);
			
			this.firstName = scnr.nextLine();
			this.lastName = scnr.nextLine();
			this.dob = scnr.nextLine();
			
			this.homeAddress = scnr.nextLine();
			this.phoneNumber = scnr.nextLine();
			this.email = scnr.nextLine();
			
			/*while(scnr.hasNext()) {
				String next = scnr.next();
				
				if(next.contains("ENDL"))
					break;
				
				this.allergies += next;
			}*/
			this.allergies = decodeNewline(scnr.nextLine());
			//this.allergies = scnr.nextLine();
			this.healthConcerns = decodeNewline(scnr.nextLine());
			this.priorIssues = decodeNewline(scnr.nextLine());
			this.medications = decodeNewline(scnr.nextLine());
			this.records = decodeNewline(scnr.nextLine());
			
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

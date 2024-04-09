package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
	String username;
	String password;
	String patientID;
	boolean isStaff;
	
	public Login() {
		
	}
	
	public Login(String username, String password, String patientID, boolean isStaff) {
		this.username = username;
		this.password = password;
		this.patientID = patientID;
		this.isStaff = isStaff;
	}
	
	public Patient getPatient() {
		Patient patient = new Patient();
		patient.load(patientID);
		return patient;
	}
	
	public static boolean exists(String username) {
		String filepath = "src\\Logins\\" + username + ".txt";
		File file = new File(filepath);
		return file.exists();
	}
	
	public void save(String username) {
		String filepath = "src\\Logins\\" + username + ".txt";
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
			
			fw.write(this.password + "\n");
			fw.write(this.patientID + "\n");
			fw.write(this.isStaff + "\n");
			
			fw.close();
		} catch(IOException e) {
			System.err.println("Patient: Unable to save file to " + filepath);
			e.printStackTrace();
		}
	}
	
	public void load(String username) {
		String filepath = "src\\Logins\\" + username + ".txt";
		File file = new File(filepath);
		
		try {
			Scanner scnr = new Scanner(file);
			
			this.username = username;
			this.password = scnr.nextLine();
			this.patientID = scnr.nextLine();
			this.isStaff = scnr.nextBoolean();
			
			scnr.close();
		} catch(FileNotFoundException e) {
			System.err.println("Patient: Unable to load file from " + filepath);
			e.printStackTrace();
		}
	}
}

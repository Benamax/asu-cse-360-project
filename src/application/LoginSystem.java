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
	public static Login currentLogin;
	
	public static boolean attemptLogin(String username, String password) {
		if(!Login.exists(username))
			return false;
		
		Login newLogin = new Login();
		newLogin.load(username);
		
		if(!newLogin.password.equals(password))
			return false;
		
		if(!newLogin.isStaff) {
			Patient newPatient = new Patient();
			newPatient.load(newLogin.patientID);
			PatientSystem.currentPatient = newPatient;
		}
			
		currentLogin = newLogin;
		return true;
	}
	
	public static void signOut() {
		currentLogin = null;
		PatientSystem.currentPatient = null;
	}
	
	public static String getCurrentUsername() {
		if(currentLogin == null)
			return "";
		
		return currentLogin.username;
	}
}

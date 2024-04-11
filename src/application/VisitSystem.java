package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VisitSystem {
	public static void addVisit(Visit visit) {
		visit.save();
	}
	
	public static List<Visit> loadVisits(String patientID) {
		File visitFolder = new File("src\\Visits");
		File[] visitFiles = visitFolder.listFiles();
		
		System.out.println("VisitSystem: Trying to find messages for patient " + patientID);
		
		List<Visit> acceptedVisits = new ArrayList<Visit>();
		for(File visitFile : visitFiles) {
			String[] nameSegments = visitFile.getName().split("_");
			
			if(nameSegments.length != 2)
				continue;
			
			String fileID = nameSegments[0];
			int number = Integer.parseInt(nameSegments[1].substring(0, nameSegments[1].indexOf('.')));
			if(fileID.equals(patientID)) {
				Visit newVisit = new Visit();
				newVisit.patientID = fileID;
				newVisit.load(number);
				acceptedVisits.add(newVisit);
			}
		}
		
		acceptedVisits.sort((o1, o2) -> o1.date.compareTo(o2.date));
		return acceptedVisits.reversed();
	}
	
	public static List<String> loadVisitNames(String patientID) {
		List<Visit> acceptedVisits = loadVisits(patientID);
		List<String> acceptedVisitNames = acceptedVisits.stream().map((msg) -> msg.title).collect(Collectors.toList());
		return acceptedVisitNames;
	}
}

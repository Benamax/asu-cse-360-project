package application;

import application.LoginView.LoginType;

public class ViewController {
	private static View INITIAL_VIEW = new InitialView();
	private static View LOGIN_VIEW = new LoginView();
	private static View STAFF_PORTAL_VIEW = new StaffPortalView();
	private static View PATIENT_LOOKUP_VIEW = new PatientLookupView();
	private static View ADD_PATIENT_VIEW = new NewPatientView();
	private static View PATIENT_PORTAL_VIEW = new PatientPortalView();
	private static View PATIENT_VISITS_VIEW = new PatientVisitsView();
	private static View INBOX_VIEW = new InboxView();
	private static View PATIENT_INFO_VIEW = new PatientInformationView();
	private static View EDIT_VISIT_VIEW = new EditVisitView();
	
	private static View CURRENT_VIEW = null;
	
	public enum Views {
		INITIAL,
		PATIENT_LOGIN,
		PATIENT_PORTAL,
		PATIENT_VISITS,
		STAFF_LOGIN,
		STAFF_PORTAL,
	    PATIENT_LOOKUP,
	    ADD_PATIENT,
	    INBOX,
	    PATIENT_INFO,
	    EDIT_VISIT
	}
	
	public static void switchView(Views newView) {
		View view = null;
		System.out.println("ViewController: Switching to " + newView.toString());
		
		switch(newView) {
			case INITIAL:
				view = INITIAL_VIEW;
				break;
			case PATIENT_LOGIN:
				view = LOGIN_VIEW;
				((LoginView)LOGIN_VIEW).setLoginType(LoginType.PATIENT);
				break;
			case STAFF_LOGIN:
				view = LOGIN_VIEW;
				((LoginView)LOGIN_VIEW).setLoginType(LoginType.STAFF);
				break;
			case STAFF_PORTAL:
				view = STAFF_PORTAL_VIEW;
				break;
		   case PATIENT_LOOKUP:
			    view = PATIENT_LOOKUP_VIEW;
			    break;
		   case ADD_PATIENT:
			   view = ADD_PATIENT_VIEW;
			   break;
			case PATIENT_PORTAL:
				view = PATIENT_PORTAL_VIEW;
				break;
			case PATIENT_VISITS:
				view = PATIENT_VISITS_VIEW;
				break;
			case INBOX:
				view = INBOX_VIEW;
				break;
			case PATIENT_INFO:
				view = PATIENT_INFO_VIEW;
				break;
			case EDIT_VISIT:
				view = EDIT_VISIT_VIEW;
				break;
			default:
				System.out.println("ViewController: No view option for " + newView.toString());
				break;
		}
		
		if(view == null)
			return;
		
		if(CURRENT_VIEW != null)
			CURRENT_VIEW.reset();
		
		CURRENT_VIEW = view;
		Main.mainScene.setRoot(view.getRoot());
	}
}

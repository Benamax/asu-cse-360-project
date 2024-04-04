package application;

public class ViewController {
	private static View INITIAL_VIEW = new InitialView();
	private static View LOGIN_VIEW = new LoginView();
	private static View STAFF_PORTAL_VIEW = new StaffPortalView();
	private static View PATIENT_LOOKUP_VIEW = new PatientLookupView();
<<<<<<< Updated upstream
=======
	private static View INBOX_VIEW = new InboxView();
	private static View ADD_PATIENT_VIEW = new NewPatientView();
>>>>>>> Stashed changes
	
	private static View CURRENT_VIEW = null;
	
	public enum Views {
		INITIAL,
		PATIENT_LOGIN,
		STAFF_LOGIN,
		PATIENT_PORTAL,
		STAFF_PORTAL,
<<<<<<< Updated upstream
	    PATIENT_LOOKUP
=======
	    PATIENT_LOOKUP,
	    INBOX,
	    ADD_PATIENT
>>>>>>> Stashed changes
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
				break;
			case STAFF_LOGIN:
				view = LOGIN_VIEW;
				break;
			case STAFF_PORTAL:
				view = STAFF_PORTAL_VIEW;
				break;
		   case PATIENT_LOOKUP:
			    view = PATIENT_LOOKUP_VIEW;
			    break;
<<<<<<< Updated upstream
=======
		   case INBOX:
			   view = INBOX_VIEW;
			   break;
		   case ADD_PATIENT:
			   view = ADD_PATIENT_VIEW;
>>>>>>> Stashed changes
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

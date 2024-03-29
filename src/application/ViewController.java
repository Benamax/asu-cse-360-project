package application;

public class ViewController {
	private static View INITIAL_VIEW = new InitialView();
	private static View LOGIN_VIEW = new LoginView();
	private static View STAFF_PORTAL_VIEW = new StaffPortalView();
	
	private static View CURRENT_VIEW = null;
	
	public enum Views {
		INITIAL,
		PATIENT_LOGIN,
		STAFF_LOGIN,
		PATIENT_PORTAL,
		STAFF_PORTAL
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

/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.*;

/**
 * 
 */
class Tests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void loginValidPatientTest() {
		assertTrue(LoginSystem.attemptLogin("Benamax", "pass"));
	}
	
	@Test
	void loginValidStaffTest() {
		assertTrue(LoginSystem.attemptLogin("DOCTOR", "cmud77"));
	}
	
	@Test
	void loginInvalidUserTest() {
		assertFalse(LoginSystem.attemptLogin("null", "pass"));
	}
	
	@Test
	void loginInvalidPassTest() {
		assertFalse(LoginSystem.attemptLogin("Benamax", "wrong"));
	}
	
	// TODO: #05 Login as incorrect account type (staff/patient)
	
	// TODO: #06 Log out at patient
	@Test
	void logOutPatientTest() {
		LoginSystem.attemptLogin("Benamax", "pass");
		assertDoesNotThrow(() -> LoginSystem.signOut());
	}
	
	// TODO: #07 Log out as staff
	@Test
	void logOutStaffTest() {
		LoginSystem.attemptLogin("DOCTOR", "cmud77");
		assertDoesNotThrow(() -> LoginSystem.signOut());
	}
	
	// TODO: #08 Nurse enters vitals
	
	// TODO: #09 Nurse enters patient answers
	
	// TODO: #10 Nurse views patient history
	
	// TODO: #11 Nurse attempts to view nonexistent patient
	
	// TODO: #12 Doctor prescribes medication
	
	// TODO: #13 Doctor views patient history
	
	// TODO: #14 Doctor attempts to view nonexistent patient
	
	// TODO: #15 Nurse enters patient information
	
	// TODO: #16 Patient changes contact info
	
	// TODO: #17 Patient views summary of visit
	
	// TODO: #18 Doctor views summary of visit
	
	// TODO: #19 Patient sends message to doctor
	
	// TODO: #20 Patient sends message to nurse
	
	// TODO: #21 Doctor sends message to patient

}

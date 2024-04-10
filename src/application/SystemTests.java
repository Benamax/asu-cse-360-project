package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class SystemTests {
	@Test
	public void Test1() {
		// NO USER FOUND
		assertEquals(false, LoginSystem.attemptLogin("null", "null"));
	}
	
	@Test
	public void Test2() {
		assertEquals(true, LoginSystem.attemptLogin("ncorwins", "cmud77"));
	}
	
	@Test
	public void Test3() {
		assertEquals(Collections.emptyList(), MessageSystem.loadMessages("null"));
	}
}
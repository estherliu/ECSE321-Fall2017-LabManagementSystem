package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;

public class Staff {

	URLMS systemProgram;
	public ManagementSystemController controller;
	public String[] arr1;
	public int n = 300;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		systemProgram = URLMS.getInstance();
		controller = new ManagementSystemController();
		controller.register("stafftestlab", "testlab");
		controller.Login("stafftestlab", "testlab");

		arr1 = new String[n];

		randombuilder.generatestr(arr1);

		System.out.println("staff test started");
	}

	@After
	public void tearDown() throws Exception {
		controller.Logout();
		controller.unregister("stafftestlab");
		
	}

	@Test
	public void test() {
		try {
			controller.addStaff(null, "researchAssistant");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.addStaff("", "researchAssistant");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeStaff(null);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeStaff("");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}

		for (int i = 0; i < arr1.length; i++) {
			controller.addStaff(arr1[i], "researchAssistant");
		}
		
		assertEquals(arr1.length, controller.checkStaffs().size());

		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.removeStaff(arr1[i]);
			} catch (Exception e) {
				assertEquals(IllegalArgumentException.class, e.getClass());
			}
		}

		assertEquals(0, controller.checkStaffs().size());
		System.out.println("staff test passed");
	}

}

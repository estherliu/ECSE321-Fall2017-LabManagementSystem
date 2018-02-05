package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;

public class Progress {

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
		controller.register("progresstestlab", "testlab");
		controller.Login("progresstestlab", "testlab");
		controller.addStaff("test", "researchAssistant");

		arr1 = new String[n];

		randombuilder.generatestr(arr1);

		System.out.println("progress test started");
	}

	@After
	public void tearDown() throws Exception {
		controller.Logout();
		controller.unregister("progresstestlab");
		
	}

	@Test
	public void test() {
		try {
			controller.addProgress(null, null);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.addProgress("", "");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		

		for (int i = 0; i < arr1.length; i++) {
			controller.addProgress(arr1[i], "test");
		}
		
		assertEquals(arr1.length, controller.checkProgress("test").size());

		
		System.out.println("progress test passed");
	}

}

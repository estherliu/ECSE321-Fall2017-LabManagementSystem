package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;
import ca.mcgill.ecse321.managementSystem.persistence.PersistenceLab;

public class Lab {
	URLMS systemProgram;
	public ManagementSystemController controller;
	public String[] arr1;
	public String[] arr2;
	public int n = 300;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceLab.loadModel();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp(){
		systemProgram = URLMS.getInstance();
		controller = new ManagementSystemController();
		arr1 = new String[n];
		randombuilder.generatestr(arr1);
		arr2 = new String[n];
		randombuilder.generatestr(arr2);
	}
	

	@After
	public void tearDown(){
	}
	
	@Test
	public void test() {
		setUp();
		System.out.println("lab test started");
		try {
			controller.register(null, null);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.register("", "");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.register("k", "k");
			controller.register("k", "k");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
		controller.unregister("k");
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.register(arr1[i], arr2[i]);
				controller.Login(arr1[i], arr2[i]);
				controller.Logout();
				controller.unregister(arr1[i]);
				controller.register(arr1[i], arr2[i]);
				controller.unregister(arr1[i]);
			} catch (Exception e) {
				System.out.println(arr1[i]);
				System.out.println(arr2[i]);
			}
			
		}
		System.out.println("lab test passed");
		tearDown();
		
	}
	

}

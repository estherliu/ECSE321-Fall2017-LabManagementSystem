package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;

public class Supply {

	URLMS systemProgram;
	public ManagementSystemController controller;
	public String[] arr1;
	public int[] arr2;
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
		controller.register("supplytestlab", "testlab");
		controller.Login("supplytestlab", "testlab");
		
		arr1 = new String[n];
		arr2 = new int[n];
		
		randombuilder.generatestr(arr1);
		randombuilder.generateint(arr2);
		
		
		System.out.println("supply test started");
	}

	@After
	public void tearDown() throws Exception {
		controller.Logout();
		controller.unregister("supplytestlab");
		
	}

	@Test
	public void test() {
		try {
			controller.addSupplies(null, 0);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.addSupplies("", 0);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeSupply(null);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeSupply("");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.addSupplies(arr1[i], arr2[i]);
			}catch (Exception e) {
				if (arr2[i]<0) {
					arr1[i] = "";
					assertEquals(IllegalArgumentException.class, e.getClass());
				}
			}
			
		}
		
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.removeSupply(arr1[i]);
			} catch (Exception e) {
				assertEquals(IllegalArgumentException.class, e.getClass());
			}
		}
		
		assertEquals(0, controller.checkSupplies().size());
		System.out.println("supply test passed");
	}

}

package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;

public class Expense {

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
		controller.unregister("expensetestlab");
		controller.register("expensetestlab", "testlab");
		controller.Login("expensetestlab", "testlab");
		
		arr1 = new String[n];
		arr2 = new int[n];
		
		randombuilder.generatestr(arr1);
		randombuilder.generateint(arr2);
		for (int i : arr2) {
			i%=1000;
		}
		
		
		System.out.println("expense test started");
	}

	@After
	public void tearDown() throws Exception {
		controller.Logout();
		controller.unregister("expensetestlab");
		
	}

	@Test
	public void test() {
		try {
			controller.addExpense(null, 0);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.addExpense("", 0);
			fail();
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		int count = arr1.length;
		float sum = 0;
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.addExpense(arr1[i], arr2[i]);
				sum+=arr2[i];
			}catch (Exception e) {
				assertEquals(IllegalArgumentException.class, e.getClass());
			}
			
		}
		
		assertEquals((int)sum, (int)controller.getBalance());
		assertEquals(count, controller.checkExpenses().size());
		System.out.println("expense test passed");
	}

}

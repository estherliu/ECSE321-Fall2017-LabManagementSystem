package test;

import static org.junit.Assert.*;

import java.io.StreamCorruptedException;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;

public class Equipment {

	URLMS systemProgram;
	public ManagementSystemController controller;
	public String[] arr1;
	public int[] arr2;
	public int n = 200;
	
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
		controller.register("equipmenttestlab", "testlab");
		controller.Login("equipmenttestlab", "testlab");
		
		arr1 = new String[n];
		arr2 = new int[n];
		
		randombuilder.generatestr(arr1);
		randombuilder.generateint(arr2);
		
		
		System.out.println("equipment test started");
	}

	@After
	public void tearDown() throws Exception {
		controller.Logout();
		controller.unregister("equipmenttestlab");
		
	}

	@Test
	public void test() {
		try {
			controller.addEquipments(null, 0);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.addEquipments("", 0);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeEquipement(null);
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		try {
			controller.removeEquipement("");
		} catch (Exception e) {
			assertEquals(IllegalArgumentException.class, e.getClass());
		}
		
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.addEquipments(arr1[i], arr2[i]);
			}catch (Exception e) {
				if (arr2[i]<0) {
					arr1[i] = "";
					assertEquals(IllegalArgumentException.class, e.getClass());
				}
			}
			
		}
		
		for (int i = 0; i < arr1.length; i++) {
			try {
				controller.removeEquipement(arr1[i]);
			} catch (Exception e) {
				assertEquals(IllegalArgumentException.class, e.getClass());
			}
		}
		
		assertEquals(0, controller.checkEquipments().size());
		System.out.println("equipment test passed");
	}
}

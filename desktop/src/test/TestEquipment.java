package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;
import tcpclient.client;

public class TestEquipment {
	
	private String[] result;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
	@Before
	public void setUp() throws Exception {
		ManagementSystem.tcpconnection = new client("localhost", 9111);
		ManagementSystem.tcpconnection.exec("connection attempt");
		ManagementSystem.tcpconnection.exec("unregister_"+"user");
		ManagementSystem.tcpconnection.exec("register_" + "user" + "_" + "password");	
	    ManagementSystem.tcpconnection.exec("login_" + "user" + "_" + "password");
	}

	@After
	public void tearDown() throws Exception {
		ManagementSystem.tcpconnection.exec("logout");
		ManagementSystem.tcpconnection.exec("unregister_"+"user");
		
	}


	@Test
	public void testEquipment() {
		String[] equiplist;
		try{
	    String name1 = "Computer";
	    String quantity1 = "20";
	    result=ManagementSystem.tcpconnection.exec("addequipments_" + name1 + "_" + quantity1);	    
		assertEquals(result[0], "true");
		
		String name2 = "Desk";
	    String quantity2 = "148.7";
	    result=ManagementSystem.tcpconnection.exec("addequipments_" + name2 + "_" + quantity2);
		assertEquals(result[0], "the value must be a valid number");
		
		result=ManagementSystem.tcpconnection.exec("removeequipment_" + name1);
		assertEquals(result[0], "true");
		result=ManagementSystem.tcpconnection.exec("removeequipment_" + name2);
		assertEquals(result[0], "false");
		}
		catch(Exception e){
			fail();
		}		
		System.out.println("test passed");
	}
}

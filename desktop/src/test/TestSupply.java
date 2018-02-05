package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;
import tcpclient.client;

public class TestSupply {

	
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
	public void testSupply() {
		String[] supplylist;
		try{
	    String name1 = "Pen";
	    String quantity1 = "15";
	    result=ManagementSystem.tcpconnection.exec("addsupplies_" + name1 + "_" + quantity1);
	    assertEquals(result[0], "true");
		
		String name2 = "Paper";
	    String quantity2 = "400.33";
	    result=ManagementSystem.tcpconnection.exec("addsupplies_" + name2 + "_" + quantity2);
	    assertEquals(result[0], "the value must be a valid number");
	    
	    result=ManagementSystem.tcpconnection.exec("removesupply_" + name2);
	    assertEquals(result[0], "false");
	    
	    result=ManagementSystem.tcpconnection.exec("removesupply_" + name1);
	    assertEquals(result[0], "true");
		}
		catch(Exception e){
			fail();
		}		
		System.out.println("test passed");
	}

}

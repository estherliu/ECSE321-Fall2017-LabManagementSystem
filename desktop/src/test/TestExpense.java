package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;
import tcpclient.client;

public class TestExpense {

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
	public void testExpense() {
		String[] expenselist;
		try{
	    String name1 = "travel";
	    String amount1 = "16.0";
	    result=ManagementSystem.tcpconnection.exec("addexpense_" + name1 + "_" + amount1);
	    assertEquals(result[0],"true");
		
		String name2 = "salary";
	    String amount2 = "word";
	    result=ManagementSystem.tcpconnection.exec("addexpense_" + name2 + "_" + amount2);
	    assertEquals(result[0],"the value must be a valid number");
	    
	    result=ManagementSystem.tcpconnection.exec("getbalance");
	    assertEquals(result[0],"16.0");
		}
		catch(Exception e){
			fail();
		}		
	}
	
}

package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;
import tcpclient.client;

public class TestStaff {
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
	public void testStaff() {
		String[] stafflist;
		try{
			String staffName1 = "Ann";
			String staffRole1 = "researcher";
			result=ManagementSystem.tcpconnection.exec("addstaff_" + staffName1 + "_" + staffRole1);
			assertEquals(result[0],"true");
		
			String staffName2 = "Ben";
			String staffRole2 = "norole";
			result=ManagementSystem.tcpconnection.exec("addstaff_" + staffName2 + "_" + staffRole2);
			assertEquals(result[0],"false");
			
			result=ManagementSystem.tcpconnection.exec("removestaff_" + staffName1);
			assertEquals(result[0],"true");
			
			result=ManagementSystem.tcpconnection.exec("removestaff_" + staffName2);
			assertEquals(result[0],"false");
		}
		catch(Exception e){
			fail();
		}		

		System.out.println("test passed");

		
	}
		
	

}

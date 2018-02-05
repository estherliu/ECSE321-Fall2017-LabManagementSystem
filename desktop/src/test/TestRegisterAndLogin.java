package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;
import tcpclient.client;

public class TestRegisterAndLogin {
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
	}

	@After
	public void tearDown() throws Exception {
		ManagementSystem.tcpconnection.exec("logout");
		ManagementSystem.tcpconnection.exec("unregister_"+"user");
		
	}



	@Test
	public void testRegisterAndLogin() throws Exception {
		System.out.println("test started");
		try{
			result=ManagementSystem.tcpconnection.exec("register_" + "user" + "_" + "password");
			assertEquals(result[0], "true");
		}
	    catch(Exception e){
			fail();
		}		
	    
		try{
	       result=ManagementSystem.tcpconnection.exec("login_" + "user" + "_" + "password");
	       assertEquals(result[0], "true");
		}
		catch(Exception e){
			fail();
		}
		System.out.println("test passed");
	}

}

package ca.mcgill.ecse321.managementSystem.application;

import javax.swing.UIManager;

import ca.mcgill.ecse321.managementSystem.view.Login;
import tcpclient.client;

public class ManagementSystem {

	public static client tcpconnection;

	public static void main(String[] args) throws Exception {

		// change the style of UI
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); // Nimbus style
		} catch (Exception e) {
			e.printStackTrace();
		}

		// to run server and client on different machines, change localhost to the ip address of server
		tcpconnection = new client("localhost", 9111);    //173.179.5.229, ip of our remote server
		tcpconnection.exec("connection attempt");
		Login frame = new Login();
		frame.setVisible(true);

	}
}

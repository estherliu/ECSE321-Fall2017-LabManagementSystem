package ca.mcgill.ecse321.managementSystem.application;

import ca.mcgill.ecse321.managementSystem.controller.ManagementSystemController;
import ca.mcgill.ecse321.managementSystem.model.URLMS;
import ca.mcgill.ecse321.managementSystem.persistence.PersistenceLab;
import tcpserver.server;

public class ManagementSystem {

	public static ManagementSystemController controller = new ManagementSystemController();
	public static URLMS urlms;
	
	public static void main(String[] args) {
		try {
			PersistenceLab.loadModel();
			server host = new server();
			host.start();
		} catch (Exception e) {
			
		}
	}
}

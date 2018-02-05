package tcpserver;

import java.net.Socket;

import ca.mcgill.ecse321.managementSystem.application.ManagementSystem;

public class sockethandler extends Thread{
	
	public int length;
	public byte[] buffer;
	public Socket socket;
	
	public sockethandler(Socket source) {
		length = 0;
		buffer = new byte[10000];
		socket =source;
	}
	
	public void run() {
		try {
			length = socket.getInputStream().read(buffer);
			if (length>0) {
				String temp = "";
				for (int i = 0; i < length; i++) {
					temp += (char)buffer[i];
				}
				String response = ManagementSystem.controller.exec(temp, this);
				socket.getOutputStream().write(response.getBytes());
				System.out.println(temp+" is executed");
				System.out.println("return "+response);
				socket.close();
			}
			else {
				
			}
		} catch (Exception e) {
				
		}
	}
}

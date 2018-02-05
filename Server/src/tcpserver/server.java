package tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{
	
	ServerSocket serverSocket;
	public static boolean open = true;
	
	
	public server() throws Exception{
		serverSocket = new ServerSocket(9111);
		serverSocket.setSoTimeout(1000);
		System.out.println("server initialized");
	}
	
	
	public void run(){
		
		System.out.println("server started");
		while (open) {
			try {
				
				Socket socket = serverSocket.accept();
				sockethandler s = new sockethandler(socket);
				if (socket.isConnected()) {
					System.out.println(socket.getInetAddress()+" is connected via "+socket.getPort());
				}
				s.start();
			} catch (Exception e) {
			}
		}
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

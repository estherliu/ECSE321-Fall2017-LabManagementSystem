package tcpclient;

import java.net.Socket;

public class client {
	public static byte[] buffer;
	public static int length;
	public static Socket socket;
	public static String IP;
	public static int PORT;
	
	
	public client(String ip, int port) throws Exception{
		length = 0;
		buffer = new byte[10000];
		IP = ip;
		PORT = port;
		
	}
	
	
	public String[] exec(String cmd) throws Exception {
		
		socket = new Socket(IP,PORT);
		socket.getOutputStream().write(cmd.getBytes());
		
		
		String temp = "";
		
		length = socket.getInputStream().read(buffer);
		for (int i = 0; i < length; i++) {
			temp += (char)buffer[i];
		}
		return temp.split("_");
	}

}

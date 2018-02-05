package ca.mcgill.ecse321.tcpclient;

import android.os.AsyncTask;

import java.net.Socket;
import java.util.Comparator;

import ca.mcgill.ecse321.managementsystem.MainActivity;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

/**
 * a static class that handle the connection between server and client
 */
public class TCPclient extends Thread{

    public static byte[] buffer = new byte[10000];;
    public static int length = 0;
    public static Socket socket;
    public static String IP = "173.179.5.229";
    public static int PORT = 9111;
    public static String command;

    /**
     *
     * @param command
     * @return an array of strings that contains the information
     *          needed by view.
     * @throws Exception
     */

    public static String[] exec(String command) throws Exception {
        command = command;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new Socket(TCPclient.IP,TCPclient.PORT);
                    socket.getOutputStream().write(TCPclient.command.getBytes());
                    length = socket.getInputStream().read(buffer);
                    TCPclient.command = "";
                    for (int i = 0; i < length; i++) {
                        TCPclient.command += (char)buffer[i];
                    }
                }catch (Exception e){

                }

            }
        });
        t.start();
        while(t.isAlive()){
            Thread.sleep(500);
        }
        return command.split("_");
    }


}

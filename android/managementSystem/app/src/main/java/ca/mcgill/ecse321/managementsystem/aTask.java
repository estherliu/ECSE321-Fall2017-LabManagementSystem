package ca.mcgill.ecse321.managementsystem;

import android.view.View;

import ca.mcgill.ecse321.tcpclient.TCPclient;

/**
 * Created by Administrator on 2017/12/4 0004.
 * A class that handle thread when calling the TCPclient class
 */

public class aTask extends Thread {
    String cmd;
    String[] response;
    public aTask(){

    }

    protected String[] exec(String command){
        try{
            response = TCPclient.exec(command);
        }catch (Exception e){
            e.printStackTrace();
            response = null;
        }
        return response;
    }
}

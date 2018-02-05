package ca.mcgill.ecse321.managementsystem;

import android.app.assist.AssistContent;

import junit.extensions.TestSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import ca.mcgill.ecse321.tcpclient.TCPclient;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Before
    public void init() throws Exception{
        String username = randomstring(10);
        String password = randomstring(10);
        TCPclient.exec("register_"+username+"_"+password);
        TCPclient.exec("login_"+username+"_"+password);
    }
    @After
    public void tearDown() throws Exception {
        TCPclient.exec("logout");
        TCPclient.exec("unregister_"+"user");

    }
    @Test
    public void addition_isCorrect() throws Exception {
        String[] researchers = new String[5];
        String t = "true";
        for (int i = 0; i < researchers.length; i++) {
            researchers[i] = randomstring(10)+i;
        }
        for (int i = 0; i < researchers.length; i++) {
            assertEquals(TCPclient.exec("addstaff_"+researchers[i]+"_"+"researcher")[0],t);
        }
        String[] assitants = new String[5];
        for (int i = 0; i < assitants.length; i++) {
            assitants[i] = randomstring(10)+i;
        }
        for (int i = 0; i < researchers.length; i++) {
            assertEquals(TCPclient.exec("addstaff_"+assitants[i]+"_"+"researchAssistant")[0],t);
        }
        String[] actual = TCPclient.exec("checkstaffs");
        for (int i = 0; i < 5; i++) {
            assertEquals(actual[i*2],researchers[i]);
            assertEquals(actual[i*2+1],"researcher");
            assertEquals(actual[i*2+10], assitants[i]);
            assertEquals(actual[i*2+11],"researchAssistant");
        }
    }

    public String randomstring(int length){
        String available = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * available.length());
            stringBuilder.append(available.charAt(index));
        }
        String str = stringBuilder.toString();
        return str;
    }
}
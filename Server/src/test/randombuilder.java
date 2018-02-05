package test;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class randombuilder {
	
	public static void generatestr(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = getSaltString();
		}
	}
	
	public static void generateint(int[] arr) {
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt();
		}
	}
	public static String getSaltString() {
		Random rnd = new Random();
        String SALTCHARS = "qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < rnd.nextInt(30)+5) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        return saltStr;
	}
}

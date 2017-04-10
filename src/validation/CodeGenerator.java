package validation;

import java.security.SecureRandom;

public class CodeGenerator {

	//use UUID to create code
	private static final String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();
	private static final int LENGHT =  5;
	
	public static String createCode(){
	   StringBuilder sb = new StringBuilder();
	   for( int i = 0; i < LENGHT; i++ ) {
		   sb.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
	   }
	      
	   return sb.toString();	   
	}
}

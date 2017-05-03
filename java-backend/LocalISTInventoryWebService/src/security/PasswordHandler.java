package security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public class PasswordHandler {
	

	public PasswordHandler() {}
	  
	public String hash(String password, String salt, Encoder encoder) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		byte[] saltyBytes = salt.getBytes("UTF-8");
		return encoder.getHash(password, saltyBytes);
	}
	

	  
	
}

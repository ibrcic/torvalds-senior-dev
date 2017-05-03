package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements Encoder {

	public SHA256() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHash(String password, byte[] salt) throws AssertionError{
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		
	        md.update(salt);
	        byte[] passwordBytes = md.digest(password.getBytes("UTF-8"));
	         
	         
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< passwordBytes.length ;i++){
	           sb.append(Integer.toString((passwordBytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		}
		
	}

}

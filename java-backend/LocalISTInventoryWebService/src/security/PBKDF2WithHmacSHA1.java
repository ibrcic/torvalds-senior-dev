package security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2WithHmacSHA1 implements Encoder {
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	
	public PBKDF2WithHmacSHA1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Returns a salted and hashed password using the provided hash.<br>
	 * Note - side effect: the password is destroyed (the char[] is filled with zeros)
	 *
	 * @param password the password to be hashed
	 * @param salt a 16 bytes salt, ideally obtained with the getNextSalt method
	 *
	 * @return the hashed password with a pinch of salt
	 */
	public String getHash(String password, byte[] salt) {
		char[] passwordChars = password.toCharArray();
		PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(passwordChars, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return new String(skf.generateSecret(spec).getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}

	

}

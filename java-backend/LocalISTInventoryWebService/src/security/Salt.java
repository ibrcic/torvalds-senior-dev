package security;

import java.security.SecureRandom;
import java.util.Random;

public class Salt {
	private static final Random RANDOM = new SecureRandom();
	public Salt() {
		// TODO Auto-generated constructor stub
	}

	public static byte[] getNextSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	
}

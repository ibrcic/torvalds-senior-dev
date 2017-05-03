package security;


public interface Encoder {
	public String getHash(String password, byte[] salt);
}

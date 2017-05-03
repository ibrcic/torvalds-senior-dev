package security;

public class Token {
	private String username, password;

	public Token(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}

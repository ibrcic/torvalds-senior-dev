package security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ist.services.rest.ConnectDb;

public class UserLogin {

	public UserLogin(){
		
	}
	
	public boolean tryLogin(String username, String password) throws Exception{
		String salt;
		try {
			salt = getSalt(username);
		
		String generated = getHash(username, password, salt);
		String fromDb= hashFromDb(username);
		if (!generated.equals(null) && !fromDb.equals(null)){
			if(generated.equals(fromDb)){
				return true;
			}
			
		}
		} catch (SQLException e) {
			throw new Exception("Username and Password combination not recognized");
			
		}
		return false;
				
	}
	
//	public UserLogin(String username, String password, boolean firstLogin){
//		PasswordHandler passwordHandler = new PasswordHandler();
//		Encoder encoder = new PBKDF2WithHmacSHA1();
//		String salt = new String(Salt.getNextSalt());
//		try {
//			passwordHandler.hash(password, salt, encoder);
//			
//		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	private String getSalt(String username) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("connecting to db...");
			ConnectDb connectDb = new ConnectDb();
			con = connectDb.getConn();

			if (con != null) {
				System.out.println("Connected!");
			}

			pstmt = con.prepareStatement(
					"SELECT salt from InventoryUserDb.User where username=?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); // required to move the cursor to the first row returned
			String salt 	= rs.getString("salt");
			return salt;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Not Connected");

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Null error");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not Connected");
		} finally {

			if (pstmt != null) {

				pstmt.close();

			}

			if (con != null) {
				con.close();
			}

		}
		return null;
	}
	
	
	private String getHash(String username, String password, String salt){
		PasswordHandler passwordHandler = new PasswordHandler();
		Encoder encoder = new PBKDF2WithHmacSHA1();
		try {
			return passwordHandler.hash(password, salt, encoder);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	public String hashFromDb(String username) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("connecting to db...");
			ConnectDb connectDb = new ConnectDb();
			con = connectDb.getConn();
	
			if (con != null) {
				System.out.println("Connected!");
			}
	
			pstmt = con.prepareStatement(
					"SELECT password from InventoryUserDb.User where username=?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); // required to move the cursor to the first row returned
			String password 	= rs.getString("password");
			return password;
	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Not Connected");
	
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Null error");
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Not Connected");
		} finally {
	
			if (pstmt != null) {
	
				pstmt.close();
	
			}
	
			if (con != null) {
				con.close();
			}
	
		}
		return null;
	}

}
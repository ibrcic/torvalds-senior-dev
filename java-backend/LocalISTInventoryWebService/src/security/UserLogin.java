package security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

import com.ist.services.rest.ConnectDb;

public class UserLogin {

	private static Map<PublicKey, PrivateKey> keylist = new HashMap<PublicKey, PrivateKey>();
	private static Cipher cipher;
	public UserLogin() {
		
	}
	public static boolean messageHandler(String message, String pubkey) throws Exception{
		String decryptedMessage = decryptText(message, getPrivateKey( getPublicKey(pubkey)));
		String[] words = decryptedMessage.split("\\s+");
		String username = words[0];
		String password = words[1];
		return tryLogin(username, password);
		
	}
	
	public static PublicKey getNewKeySet() throws NoSuchAlgorithmException{
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		KeyPair kp = keygen.generateKeyPair();
		PublicKey pubkey = kp.getPublic(); 
		keylist.put(pubkey,	kp.getPrivate());
		return pubkey;
	}
	
	public String encryptText(String msg, PublicKey key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException {
		cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}
	
	public static String decryptText(String msg, PrivateKey privKey)
			throws InvalidKeyException, UnsupportedEncodingException,
			IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8" );
	}
	
	public static PrivateKey getPrivateKey(PublicKey pubkey){
		return keylist.get(pubkey);
	}
	
	public static PublicKey getPublicKey(String pubKey) throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] keyBytes = Base64.decodeBase64(pubKey);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(X509publicKey);
	}
	

	
	
	public static boolean tryLogin(String username, String password) throws Exception{
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
	
	private static String getSalt(String username) throws SQLException{
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
	
	
	private static String getHash(String username, String password, String salt){
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
		
	public static String hashFromDb(String username) throws SQLException {

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
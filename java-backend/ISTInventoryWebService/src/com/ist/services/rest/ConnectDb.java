package com.ist.services.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Noah
 *
 */
public class ConnectDb {

	Connection conn;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public ConnectDb(String username, String password) throws ClassNotFoundException, SQLException {
		conn = connect(username, password);
	}

	public java.sql.Connection connect(String username, String password) throws ClassNotFoundException, SQLException {
		// String driver = "org.mariadb.jdbc.Driver";
	//	String driver = "com.mysql.jdbc.Driver";
		String driverMaria = "org.mariadb.jdbc.Driver";
		// For testing
		// String url =
		// "jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");";
		// String url = "jdbc:mariadb://torvalds.ist.rit.edu:3306/DB";
		String url = "jdbc:mariadb://team-torvalds.ist.rit.edu:3306";
		//Class.forName(driverMaria);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public Connection getConn() {
		return conn;
	}

}

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
		// String driverMySQL = "com.mysql.jdbc.Driver";
		// String driverMaria = "org.mariadb.jdbc.Driver";
		// String url =
		// "jdbc:mariadb://team-torvalds.ist.rit.edu:3306/InventoryItemDb";

		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3307/mydb";
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public Connection getConn() {
		return conn;
	}

}

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
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	public ConnectDb(String username, String password)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		conn = connect(username, password);
	}

	public java.sql.Connection connect(String username, String password)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		// String driverMySQL = "com.mysql.jdbc.Driver";
		String driverMaria = "org.mariadb.jdbc.Driver";
		// String url =
		// "jdbc:mariadb://team-torvalds.ist.rit.edu:3306/InventoryItemDb";

		// Class.forName("com.mysql.jdbc.Driver");
		// String url = "jdbc:mysql://localhost:3307/mydb";
		Class.forName(driverMaria);
		String url = "jdbc:mariadb://team-torvalds.ist.rit.edu:3306/InventoryItemDb";

		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public Connection getConn() {
		return conn;
	}

}

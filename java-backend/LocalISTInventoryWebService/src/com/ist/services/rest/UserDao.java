package com.ist.services.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ist.services.rest.pojo.User;

/**
 * @author Hassan Jegan Ndow
 *
 */

public class UserDao {

	List<User> borrowerDbList = new ArrayList<User>();

	public void connectDb(String username, String password) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("connecting to db...");
			ConnectDb connectDb = new ConnectDb(username, password);
			con = connectDb.getConn();

			if (con != null) {
				System.out.println("Connected!");
			}

			pstmt = con.prepareStatement(
					"SELECT Borrower.borrowerId,Borrower.username,Borrower.email,Borrower.flagged,Major.majorTitle, Major.majorAbbreviation, Major.majorID, Class.classId, Class.classTitle, Class.className,Section.section from InventoryItemDb.Borrower LEFT JOIN InventoryItemDb.Borrower_has_Major ON Borrower.borrowerId = Borrower_has_Major.Borrower_borrowerId LEFT JOIN InventoryItemDb.Major on Borrower_has_Major.Major_majorID = Major.majorId LEFT JOIN InventoryItemDb.Borrower_has_Class ON Borrower.borrowerId = Borrower_has_Class.Borrower_borrowerId LEFT JOIN InventoryItemDb.Class on Borrower_has_Class.Class_classId = Class.classId LEFT JOIN InventoryItemDb.Section on Class.classId = Section.Class_classId");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long borrowerId = rs.getLong("borrowerId");
				String userName = rs.getString("username");
				String email = rs.getString("email");
				long majorId = rs.getLong("majorId");
				String majorTitle = rs.getString("majorTitle");
				String majorAbbr = rs.getString("majorAbbreviation");
				int flagged = rs.getInt("flagged");
				long classId = rs.getLong("classId");
				String classTitle = rs.getString("classTitle");
				String className = rs.getString("className");
				int section = rs.getInt("section");

				loadData(borrowerId, userName, email, majorId, majorTitle, majorAbbr, flagged, classId, classTitle,
						className, section);

			}

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

	}

	public void loadData(long borrowerId, String userName, String email, long majorId, String majorTitle,
			String majorAbbr, int flagged, long classId, String classTitle, String className, int section) {

		User user = new User();

		user.setBorrowerId(borrowerId);
		user.setUserName(userName);
		user.setEmail(email);
		user.setMajorId(majorId);
		user.setMajorTitle(majorTitle);
		user.setMajorAbbr(majorAbbr);
		user.setFlagged(flagged);
		user.setClassId(classId);
		user.setClassTitle(classTitle);
		user.setClassName(className);
		user.setSection(section);

		borrowerDbList.add(user);
	}

	// Read
	public List<User> getAllUsers(String username, String password) throws SQLException {
		List<User> userList = null;
		connectDb(username, password);

		long borrowerId;
		String userName;
		String email;
		long majorId;
		String majorTitle;
		String majorAbbr;
		int flagged;
		long classId;
		String classTitle;
		String className;
		int section;

		if (borrowerDbList.size() > 0) {
			userList = new ArrayList<User>();
			for (User user : borrowerDbList) {
				borrowerId = user.getBorrowerId();
				userName = user.getUserName();
				email = user.getEmail();
				majorId = user.getMajorId();
				majorTitle = user.getMajorTitle();
				majorAbbr = user.getMajorAbbr();
				flagged = user.getFlagged();
				classId = user.getClassId();
				classTitle = user.getClassTitle();
				className = user.getClassName();
				section = user.getSection();

				User user2 = new User();
				user2.setBorrowerId(borrowerId);
				user2.setUserName(userName);
				user2.setEmail(email);
				user2.setMajorId(majorId);
				user2.setMajorTitle(majorTitle);
				user2.setMajorAbbr(majorAbbr);
				user2.setFlagged(flagged);
				user2.setClassId(classId);
				user2.setClassTitle(classTitle);
				user2.setClassName(className);
				user2.setSection(section);

				userList.add(user2);

			}

		}

		return userList;
	}

	// Get User
	public User getUser(long id, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		for (User user : users) {
			if (user.getBorrowerId() == id) {
				return user;
			}
		}
		return null;
	}

	// Add user
	public int addUser(User pUser, String username, String password) throws SQLException {
		List<User> userList = getAllUsers(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		boolean userExists = false;
		for (User user : userList) {
			if (user.getBorrowerId() == pUser.getBorrowerId()) {
				userExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!userExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					// TODO - create prepared statements and execute them
					pstmt1 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Borrower (borrowerId, username, email, flagged) VALUES (?, ?, ?, ?)");

					long borrowerId = pUser.getBorrowerId();
					String borrowerUsername = pUser.getUserName();
					String email = pUser.getEmail();
					int flagged = pUser.getFlagged();

					pstmt1.setLong(1, borrowerId);
					pstmt1.setString(2, borrowerUsername);
					pstmt1.setString(3, email);
					pstmt1.setInt(4, flagged);

					pstmt2 = con.prepareStatement(
							"INSERT IGNORE INTO InventoryItemDb.Major (majorID, majorTitle, majorAbbreviation) VALUES (?,?,?)");

					long majorID = pUser.getMajorId();
					String majorTitle = pUser.getMajorTitle();
					String majorAbbreviation = pUser.getMajorAbbr();

					pstmt2.setLong(1, majorID);
					pstmt2.setString(2, majorTitle);
					pstmt2.setString(3, majorAbbreviation);

					pstmt3 = con.prepareStatement(
							"INSERT IGNORE INTO InventoryItemDb.Class (classId, classTitle, className) VALUES (?, ?, ?)");

					long classId = pUser.getClassId();
					String classTitle = pUser.getClassTitle();
					String className = pUser.getClassName();

					pstmt3.setLong(1, classId);
					pstmt3.setString(2, classTitle);
					pstmt3.setString(3, className);

					pstmt4 = con.prepareStatement(
							"INSERT IGNORE INTO InventoryItemDb.Section (Class_classId, section) VALUES (?, ?)");

					int section = pUser.getSection();

					pstmt4.setLong(1, classId);
					pstmt4.setInt(2, section);

					pstmt5 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Borrower_has_Class (Borrower_borrowerId, Class_classId) VALUES (?, ?)");

					pstmt5.setLong(1, borrowerId);
					pstmt5.setLong(2, classId);

					pstmt6 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Borrower_has_Major (Borrower_borrowerId, Major_majorID) VALUES (?, ?)");

					pstmt6.setLong(1, borrowerId);
					pstmt6.setLong(2, majorID);

					pstmt1.executeUpdate();
					pstmt2.executeUpdate();
					pstmt3.executeUpdate();
					pstmt4.executeUpdate();
					pstmt5.executeUpdate();
					pstmt6.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Not Connected");
				} finally {

					if (pstmt1 != null) {

						pstmt1.close();

					}

					if (pstmt2 != null) {

						pstmt2.close();

					}

					if (pstmt3 != null) {

						pstmt3.close();

					}

					if (pstmt4 != null) {

						pstmt4.close();

					}

					if (pstmt5 != null) {

						pstmt5.close();

					}

					if (pstmt6 != null) {

						pstmt6.close();

					}

					if (con != null) {
						con.close();
					}

				}

				return 1;
			}
		}
		return 0;
	} // end of addUser

	// Update Item
	public int updateUser(User pUser, String username, String password) throws SQLException {

		List<User> userList = getAllUsers(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		for (User user : userList) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Borrower SET username = ?, email = ?, flagged = ? WHERE borrowerId = ?");

					long borrowerId = pUser.getBorrowerId();
					String borrowerUsername = pUser.getUserName();
					String email = pUser.getEmail();
					int flagged = pUser.getFlagged();

					pstmt1.setString(1, borrowerUsername);
					pstmt1.setString(2, email);
					pstmt1.setInt(3, flagged);
					pstmt1.setLong(4, borrowerId);

					pstmt2 = con.prepareStatement(
							"UPDATE InventoryItemDb.Major SET majorTitle = ?, majorAbbreviation = ? WHERE majorID = ?");

					long majorID = pUser.getMajorId();
					String majorTitle = pUser.getMajorTitle();
					String majorAbbreviation = pUser.getMajorAbbr();

					pstmt2.setString(1, majorTitle);
					pstmt2.setString(2, majorAbbreviation);
					pstmt2.setLong(3, majorID);

					pstmt3 = con.prepareStatement(
							"UPDATE InventoryItemDb.Class SET classTitle = ?, className = ? WHERE classId = ?");

					long classId = pUser.getClassId();
					String classTitle = pUser.getClassTitle();
					String className = pUser.getClassName();

					pstmt3.setString(1, classTitle);
					pstmt3.setString(2, className);
					pstmt3.setLong(3, classId);

					pstmt1.executeUpdate();
					pstmt2.executeUpdate();
					pstmt3.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Not Connected");
				} finally {

					if (pstmt1 != null) {

						pstmt1.close();

					}

					if (pstmt2 != null) {

						pstmt2.close();

					}

					if (pstmt3 != null) {

						pstmt3.close();

					}

					if (con != null) {
						con.close();
					}

				}

				return 1;
			} else {
				System.out.println("Did not update");
			}
		}

		return 0;
	} // end of updateUser

}

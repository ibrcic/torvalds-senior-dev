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
					"SELECT borrower.borrowerId,borrower.username,borrower.email,borrower.flagged,major.majorTitle, major.majorAbbreviation, major.majorID, class.classId, class.classTitle, class.className,section.section from mydb.borrower LEFT JOIN mydb.borrower_has_major ON borrower.borrowerId = borrower_has_major.borrower_borrowerId LEFT JOIN mydb.major on borrower_has_major.major_majorID = major.majorId LEFT JOIN mydb.borrower_has_class ON borrower.borrowerId = borrower_has_class.borrower_borrowerId LEFT JOIN mydb.class on borrower_has_class.class_classId = class.classId LEFT JOIN mydb.section on class.classId = section.Class_classId");

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

		User user = new User(borrowerId, userName, email, majorId, majorTitle, majorAbbr, classId, classTitle,
				className, section, flagged);

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

				userList.add(new User(borrowerId, userName, email, majorId, majorTitle, majorAbbr, classId, classTitle,
						className, section, flagged));

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

}

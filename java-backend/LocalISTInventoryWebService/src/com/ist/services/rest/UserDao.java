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
	List<User> classDbList = new ArrayList<User>();
	List<User> majorDbList = new ArrayList<User>();

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

	// loads user related information
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

	// loads only class related information
	public void loadDataClass(long classId, String classTitle, String className) {

		User userClass = new User();

		userClass.setClassId(classId);
		userClass.setClassTitle(classTitle);
		userClass.setClassName(className);

		classDbList.add(userClass);
	}

	// loads only major related information
	public void loadData(long majorId, String majorTitle, String majorAbbr) {

		User major = new User();

		major.setMajorId(majorId);
		major.setMajorTitle(majorTitle);
		major.setMajorAbbr(majorAbbr);

		majorDbList.add(major);
	}

	// Gets all users
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

	// Get user by UID
	public User getUser(long id, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		for (User user : users) {
			if (user.getBorrowerId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	// Get class by id
	public User getClass(long id, String username, String password) throws SQLException {
		List<User> users = getClasses(username, password);
		for (User user : users) {
			if (user.getClassId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	// Get major by id
	public User getMajor(long id, String username, String password) throws SQLException {
		List<User> users = getMajors(username, password);
		for (User user : users) {
			if (user.getMajorId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	// gets all classes
	public List<User> getClasses(String username, String password) throws SQLException {
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
					"SELECT Class.classId,  Class.classTitle,  Class.className from InventoryItemDb.Class");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long classId = rs.getLong("classId");
				String classTitle = rs.getString("classTitle");
				String className = rs.getString("className");

				loadDataClass(classId, classTitle, className);

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
		return classDbList;
	}

	// gets all majors
	public List<User> getMajors(String username, String password) throws SQLException {
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
					"SELECT Major.majorID,  Major.majorTitle,  Major.majorAbbreviation FROM InventoryItemDb.Major");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long majorId = rs.getLong("majorId");
				String majorTitle = rs.getString("majorTitle");
				String majorAbbr = rs.getString("majorAbbreviation");

				loadData(majorId, majorTitle, majorAbbr);

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
		return majorDbList;
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
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
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

					// pstmt2 = con.prepareStatement(
					// "INSERT IGNORE INTO InventoryItemDb.Major (majorID,
					// majorTitle, majorAbbreviation) VALUES (?,?,?)");
					//
					// long majorID = pUser.getMajorId();
					// String majorTitle = pUser.getMajorTitle();
					// String majorAbbreviation = pUser.getMajorAbbr();
					//
					// pstmt2.setLong(1, majorID);
					// pstmt2.setString(2, majorTitle);
					// pstmt2.setString(3, majorAbbreviation);

					// pstmt3 = con.prepareStatement(
					// "INSERT IGNORE INTO InventoryItemDb.Class (classId,
					// classTitle, className) VALUES (?, ?, ?)");
					//
					// long classId = pUser.getClassId();
					// String classTitle = pUser.getClassTitle();
					// String className = pUser.getClassName();
					//
					// pstmt3.setLong(1, classId);
					// pstmt3.setString(2, classTitle);
					// pstmt3.setString(3, className);

					// pstmt4 = con.prepareStatement(
					// "INSERT IGNORE INTO InventoryItemDb.Section
					// (Class_classId, section) VALUES (?, ?)");
					//
					// int section = pUser.getSection();
					//
					// pstmt4.setLong(1, classId);
					// pstmt4.setInt(2, section);
					//
					// pstmt5 = con.prepareStatement(
					// "INSERT INTO InventoryItemDb.Borrower_has_Class
					// (Borrower_borrowerId, Class_classId) VALUES (?, ?)");
					//
					// pstmt5.setLong(1, borrowerId);
					// pstmt5.setLong(2, classId);

					// pstmt6 = con.prepareStatement(
					// "INSERT INTO InventoryItemDb.Borrower_has_Major
					// (Borrower_borrowerId, Major_majorID) VALUES (?, ?)");
					//
					// pstmt6.setLong(1, borrowerId);
					// pstmt6.setLong(2, majorID);

					pstmt1.executeUpdate();
					// pstmt2.executeUpdate();
					// pstmt3.executeUpdate();
					// pstmt4.executeUpdate();
					// pstmt5.executeUpdate();
					// pstmt6.executeUpdate();

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

	// Attach class to user
	public int attachClass(User pUser, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		List<User> classes = getClasses(username, password);
		boolean userIdExists = false;
		boolean classIdExists = false;
		for (User user : users) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
				userIdExists = true;

			}

		}
		for (User userClass : classes) {
			if (userClass.getClassId().equals(pUser.getClassId())) {
				classIdExists = true;

			}
		}

		if (userIdExists && classIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Borrower_has_Class(Borrower_borrowerId, Class_classId) VALUES (?, ?)");

				long borrowerId = pUser.getBorrowerId();
				long classId = pUser.getClassId();

				pstmt1.setLong(1, borrowerId);
				pstmt1.setLong(2, classId);

				pstmt1.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Not Connected");
			} finally {

				if (pstmt1 != null) {

					pstmt1.close();

				}

				if (con != null) {
					con.close();
				}

			}
			return 1;
		}
		return 0;
	} // attachClass

	// Detach class from borrower
	public int detachClass(User pUser, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		List<User> classes = getClasses(username, password);
		boolean itemIdExists = false;
		boolean classIdExists = false;
		for (User user : users) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
				itemIdExists = true;
			}
		}
		for (User userClass : classes) {
			if (userClass.getClassId().equals(pUser.getClassId())) {
				classIdExists = true;
			}
		}

		if (itemIdExists && classIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"DELETE FROM InventoryItemDb.Borrower_has_Class WHERE Borrower_borrowerId = ? AND Class_classId = ?");
				long borrowerId = pUser.getBorrowerId();
				long classId = pUser.getClassId();

				pstmt1.setLong(1, borrowerId);
				pstmt1.setLong(2, classId);

				pstmt1.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Not Connected");
			} finally {

				if (pstmt1 != null) {

					pstmt1.close();

				}

				if (con != null) {
					con.close();
				}

			}
			return 1;
		}
		return 0;
	} // detachClass

	// Detach major from borrower
	public int detachMajor(User pUser, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		List<User> majors = getMajors(username, password);
		boolean itemIdExists = false;
		boolean majorIdExists = false;
		for (User user : users) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
				itemIdExists = true;
			}
		}
		for (User major : majors) {
			if (major.getMajorId().equals(pUser.getMajorId())) {
				majorIdExists = true;
			}
		}

		if (itemIdExists && majorIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"DELETE FROM InventoryItemDb.Borrower_has_Major WHERE Borrower_borrowerId = ? AND Major_majorID = ?");
				long borrowerId = pUser.getBorrowerId();
				long majorId = pUser.getMajorId();

				pstmt1.setLong(1, borrowerId);
				pstmt1.setLong(2, majorId);

				pstmt1.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Not Connected");
			} finally {

				if (pstmt1 != null) {

					pstmt1.close();

				}

				if (con != null) {
					con.close();
				}

			}
			return 1;
		}
		return 0;
	} // detachMajor

	// Attach major to user
	public int attachMajor(User pUser, String username, String password) throws SQLException {
		List<User> users = getAllUsers(username, password);
		List<User> majors = getMajors(username, password);
		boolean userIdExists = false;
		boolean majorIdExists = false;
		for (User user : users) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
				userIdExists = true;
			}
		}
		for (User major : majors) {
			if (major.getMajorId().equals(pUser.getMajorId())) {
				majorIdExists = true;
			}
		}

		if (userIdExists && majorIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Borrower_has_Major(Borrower_borrowerId, Major_majorID) VALUES (?, ?)");

				long borrowerId = pUser.getBorrowerId();
				long majorID = pUser.getMajorId();

				pstmt1.setLong(1, borrowerId);
				pstmt1.setLong(2, majorID);

				pstmt1.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Not Connected");
			} finally {

				if (pstmt1 != null) {

					pstmt1.close();

				}

				if (con != null) {
					con.close();
				}

			}
			return 1;
		}
		return 0;
	} // attachMajor

	// web doc
	// Add class
	public int addClass(User pUser, String username, String password) throws SQLException {
		List<User> userList = getClasses(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;

		// TODO: reformat flag condition
		boolean userExists = false;
		for (User user : userList) {
			if (user.getClassId().equals(pUser.getClassId())) {
				userExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!userExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"INSERT IGNORE INTO InventoryItemDb.Class (classTitle, className) VALUES (?, ?)");

					String classTitle = pUser.getClassTitle();
					String className = pUser.getClassName();

					pstmt1.setString(1, classTitle);
					pstmt1.setString(2, className);

					pstmt1.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Not Connected");
				} finally {

					if (pstmt1 != null) {

						pstmt1.close();

					}

					if (con != null) {
						con.close();
					}

				}

				return 1;
			}
		}
		return 0;
	} // end of addClass

	// web doc
	// Add major
	public int addMajor(User pUser, String username, String password) throws SQLException {
		List<User> userList = getMajors(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean userExists = false;

		// TODO: reformat flag condition
		for (User user : userList) {
			if (user.getMajorId().equals(pUser.getMajorId())) {
				userExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!userExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Major (majorTitle, majorAbbreviation) VALUES (?,?)");

					String majorTitle = pUser.getMajorTitle();
					String majorAbbreviation = pUser.getMajorAbbr();

					pstmt1.setString(1, majorTitle);
					pstmt1.setString(2, majorAbbreviation);

					pstmt1.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Not Connected");
				} finally {

					if (pstmt1 != null) {

						pstmt1.close();

					}

					if (con != null) {
						con.close();
					}

				}

				return 1;
			}
		}
		return 0;
	} // end of addMajor

	// Update user
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

					// pstmt2 = con.prepareStatement(
					// "UPDATE InventoryItemDb.Major SET majorTitle = ?,
					// majorAbbreviation = ? WHERE majorID = ?");
					//
					// long majorID = pUser.getMajorId();
					// String majorTitle = pUser.getMajorTitle();
					// String majorAbbreviation = pUser.getMajorAbbr();
					//
					// pstmt2.setString(1, majorTitle);
					// pstmt2.setString(2, majorAbbreviation);
					// pstmt2.setLong(3, majorID);
					//
					// pstmt3 = con.prepareStatement(
					// "UPDATE InventoryItemDb.Class SET classTitle = ?,
					// className = ? WHERE classId = ?");
					//
					// long classId = pUser.getClassId();
					// String classTitle = pUser.getClassTitle();
					// String className = pUser.getClassName();
					//
					// pstmt3.setString(1, classTitle);
					// pstmt3.setString(2, className);
					// pstmt3.setLong(3, classId);

					pstmt1.executeUpdate();
					// pstmt2.executeUpdate();
					// pstmt3.executeUpdate();

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

	// Update class
	public int updateClass(User pUser, String username, String password) throws SQLException {

		List<User> userList = getClasses(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		for (User user : userList) {
			if (user.getClassId().equals(pUser.getClassId())) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					// pstmt2 = con.prepareStatement(
					// "UPDATE InventoryItemDb.Major SET majorTitle = ?,
					// majorAbbreviation = ? WHERE majorID = ?");
					//
					// long majorID = pUser.getMajorId();
					// String majorTitle = pUser.getMajorTitle();
					// String majorAbbreviation = pUser.getMajorAbbr();
					//
					// pstmt2.setString(1, majorTitle);
					// pstmt2.setString(2, majorAbbreviation);
					// pstmt2.setLong(3, majorID);
					//
					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Class SET classTitle = ?,className = ? WHERE classId = ?");

					long classId = pUser.getClassId();
					String classTitle = pUser.getClassTitle();
					String className = pUser.getClassName();

					pstmt1.setString(1, classTitle);
					pstmt1.setString(2, className);
					pstmt1.setLong(3, classId);

					pstmt1.executeUpdate();
					// pstmt2.executeUpdate();
					// pstmt3.executeUpdate();

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
	} // end of updateClass

	// Update class
	public int updateMajor(User pUser, String username, String password) throws SQLException {

		List<User> userList = getMajors(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		for (User user : userList) {
			if (user.getMajorId().equals(pUser.getMajorId())) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Major SET majorTitle = ?,majorAbbreviation = ? WHERE majorID = ?");

					long majorID = pUser.getMajorId();
					String majorTitle = pUser.getMajorTitle();
					String majorAbbreviation = pUser.getMajorAbbr();

					pstmt1.setString(1, majorTitle);
					pstmt1.setString(2, majorAbbreviation);
					pstmt1.setLong(3, majorID);

					pstmt1.executeUpdate();

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
	} // end of updateMajor

}

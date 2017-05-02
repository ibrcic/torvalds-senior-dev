package com.ist.services.rest;

import java.sql.Connection;
import java.sql.Date;
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
	List<User> privilegeDbList = new ArrayList<User>();
	List<User> offenseDbList = new ArrayList<User>();
	List<User> sectionDbList = new ArrayList<User>();
	List<User> usersClassesDbList = new ArrayList<User>();
	List<User> usersMajorsDbList = new ArrayList<User>();

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

			// SELECT
			// Borrower.borrowerId,Borrower.username,Borrower.email,Borrower.flagged,Major.majorTitle,
			// Major.majorAbbreviation, Major.majorID, Class.classId,
			// Class.classTitle, Class.className,Section.sectionId,
			// Section.section, Priviledge.priviledgeId,
			// Priviledge.priviledgeName, Offense.offenseId,
			// Offense.offenseName, Offense.offenseDescription,
			// Offense.offenseDate, Offense.rentalId, Offense.itemId from
			// InventoryItemDb.Borrower LEFT JOIN
			// InventoryItemDb.Borrower_has_Major ON Borrower.borrowerId =
			// Borrower_has_Major.Borrower_borrowerId LEFT JOIN
			// InventoryItemDb.Major on Borrower_has_Major.Major_majorID =
			// Major.majorId LEFT JOIN InventoryItemDb.Borrower_has_Class ON
			// Borrower.borrowerId = Borrower_has_Class.Borrower_borrowerId LEFT
			// JOIN InventoryItemDb.Class on Borrower_has_Class.Class_classId =
			// Class.classId LEFT JOIN InventoryItemDb.Section on Class.classId
			// = Section.Class_classId LEFT JOIN InventoryItemDb.Priviledge ON
			// Borrower.Priviledge_priviledgeId = Priviledge.priviledgeId LEFT
			// JOIN InventoryItemDb.Offense ON Borrower.Offense_offenseId =
			// Offense.offenseId
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
		// user.setPassword(password);
		// user.setPrivilegeId(privilegeId);
		// user.setPrivilegeName(privilegeName);
		// user.setOffenseId(offenseId);
		// user.setOffenseName(offenseName);
		// user.setOffenseDescription(offenseDescription);
		// user.setOffenseDate(offenseDate);

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

	// loads only privilege related information
	public void loadData(int priviledgeId, String priviledgeName) {

		User privilege = new User();

		privilege.setPrivilegeId(priviledgeId);
		privilege.setPrivilegeName(priviledgeName);

		privilegeDbList.add(privilege);
	}

	// loads only section related information
	public void loadSectionData(long classId, int section) {

		User userSection = new User();

		userSection.setClassId(classId);
		userSection.setSection(section);

		sectionDbList.add(userSection);
	}

	// loads only users-classes related information
	public void loadDataUsersClasses(long borrowerId, long classId) {

		User usersClasses = new User();

		usersClasses.setBorrowerId(borrowerId);
		usersClasses.setClassId(classId);

		usersClassesDbList.add(usersClasses);
	}

	// loads only users-majors related information
	public void loadDataUsersMajors(long userId, long majorId) {

		User usersMajors = new User();

		usersMajors.setBorrowerId(userId);
		usersMajors.setMajorId(majorId);

		usersMajorsDbList.add(usersMajors);
	}

	// loads only offense related information
	public void loadData(int offenseId, String offenseName, String offenseDescription, Date offenseDate, long rentalId,
			long itemId) {

		User offense = new User();

		offense.setOffenseId(offenseId);
		offense.setOffenseName(offenseName);
		offense.setOffenseDescription(offenseDescription);
		offense.setOffenseDate(offenseDate);
		offense.setRentalId(rentalId);
		offense.setItemId(itemId);

		offenseDbList.add(offense);
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

	// Get privilege by id
	public User getPrivilege(int id, String username, String password) throws SQLException {
		List<User> users = getPrivileges(username, password);
		for (User user : users) {
			if (user.getPrivilegeId() == id) {
				return user;
			}
		}
		return null;
	}

	// Get section by its id
	public User getSection(long id, String username, String password) throws SQLException {
		List<User> users = getSections(username, password);
		for (User user : users) {
			if (user.getSectionId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	// Get offense by id
	public User getOffense(int id, String username, String password) throws SQLException {
		List<User> users = getOffenses(username, password);
		for (User user : users) {
			if (user.getOffenseId() == id) {
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

	// gets list of users-classes related info only
	public List<User> getUsersClasses(String username, String password) throws SQLException {
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
					"SELECT Borrower_has_Class.Borrower_borrowerId, Borrower_has_Class.Class_classId FROM InventoryItemDb.Borrower_has_Class");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long borrowerId = rs.getInt("Borrower_borrowerId");
				long classId = rs.getInt("Class_classId");

				loadDataUsersClasses(borrowerId, classId);

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
		return usersClassesDbList;
	}

	// gets list of users-classes related info only
	public List<User> getUsersMajors(String username, String password) throws SQLException {
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
					"SELECT Borrower_has_Major.Borrower_borrowerId, Borrower_has_Major.Major_majorID FROM InventoryItemDb.Borrower_has_Major");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long borrowerId = rs.getInt("Borrower_borrowerId");
				long majorId = rs.getInt("Major_majorID");

				loadDataUsersMajors(borrowerId, majorId);

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
		return usersMajorsDbList;
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

	// gets all privileges
	public List<User> getPrivileges(String username, String password) throws SQLException {
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
					"SELECT Priviledge.priviledgeId, Priviledge.priviledgeName from InventoryItemDb.Priviledge");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int priviledgeId = rs.getInt("priviledgeId");
				String priviledgeName = rs.getString("priviledgeName");

				loadData(priviledgeId, priviledgeName);

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
		return privilegeDbList;
	}

	// gets all sections
	public List<User> getSections(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println("connecting to db...");
			ConnectDb connectDb = new ConnectDb(username, password);
			con = connectDb.getConn();

			if (con != null) {
				System.out.println("Connected!");
			}

			pstmt = con.prepareStatement("SELECT Section.Class_classId, Section.section from InventoryItemDb.Section");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long classId = rs.getLong("Class_classId");
				int section = rs.getInt("section");

				loadSectionData(classId, section);

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
		return sectionDbList;
	} // getSections

	// Get sections of a class
	public List<User> getSectionsByClass(long id, String username, String password) throws SQLException {
		List<User> sections = getSections(username, password);
		List<User> classSections = new ArrayList<User>();
		for (User user : sections) {
			if (user.getClassId().equals(id)) {
				classSections.add(user);
			}
		}
		return classSections;
	}

	// Get classes of a user
	public List<User> getClassesByUser(long id, String username, String password) throws SQLException {
		List<User> classes = getUsersClasses(username, password);
		List<User> userClasses = new ArrayList<User>();
		for (User user : classes) {
			if (user.getBorrowerId().equals(id)) {
				userClasses.add(user);
			}
		}
		return userClasses;
	}

	// Get majors of a user
	public List<User> getMajorsByUser(long id, String username, String password) throws SQLException {
		List<User> majors = getUsersMajors(username, password);
		List<User> userMajors = new ArrayList<User>();
		for (User user : majors) {
			if (user.getBorrowerId().equals(id)) {
				userMajors.add(user);
			}
		}
		return userMajors;
	}

	// gets all offenses
	public List<User> getOffenses(String username, String password) throws SQLException {
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
					"SELECT Offense.offenseId, Offense.offenseName, Offense.offenseDescription, Offense.offenseDate, Offense.rentalId, Offense.itemId from InventoryItemDb.Offense");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int offenseId = rs.getInt("offenseId");
				String offenseName = rs.getString("offenseName");
				String offenseDescription = rs.getString("offenseDescription");
				Date offenseDate = rs.getDate("offenseDate");
				long rentalId = rs.getLong("rentalId");
				long itemId = rs.getLong("itemId");

				loadData(offenseId, offenseName, offenseDescription, offenseDate, rentalId, itemId);

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

	// Add user
	public int addUser(User pUser, String username, String password) throws SQLException {
		List<User> userList = getAllUsers(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean userExists = false;
		for (User user : userList) {
			if (user.getBorrowerId().equals(pUser.getBorrowerId()) || user.getUserName().equals(pUser.getUserName())
					|| user.getEmail().equals(pUser.getEmail())) {
				userExists = true;
				System.out.println("Already Exists");
				return 0;
			}
		}
		if (!userExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Borrower (borrowerId,username, email, flagged) VALUES (?, ?, ?, ?)");
				// pstmt1 = con.prepareStatement(
				// "INSERT INTO InventoryItemDb.Borrower (borrowerId,
				// username, email, flagged, password,
				// Priviledge_priviledgeId, Offense_offenseId) VALUES (?, ?,
				// ?, ?, ?, ?, ?)");

				long borrowerId = pUser.getBorrowerId();
				String borrowerUsername = pUser.getUserName();
				String email = pUser.getEmail();
				int flagged = pUser.getFlagged();
				// String userPassword = pUser.getPassword();
				// long privilegeId = pUser.getPrivilegeId();
				// long offenseId = pUser.getOffenseId();

				pstmt1.setLong(1, borrowerId);
				pstmt1.setString(2, borrowerUsername);
				pstmt1.setString(3, email);
				pstmt1.setInt(4, flagged);
				// pstmt1.setString(4, userPassword);
				// pstmt1.setLong(5, privilegeId);
				// pstmt1.setLong(6, offenseId);

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

		boolean userExists = false;
		for (User user : userList) {
			if (user.getClassTitle() != null && user.getClassName() != null) {
				if (user.getClassTitle().equals(pUser.getClassTitle())
						&& user.getClassName().equals(pUser.getClassName())) {
					userExists = true;
					System.out.println("Already Exists");
					return 0;
				}
			}
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
		return 0;
	} // end of addClass

	// web doc
	// Add major
	public int addMajor(User pUser, String username, String password) throws SQLException {
		List<User> userList = getMajors(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean userExists = false;

		for (User user : userList) {
			if (user.getMajorTitle() != null && user.getMajorAbbr() != null) {
				if (user.getMajorTitle().equals(pUser.getMajorTitle())
						&& user.getMajorAbbr().equals(pUser.getMajorAbbr())) {
					userExists = true;
					System.out.println("Already Exists");
					return 0;
				}
			}
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
		return 0;
	} // end of addMajor

	// web doc
	// Add privilege
	public int addPrivilege(User pUser, String username, String password) throws SQLException {
		List<User> userList = getPrivileges(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean userExists = false;

		// TODO: reformat flag condition
		for (User user : userList) {
			if (user.getPrivilegeName().equals(pUser.getPrivilegeName())) {
				userExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!userExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement("INSERT INTO InventoryItemDb.Privilege (privilegeName) VALUES (?)");

					String privilegeName = pUser.getPrivilegeName();

					pstmt1.setString(1, privilegeName);

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
	} // end of addPrivilege

	// web doc
	// Add section
	public int addSection(User pUser, String username, String password) throws SQLException {
		List<User> userList = getSections(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean userExists = false;

		for (User user : userList) {
			System.out.println(user.getClassId());
			if (user.getClassId().equals(pUser.getClassId()) && user.getSection() == pUser.getSection()) {
				userExists = true;
				System.out.println("Already Exists");
				return 0;
			}
		}
		if (!userExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con
						.prepareStatement("INSERT INTO InventoryItemDb.Section (Class_classId, section) VALUES (?,?)");

				long classId = pUser.getClassId();
				int section = pUser.getSection();

				pstmt1.setLong(1, classId);
				pstmt1.setLong(2, section);

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
	} // end of addSection

	// web doc
	// Add offense
	public int addOffense(User pUser, String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		boolean userExists = false;

		if (!userExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Offense (offenseName, offenseDescription, offenseDate, rentalId, itemId) VALUES (?,?,?,?,?)");

				String offenseName = pUser.getOffenseName();
				String offenseDescription = pUser.getOffenseDescription();
				Date offenseDate = pUser.getOffenseDate();
				long rentalId = pUser.getRentalId();
				long itemId = pUser.getItemId();

				pstmt1.setString(1, offenseName);
				pstmt1.setString(2, offenseDescription);
				pstmt1.setDate(3, offenseDate);
				pstmt1.setLong(4, rentalId);
				pstmt1.setLong(5, itemId);

				pstmt2 = con.prepareStatement("select last_insert_id() as last_id from InventoryItemDb.Offense");

				pstmt3 = con.prepareStatement(
						"UPDATE InventoryItemDb.Borrower SET Offense_offenseId = @last_id, flagged = 1 WHERE borrowerId = ?");

				long borrowerId = pUser.getBorrowerId();
				pstmt3.setLong(1, borrowerId);

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
		}
		return 0;
	} // end of addOffense

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
		for (User user : userList) {
			if (user.getClassId().equals(pUser.getClassId())) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Class SET classTitle = ?,className = ? WHERE classId = ?");

					long classId = pUser.getClassId();
					String classTitle = pUser.getClassTitle();
					String className = pUser.getClassName();

					pstmt1.setString(1, classTitle);
					pstmt1.setString(2, className);
					pstmt1.setLong(3, classId);

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

	// Update privilege
	public int updatePrivilege(User pUser, String username, String password) throws SQLException {

		List<User> userList = getPrivileges(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (User user : userList) {
			if (user.getPrivilegeId() == pUser.getPrivilegeId()) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Privilege SET privilegeName = ? WHERE privilegeId = ?");

					int privilegeId = pUser.getPrivilegeId();
					String privilegeName = pUser.getPrivilegeName();

					pstmt1.setString(1, privilegeName);
					pstmt1.setInt(2, privilegeId);

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
			} else {
				System.out.println("Did not update");
			}
		}

		return 0;
	} // end of updatePrivilege

	// Update section
	public int updateSection(User pUser, String username, String password) throws SQLException {

		List<User> userList = getSections(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		for (User user : userList) {
			if (user.getSectionId().equals(pUser.getSectionId())) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Section SET Class_classId = ?, section = ? WHERE sectionId = ?");

					long classId = pUser.getClassId();
					int section = pUser.getSection();
					long sectionId = pUser.getSectionId();

					pstmt1.setLong(1, classId);
					pstmt1.setInt(2, section);
					pstmt1.setLong(3, sectionId);

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
	} // end of updateSection

	// Update offense
	public int updateOffense(User pUser, String username, String password) throws SQLException {

		List<User> userList = getOffenses(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		for (User user : userList) {
			if (user.getOffenseId() == pUser.getOffenseId()) {
				int index = userList.indexOf(user);
				userList.set(index, pUser);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Offense SET offenseName = ?, offenseDescription = ?, offenseDate = ?, rentalId = ?, itemId = ? WHERE offenseId = ?");

					int offenseId = pUser.getOffenseId();
					String offenseName = pUser.getOffenseName();
					String offenseDescription = pUser.getOffenseDescription();
					Date offenseDate = pUser.getOffenseDate();
					long rentalId = pUser.getRentalId();
					long itemId = pUser.getItemId();

					pstmt1.setString(1, offenseName);
					pstmt1.setString(2, offenseDescription);
					pstmt1.setDate(3, offenseDate);
					pstmt1.setLong(4, rentalId);
					pstmt1.setLong(5, itemId);
					pstmt1.setLong(6, offenseId);

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
	} // end of updateOffense

}

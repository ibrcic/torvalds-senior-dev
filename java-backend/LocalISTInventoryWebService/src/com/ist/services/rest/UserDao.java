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
					"SELECT  Item.idItem, Item.serialNumber, Item.department, Item.aquireDate, Item.yellowTag, Item.procurementOrder, Item.cost, Item.assetTag, ItemType.ItemTypeId,  ItemType.ItemTypeName,  ItemType.image,  ItemType.manufacturer, ItemType.model, Damage.DamageId,  Damage.damageName,  Damage.damageDescription,  Damage.Severity, Warranty.warrentyId, Warranty.warrentyName, Warranty.warrantyDescription, Warranty.warantyCompany, Warranty.endDate  from InventoryItemDb.Item JOIN InventoryItemDb.ItemType ON Item.ItemType_itemTypeId = ItemType.ItemTypeId LEFT JOIN InventoryItemDb.Item_has_Damage ON Item.idItem = Item_has_Damage.Item_idItem LEFT JOIN InventoryItemDb.Damage ON Item_has_Damage.Damage_damageId = Damage.DamageId LEFT JOIN InventoryItemDb.Item_has_Warranty ON Item.idItem = Item_has_Warranty.Item_idItem LEFT JOIN Warranty ON Item_has_Warranty.Warranty_warrentyId = Warranty.warrentyId");

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

	// Add item
	public int addUser(User pUser, String username, String password) throws SQLException {
		List<User> userList = getAllUsers(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
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
	// public int updateUser(User pUser, String username, String password)
	// throws SQLException {
	//
	// List<User> userList = getAllUsers(username, password);
	// Connection con = null;
	// PreparedStatement pstmt1 = null;
	// PreparedStatement pstmt2 = null;
	// for (User user : userList) {
	// if (user.getBorrowerId().equals(pUser.getBorrowerId())) {
	// int index = userList.indexOf(user);
	// userList.set(index, pUser);
	// try {
	// ConnectDb connectDb = new ConnectDb(username, password);
	// con = connectDb.getConn();
	// pstmt1 = con.prepareStatement(
	// "UPDATE mydb.itemtype SET itemTypeName = ?, manufacturer = ?, model = ?
	// where itemTypeId = ?");
	//
	// long itemTypeId = pItem.getItemTypeId();
	// String itemTypeName = pItem.getItemTypeName();
	// String manufacturer = pItem.getManufacturer();
	// String model = pItem.getModel();
	//
	// pstmt1.setString(1, itemTypeName);
	// pstmt1.setString(2, manufacturer);
	// pstmt1.setString(3, model);
	// pstmt1.setLong(4, itemTypeId);
	//
	// pstmt2 = con.prepareStatement(
	// "UPDATE mydb.item SET serialNumber = ?, typeId = ?, department = ?,
	// aquireDate = ?, yellowTag = ?, procurementOrder = ?, cost = ?, assetTag =
	// ? where idItem = ?");
	//
	// long itemId = pItem.getIdItem();
	// String serialNumber = pItem.getSerialNumber();
	// int type = pItem.getTypeId();
	// String department = pItem.getDepartment();
	// Date aquireDate = pItem.getAquireDate();
	// int yellowTag = pItem.getYellowTag();
	// String procurementOrder = pItem.getProcurementOrder();
	// double cost = pItem.getCost();
	// String assetTag = pItem.getAssetTag();
	//
	// pstmt2.setString(1, serialNumber);
	// pstmt2.setInt(2, type);
	// pstmt2.setString(3, department);
	// pstmt2.setDate(4, aquireDate);
	// pstmt2.setInt(5, yellowTag);
	// pstmt2.setString(6, procurementOrder);
	// pstmt2.setDouble(7, cost);
	// pstmt2.setString(8, assetTag);
	// pstmt2.setLong(9, itemId);
	//
	// pstmt1.executeUpdate();
	// pstmt2.executeUpdate();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("Not Connected");
	// } finally {
	//
	// if (pstmt1 != null) {
	//
	// pstmt1.close();
	//
	// }
	//
	// if (pstmt2 != null) {
	//
	// pstmt2.close();
	//
	// }
	//
	// if (con != null) {
	// con.close();
	// }
	//
	// }
	//
	// return 1;
	// } else {
	// System.out.println("Did not update");
	// }
	// }
	//
	// return 0;
	// } // end of updateUser

}

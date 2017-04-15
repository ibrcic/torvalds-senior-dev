package com.ist.services.rest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ist.services.rest.pojo.Reservation;

/**
 * @author Hassan Jegan Ndow
 *
 */

public class ReservationDao {

	List<Reservation> reservationDbList = new ArrayList<Reservation>();

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
					"SELECT Rental.rentalId, Rental.signature, Rental.startDate, Rental.endDate, Reservation.reservationId, Reservation.Borrower_borrowerId, Reservation.ItemType_itemTypeId FROM InventoryItemDb.Rental LEFT JOIN InventoryItemDb.Reservation ON Rental.rentalId = Reservation.Rental_rentalId");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long reservationId = rs.getInt("reservationId");
				long borrowerId = rs.getInt("Borrower_borrowerId");
				long rentalId = rs.getInt("rentalId");
				long itemTypeId = rs.getInt("ItemType_itemTypeId");
				String signature = rs.getString("signature");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");

				loadData(reservationId, borrowerId, rentalId, itemTypeId, signature, startDate, endDate);

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

	public void loadData(long reservationId, long borrowerId, long rentalId, long itemTypeId, String signature,
			Date startDate, Date endDate) {
		Reservation reservation = new Reservation();
		reservation.setReservationId(reservationId);
		reservation.setBorrowerId(borrowerId);
		reservation.setRentalId(rentalId);
		reservation.setItemTypeId(itemTypeId);
		reservation.setSignature(signature);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);

		reservationDbList.add(reservation);
	}

	// Read
	public List<Reservation> getAllReservations(String username, String password) throws SQLException {
		List<Reservation> reservationList = null;
		connectDb(username, password);
		long reservationId;
		long borrowerId;
		long rentalId;
		long itemTypeId;
		String signature;
		Date startDate;
		Date endDate;

		if (reservationDbList.size() > 0) {
			reservationList = new ArrayList<Reservation>();
			for (Reservation reservation : reservationDbList) {
				reservationId = reservation.getReservationId();
				borrowerId = reservation.getBorrowerId();
				rentalId = reservation.getRentalId();
				itemTypeId = reservation.getItemTypeId();
				signature = reservation.getSignature();
				startDate = reservation.getStartDate();
				endDate = reservation.getEndDate();

				Reservation reservation2 = new Reservation();
				reservation2.setReservationId(reservationId);
				reservation2.setBorrowerId(borrowerId);
				reservation2.setRentalId(rentalId);
				reservation2.setItemTypeId(itemTypeId);
				reservation2.setSignature(signature);
				reservation2.setStartDate(startDate);
				reservation2.setEndDate(endDate);

				reservationList.add(reservation2);

			}

		}

		return reservationList;
	}

	// Get Item
	public Reservation getItem(long id, String username, String password) throws SQLException {
		List<Reservation> reservations = getAllReservations(username, password);
		for (Reservation reservation : reservations) {
			if (reservation.getReservationId() == id) {
				return reservation;
			}
		}
		return null;
	}

	// // Add item
	// public int addItem(Item pItem, String username, String password) throws
	// SQLException {
	// List<Item> itemList = getAllItems(username, password);
	// Connection con = null;
	// PreparedStatement pstmt1 = null;
	// PreparedStatement pstmt2 = null;
	// boolean itemExists = false;
	// for (Item item : itemList) {
	// if (item.getIdItem() == pItem.getIdItem()) {
	// itemExists = true;
	// System.out.println("Already Exists");
	// break;
	// }
	// if (!itemExists) {
	// try {
	//
	// ConnectDb connectDb = new ConnectDb(username, password);
	// con = connectDb.getConn();
	// pstmt1 = con.prepareStatement(
	// "INSERT INTO InventoryItemDb.ItemType (ItemTypeId, itemTypeName,
	// manufacturer, model) VALUES (?, ?, ?, ?)");
	//
	// long itemTypeId = pItem.getItemTypeId();
	// String itemTypeName = pItem.getItemTypeName();
	// String manufacturer = pItem.getManufacturer();
	// String model = pItem.getModel();
	//
	// pstmt1.setLong(1, itemTypeId);
	// pstmt1.setString(2, itemTypeName);
	// pstmt1.setString(3, manufacturer);
	// pstmt1.setString(4, model);
	//
	// pstmt2 = con.prepareStatement(
	// "INSERT INTO InventoryItemDb.Item (idItem, serialNumber, department,
	// aquireDate, yellowTag, procurementOrder, cost, assetTag,
	// ItemType_itemTypeId) VALUES (?,?,?,?,?,?,?,?,?)");
	//
	// long itemId = pItem.getIdItem();
	// String serialNumber = pItem.getSerialNumber();
	// String department = pItem.getDepartment();
	// Date aquireDate = pItem.getAquireDate();
	// int yellowTag = pItem.getYellowTag();
	// String procurementOrder = pItem.getProcurementOrder();
	// double cost = pItem.getCost();
	// String assetTag = pItem.getAssetTag();
	// long itemTypeIdFk = pItem.getItemTypeId();
	//
	// pstmt2.setLong(1, itemId);
	// pstmt2.setString(2, serialNumber);
	// pstmt2.setString(3, department);
	// pstmt2.setDate(4, aquireDate);
	// pstmt2.setInt(5, yellowTag);
	// pstmt2.setString(6, procurementOrder);
	// pstmt2.setDouble(7, cost);
	// pstmt2.setString(8, assetTag);
	// pstmt2.setLong(9, itemTypeIdFk);
	//
	// pstmt1.executeUpdate();
	// pstmt2.executeUpdate();
	// // itemList.add(pItem);
	// // System.out.println("name: " + name);
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
	// }
	// }
	// return 0;
	// }
	//
	// // Update Item
	// public int updateItem(Item pItem, String username, String password)
	// throws SQLException {
	//
	// List<Item> itemList = getAllItems(username, password);
	// Connection con = null;
	// PreparedStatement pstmt1 = null;
	// PreparedStatement pstmt2 = null;
	// for (Item item : itemList) {
	// if (item.getIdItem().equals(pItem.getIdItem())) {
	// int index = itemList.indexOf(item);
	// itemList.set(index, pItem);
	// try {
	// ConnectDb connectDb = new ConnectDb(username, password);
	// con = connectDb.getConn();
	// pstmt1 = con.prepareStatement(
	// "UPDATE InventoryItemDb.itemtype SET itemTypeName = ?, manufacturer = ?,
	// model = ? where itemTypeId = ?");
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
	// "UPDATE InventoryItemDb.item SET serialNumber = ?, typeId = ?, department
	// = ?, aquireDate = ?, yellowTag = ?, procurementOrder = ?, cost = ?,
	// assetTag = ? where idItem = ?");
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
	// }
	//
	// // Retire Item
	// public int retireItem(Item pItem, String username, String password)
	// throws SQLException {
	//
	// List<Item> itemList = getAllItems(username, password);
	// Connection con = null;
	// PreparedStatement pstmt = null;
	// for (Item item : itemList) {
	// if (item.getIdItem().equals(pItem.getIdItem())) {
	// int index = itemList.indexOf(item);
	// itemList.set(index, pItem);
	// try {
	// ConnectDb connectDb = new ConnectDb(username, password);
	// con = connectDb.getConn();
	// pstmt = con.prepareStatement("UPDATE items SET name=? where id=?");
	//
	// // long id = pItem.getId();
	// // String name = pItem.getName();
	// // String retiredName = "retired:" + name;
	// //
	// // pstmt.setString(1, retiredName);
	// // pstmt.setLong(2, id);
	// //
	// // pstmt.executeUpdate();
	// // System.out.println("Retired");
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("Not Connected");
	// } finally {
	//
	// if (pstmt != null) {
	//
	// pstmt.close();
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
	// System.out.println("Did not retire");
	// }
	// }
	//
	// return 0;
	// }
}

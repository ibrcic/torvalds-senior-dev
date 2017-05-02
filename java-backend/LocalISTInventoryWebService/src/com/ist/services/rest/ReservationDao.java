package com.ist.services.rest;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ist.services.rest.pojo.IdItemReturnList;
import com.ist.services.rest.pojo.Item;
import com.ist.services.rest.pojo.Reservation;

/**
 * @author Hassan Jegan Ndow
 *
 */

public class ReservationDao {

	List<Reservation> reservationDbList = new ArrayList<Reservation>();
	List<Reservation> rentalDbList = new ArrayList<Reservation>();
	List<Reservation> checkoutDbList = new ArrayList<Reservation>();

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
					"SELECT Reservation.reservationId, Reservation.Borrower_borrowerId,Reservation.ItemType_itemTypeId, Rental.rentalId, Rental.signature, Rental.startDate, Rental.endDate, Rental.Borrower_borrowerId, Rental_has_Item.Item_idItem, Item.department, Item.idItem, Item.barcode, Item.serialNumber, Item.department, Item.aquireDate, Item.yellowTag, Item.procurementOrder, Item.cost, Item.assetTag,ItemType.ItemTypeId,  ItemType.ItemTypeName,  ItemType.image,  ItemType.manufacturer, ItemType.model, Damage.DamageId,  Damage.damageName,  Damage.damageDescription,  Damage.Severity, Warranty.warrentyId, Warranty.warrentyName, Warranty.warrantyDescription, Warranty.warantyCompany FROM InventoryItemDb.Reservation LEFT JOIN InventoryItemDb.Rental ON Reservation.Rental_rentalId = Rental.rentalId LEFT JOIN InventoryItemDb.Rental_has_Item ON Rental.rentalId = Rental_has_Item.Rental_rentalId LEFT JOIN InventoryItemDb.Item ON Rental_has_Item.Item_idItem = Item.idItem LEFT JOIN InventoryItemDb.ItemType ON Item.ItemType_itemTypeId = ItemType.ItemTypeId LEFT JOIN InventoryItemDb.Item_has_Damage ON Item.idItem = Item_has_Damage.Item_idItem LEFT JOIN InventoryItemDb.Damage ON Item_has_Damage.Damage_damageId = Damage.DamageId LEFT JOIN InventoryItemDb.Item_has_Warranty ON Item.idItem = Item_has_Warranty.Item_idItem LEFT JOIN Warranty ON Item_has_Warranty.Warranty_warrentyId = Warranty.warrentyId");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long reservationId = rs.getInt("reservationId");
				long borrowerId = rs.getInt("Borrower_borrowerId");
				long rentalId = rs.getInt("rentalId");
				long itemTypeId = rs.getInt("ItemType_itemTypeId");
				String signature = rs.getString("signature");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				long id = rs.getInt("idItem");
				Blob barcodeBlob = rs.getBlob("barcode");
				String serialNumber = rs.getString("serialNumber");
				String department = rs.getString("department");
				Date aquireDate = rs.getDate("aquireDate");
				int yellowTag = rs.getInt("yellowTag");
				String procOrder = rs.getString("procurementOrder");
				double cost = rs.getDouble("cost");
				String assetTag = rs.getString("assetTag");
				String itemTypeName = rs.getString("ItemTypeName");
				String itemTypeManufacturer = rs.getString("manufacturer");
				String itemTypeModel = rs.getString("model");
				long damageId = rs.getInt("DamageId");
				String damageName = rs.getString("damageName");
				String damageDescription = rs.getString("damageDescription");
				int severity = rs.getInt("Severity");
				long warrentyId = rs.getInt("warrentyId");
				String warrentyName = rs.getString("warrentyName");
				String warrentyCompany = rs.getString("warantyCompany");
				String warrentyDescription = rs.getString("warrantyDescription");

				loadDataReservations(reservationId, borrowerId, rentalId, itemTypeId, signature, startDate, endDate, id,
						barcodeBlob, serialNumber, department, aquireDate, yellowTag, procOrder, cost, assetTag,
						itemTypeName, itemTypeManufacturer, itemTypeModel, damageId, damageName, damageDescription,
						severity, warrentyId, warrentyName, warrentyCompany, warrentyDescription);

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

	public void loadDataReservations(long reservationId, long borrowerId, long rentalId, long itemTypeId,
			String signature, Date startDate, Date endDate, long id, Blob barcodeBlob, String serialNumber,
			String department, Date aquireDate, int yellowTag, String procOrder, double cost, String assetTag,
			String itemTypeName, String itemTypeManufacturer, String itemTypeModel, long damageId, String damageName,
			String damageDescription, int severity, long warrentyId, String warrentyName, String warrentyCompany,
			String warrentyDescription) {

		Reservation reservation = new Reservation();
		reservation.setReservationId(reservationId);
		reservation.setBorrowerId(borrowerId);
		reservation.setRentalId(rentalId);
		reservation.setSignature(signature);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setIdItem(id);
		byte[] bytesBarcode = null;
		String barcodeString = null;
		if (barcodeBlob != null) {
			try {
				bytesBarcode = barcodeBlob.getBytes(1, (int) barcodeBlob.length());
				barcodeString = new String(bytesBarcode);
				reservation.setBarcode(barcodeString);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		reservation.setSerialNumber(serialNumber);
		reservation.setDepartment(department);
		reservation.setAquireDate(aquireDate);
		reservation.setYellowTag(yellowTag);
		reservation.setProcurementOrder(procOrder);
		reservation.setCost(cost);
		reservation.setAssetTag(assetTag);
		reservation.setItemTypeId(itemTypeId);
		reservation.setItemTypeName(itemTypeName);
		reservation.setManufacturer(itemTypeManufacturer);
		reservation.setModel(itemTypeModel);
		reservation.setDamageId(damageId);
		reservation.setDamageName(damageName);
		reservation.setDamageDescription(damageDescription);
		reservation.setSeverity(severity);
		reservation.setWarrentyId(warrentyId);
		reservation.setWarrentyName(warrentyName);
		reservation.setWarrentyDescription(warrentyDescription);
		reservation.setWarrentyCompany(warrentyCompany);

		reservationDbList.add(reservation);
	}

	public void loadDataCheckouts(long rentalId, long idItem, long userId, Date startDate, Date endDate) {

		Reservation reservation = new Reservation();
		reservation.setRentalId(rentalId);
		reservation.setIdItem(idItem);
		reservation.setBorrowerId(userId);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		checkoutDbList.add(reservation);
	}

	public void loadDataRentals(long rentalId, String signature, Date startDate, Date endDate, long borrowerId,
			long idItem, Blob barcodeBlob, String serialNumber, String department, Date aquireDate, int yellowTag,
			String procOrder, double cost, String assetTag, long itemTypeId, String itemTypeName,
			String itemTypeManufacturer, String itemTypeModel, long damageId, String damageName,
			String damageDescription, int severity, long warrentyId, String warrentyName, String warrentyCompany,
			String warrentyDescription) {

		Reservation reservation = new Reservation();
		reservation.setBorrowerId(borrowerId);
		reservation.setRentalId(rentalId);
		reservation.setSignature(signature);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setIdItem(idItem);
		byte[] bytesBarcode = null;
		String barcodeString = null;
		if (barcodeBlob != null) {
			try {
				bytesBarcode = barcodeBlob.getBytes(1, (int) barcodeBlob.length());
				barcodeString = new String(bytesBarcode);
				reservation.setBarcode(barcodeString);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		reservation.setSerialNumber(serialNumber);
		reservation.setDepartment(department);
		reservation.setAquireDate(aquireDate);
		reservation.setYellowTag(yellowTag);
		reservation.setProcurementOrder(procOrder);
		reservation.setCost(cost);
		reservation.setAssetTag(assetTag);
		reservation.setItemTypeId(itemTypeId);
		reservation.setItemTypeName(itemTypeName);
		reservation.setManufacturer(itemTypeManufacturer);
		reservation.setModel(itemTypeModel);
		reservation.setDamageId(damageId);
		reservation.setDamageName(damageName);
		reservation.setDamageDescription(damageDescription);
		reservation.setSeverity(severity);
		reservation.setWarrentyId(warrentyId);
		reservation.setWarrentyName(warrentyName);
		reservation.setWarrentyDescription(warrentyDescription);
		reservation.setWarrentyCompany(warrentyCompany);

		rentalDbList.add(reservation);
	}

	// gets list of all reservation info
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
		String itemTypeName;
		String manufacturer;
		String model;
		long itemId;
		String barcodeString;
		String serialNumber;
		String department;
		Date aquireDate;
		int yellowTag;
		String procurementOrder;
		double cost;
		String assetTag;
		long damageId;
		String damageName;
		String damageDescription;
		int severity;
		long warrentyId;
		String warrentyName;
		String warrentyCompany;
		Date warrentyEndDate;
		String warrentyDescription;

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
				itemTypeName = reservation.getItemTypeName();
				manufacturer = reservation.getManufacturer();
				model = reservation.getModel();
				itemId = reservation.getIdItem();
				barcodeString = reservation.getBarcode();
				serialNumber = reservation.getSerialNumber();
				department = reservation.getDepartment();
				aquireDate = reservation.getAquireDate();
				yellowTag = reservation.getYellowTag();
				procurementOrder = reservation.getProcurementOrder();
				cost = reservation.getCost();
				assetTag = reservation.getAssetTag();
				damageId = reservation.getDamageId();
				damageName = reservation.getDamageName();
				damageDescription = reservation.getDamageDescription();
				severity = reservation.getSeverity();
				warrentyId = reservation.getWarrentyId();
				warrentyName = reservation.getWarrentyName();
				warrentyDescription = reservation.getWarrentyDescription();
				warrentyCompany = reservation.getWarrentyCompany();
				warrentyEndDate = reservation.getWarrentyEndDate();

				Reservation reservation2 = new Reservation();
				reservation2.setReservationId(reservationId);
				reservation2.setBorrowerId(borrowerId);
				reservation2.setRentalId(rentalId);
				reservation2.setSignature(signature);
				reservation2.setStartDate(startDate);
				reservation2.setEndDate(endDate);
				reservation2.setIdItem(itemId);
				reservation2.setBarcode(barcodeString);
				reservation2.setSerialNumber(serialNumber);
				reservation2.setDepartment(department);
				reservation2.setAquireDate(aquireDate);
				reservation2.setYellowTag(yellowTag);
				reservation2.setProcurementOrder(procurementOrder);
				reservation2.setCost(cost);
				reservation2.setAssetTag(assetTag);
				reservation2.setItemTypeId(itemTypeId);
				reservation2.setItemTypeName(itemTypeName);
				reservation2.setManufacturer(manufacturer);
				reservation2.setModel(model);
				reservation2.setDamageId(damageId);
				reservation2.setDamageName(damageName);
				reservation2.setDamageDescription(damageDescription);
				reservation2.setSeverity(severity);
				reservation2.setWarrentyId(warrentyId);
				reservation2.setWarrentyName(warrentyName);
				reservation2.setWarrentyDescription(warrentyDescription);
				reservation2.setWarrentyCompany(warrentyCompany);
				reservation2.setWarrentyEndDate(warrentyEndDate);

				reservationList.add(reservation2);

			}

		}

		return reservationList;
	}

	// gets list of all rental info only
	public List<Reservation> getRentals(String username, String password) throws SQLException {
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
					"SELECT Rental.rentalId, Rental.signature, Rental.startDate, Rental.endDate, Rental.Borrower_borrowerId, Rental_has_Item.Item_idItem, Item.department, Item.idItem, Item.barcode, Item.serialNumber, Item.department, Item.aquireDate, Item.yellowTag, Item.procurementOrder, Item.cost, Item.assetTag,ItemType.ItemTypeId,  ItemType.ItemTypeName,  ItemType.image,  ItemType.manufacturer, ItemType.model, Damage.DamageId,  Damage.damageName,  Damage.damageDescription,  Damage.Severity, Warranty.warrentyId, Warranty.warrentyName, Warranty.warrantyDescription, Warranty.warantyCompany FROM InventoryItemDb.Rental LEFT JOIN InventoryItemDb.Rental_has_Item ON Rental.rentalId = Rental_has_Item.Rental_rentalId LEFT JOIN InventoryItemDb.Item ON Rental_has_Item.Item_idItem = Item.idItem LEFT JOIN InventoryItemDb.ItemType ON Item.ItemType_itemTypeId = ItemType.ItemTypeId LEFT JOIN InventoryItemDb.Item_has_Damage ON Item.idItem = Item_has_Damage.Item_idItem LEFT JOIN InventoryItemDb.Damage ON Item_has_Damage.Damage_damageId = Damage.DamageId LEFT JOIN InventoryItemDb.Item_has_Warranty ON Item.idItem = Item_has_Warranty.Item_idItem LEFT JOIN Warranty ON Item_has_Warranty.Warranty_warrentyId = Warranty.warrentyId");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long borrowerId = rs.getInt("Borrower_borrowerId");
				long rentalId = rs.getInt("rentalId");
				String signature = rs.getString("signature");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				long idItem = rs.getLong("Item_idItem");
				long itemTypeId = rs.getInt("ItemTypeId");
				Blob barcodeBlob = rs.getBlob("barcode");
				String serialNumber = rs.getString("serialNumber");
				String department = rs.getString("department");
				Date aquireDate = rs.getDate("aquireDate");
				int yellowTag = rs.getInt("yellowTag");
				String procOrder = rs.getString("procurementOrder");
				double cost = rs.getDouble("cost");
				String assetTag = rs.getString("assetTag");
				String itemTypeName = rs.getString("ItemTypeName");
				String itemTypeManufacturer = rs.getString("manufacturer");
				String itemTypeModel = rs.getString("model");
				long damageId = rs.getInt("DamageId");
				String damageName = rs.getString("damageName");
				String damageDescription = rs.getString("damageDescription");
				int severity = rs.getInt("Severity");
				long warrentyId = rs.getInt("warrentyId");
				String warrentyName = rs.getString("warrentyName");
				String warrentyCompany = rs.getString("warantyCompany");
				String warrentyDescription = rs.getString("warrantyDescription");

				loadDataRentals(rentalId, signature, startDate, endDate, borrowerId, idItem, barcodeBlob, serialNumber,
						department, aquireDate, yellowTag, procOrder, cost, assetTag, itemTypeId, itemTypeName,
						itemTypeManufacturer, itemTypeModel, damageId, damageName, damageDescription, severity,
						warrentyId, warrentyName, warrentyCompany, warrentyDescription);
				;

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
		return rentalDbList;
	}

	// gets list of checkout info only
	public List<Reservation> getCheckouts(String username, String password) throws SQLException {
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
					"SELECT Rental_has_Item.Rental_rentalId, Rental_has_Item.Item_idItem, Rental_has_Item.Rental_Student_studentId, Rental.startDate, Rental.endDate FROM InventoryItemDb.Rental_has_Item LEFT JOIN InventoryItemDb.Rental ON Rental_has_Item.Rental_rentalId = Rental.rentalId");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long borrowerId = rs.getInt("Rental_Student_studentId");
				long rentalId = rs.getInt("Rental_rentalId");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				long idItem = rs.getLong("Item_idItem");

				loadDataCheckouts(rentalId, idItem, borrowerId, startDate, endDate);

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
		return checkoutDbList;
	}

	// Get Reservation by its id
	public Reservation getReservation(long id, String username, String password) throws SQLException {
		List<Reservation> reservations = getAllReservations(username, password);
		for (Reservation reservation : reservations) {
			if (reservation.getReservationId().equals(id)) {
				return reservation;
			}
		}
		return null;
	}

	// Get Rental by its id
	public Reservation getRental(long id, String username, String password) throws SQLException {
		List<Reservation> reservations = getRentals(username, password);
		for (Reservation reservation : reservations) {
			if (reservation.getRentalId().equals(id)) {
				return reservation;
			}
		}
		return null;
	}

	// Get rentals of a borrower
	public List<Reservation> getRentalsByBorrower(long id, String username, String password) throws SQLException {
		List<Reservation> rentals = getRentals(username, password);
		List<Reservation> borrowerRentals = new ArrayList<Reservation>();
		for (Reservation reservation : rentals) {
			if (reservation.getBorrowerId().equals(id)) {
				borrowerRentals.add(reservation);
			}
		}
		return borrowerRentals;
	}

	// Get reservation
	public List<Reservation> getReservations(long id, String username, String password) throws SQLException {
		List<Reservation> reservations = getAllReservations(username, password);
		List<Reservation> borrowerReservations = new ArrayList<Reservation>();
		for (Reservation reservation : reservations) {
			if (reservation.getBorrowerId().equals(id)) {
				borrowerReservations.add(reservation);
			}
		}
		return borrowerReservations;
	}

	// Add reservation
	public Long addReservation(Reservation pReservation, String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		long rentalId = 0;

		try {

			ConnectDb connectDb = new ConnectDb(username, password);
			con = connectDb.getConn();

			pstmt1 = con
					.prepareStatement("INSERT INTO InventoryItemDb.Rental (Rental.Borrower_borrowerId)  VALUES (?)");

			long borrowerId = pReservation.getBorrowerId();

			pstmt1.setLong(1, borrowerId);

			pstmt2 = con.prepareStatement("select last_insert_id() as last_id from InventoryItemDb.Rental");

			pstmt1.executeUpdate();
			ResultSet rs = pstmt2.executeQuery();
			while (rs.next()) {
				rentalId = rs.getLong("last_id");
			}

			pstmt3 = con.prepareStatement(
					"INSERT INTO InventoryItemDb.Reservation (Reservation.Borrower_borrowerId, Reservation.Rental_rentalId, Reservation.ItemType_itemTypeId) VALUES (?, ?, ?)");

			long itemTypeId = pReservation.getItemTypeId();

			pstmt3.setLong(1, borrowerId);
			pstmt3.setLong(2, rentalId);
			pstmt3.setLong(3, itemTypeId);

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

			if (pstmt4 != null) {

				pstmt3.close();

			}

			if (con != null) {
				con.close();
			}

		}

		return rentalId;

	} // end of addReservation

	// Add rental
	public Long addRental(Reservation pReservation, String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		long rentalId = 0;

		try {

			ConnectDb connectDb = new ConnectDb(username, password);
			con = connectDb.getConn();

			pstmt1 = con.prepareStatement(
					"INSERT INTO InventoryItemDb.Rental (Rental.signature, Rental.startDate, Rental.endDate, Rental.Borrower_borrowerId)  VALUES (?, ?, ?, ?)");

			String signature = pReservation.getSignature();
			Date startDate = pReservation.getStartDate();
			Date endDate = pReservation.getEndDate();
			long borrowerId = pReservation.getBorrowerId();

			pstmt1.setString(1, signature);
			pstmt1.setDate(2, startDate);
			pstmt1.setDate(3, endDate);
			pstmt1.setLong(4, borrowerId);

			pstmt2 = con.prepareStatement("select last_insert_id() as last_id from InventoryItemDb.Rental");

			pstmt1.executeUpdate();
			ResultSet rs = pstmt2.executeQuery();
			while (rs.next()) {
				rentalId = rs.getLong("last_id");
			}

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

		return rentalId;
	} // end of addRental

	// Attach item to rental
	public int attachItem(Reservation pReservation, String username, String password) throws SQLException {
		List<Reservation> rentals = getRentals(username, password);
		List<Reservation> rentalsItems = getCheckouts(username, password);
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.getAllItems(username, password);
		boolean itemIdExists = false;
		boolean rentalIdExists = false;
		boolean checkoutExists = true;
		for (Item item : items) {
			if (item.getIdItem().equals(pReservation.getIdItem())) {
				itemIdExists = true;
			}
		}
		for (Reservation rental : rentals) {
			if (rental.getRentalId().equals(pReservation.getRentalId())) {
				rentalIdExists = true;
			}
		}

		for (Reservation rental : rentalsItems) {
			if (rental.getIdItem() != null && rental.getBorrowerId() != null && rental.getRentalId() != null) {
				if (rental.getIdItem().equals(pReservation.getIdItem())
						&& rental.getBorrowerId().equals(pReservation.getBorrowerId())
						&& rental.getRentalId().equals(pReservation.getRentalId())) {
					checkoutExists = false;
					System.out.println("Already Exists");
					return 0;
				}
			}
		}

		if (itemIdExists && rentalIdExists && checkoutExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Rental_has_Item(Rental_has_Item.Rental_rentalId,Rental_has_Item.Rental_Student_studentId,Rental_has_Item.Item_idItem) VALUES (?, ?, ?)");

				long rentalId = pReservation.getRentalId();
				long itemId = pReservation.getIdItem();
				long borrowerId = pReservation.getBorrowerId();

				pstmt1.setLong(1, rentalId);
				pstmt1.setLong(2, borrowerId);
				pstmt1.setLong(3, itemId);

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
	} // attachItem

	// Checkout item to rental
	public int checkoutItem(Reservation pReservation, String username, String password) throws SQLException {
		List<Reservation> rentals = getRentals(username, password);
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.getAllItems(username, password);
		List<Long> itemsToCheckout = pReservation.getIdItemList();
		Long borrowerId = pReservation.getBorrowerId();
		Long rentalId = pReservation.getRentalId();
		boolean itemIdExists = false;
		boolean rentalIdExists = false;
		for (Item item : items) {
			if (itemsToCheckout.contains(item.getIdItem())) {
				itemIdExists = true;
				System.out.println("Contains element");
			}
		}
		for (Reservation rental : rentals) {
			if (rental.getBorrowerId().equals(pReservation.getBorrowerId())) {
				rentalIdExists = true;
			}
		}

		if (itemIdExists && rentalIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				for (Long checkoutItem : itemsToCheckout) {
					Reservation checkoutReservation = new Reservation();
					checkoutReservation.setIdItem(checkoutItem);
					checkoutReservation.setBorrowerId(borrowerId);
					checkoutReservation.setRentalId(rentalId);
					attachItem(checkoutReservation, username, password);
				}

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
	} // checkoutItem

	// Check-in item from rental
	public int checkInItem(Reservation pReservation, String username, String password) throws SQLException {
		List<Reservation> rentals = getRentals(username, password);
		List<IdItemReturnList> itemsToCheckIn = pReservation.getIdItemReturnList();

		for (IdItemReturnList item : itemsToCheckIn) {
			System.out.println(item.getRentalId() + " " + item.getItemID());
		}
		Long borrowerId = pReservation.getBorrowerId();
		boolean rentalIdExists = false;

		for (Reservation rental : rentals) {
			if (rental.getBorrowerId().equals(pReservation.getBorrowerId())) {
				rentalIdExists = true;
				System.out.println("Equals element");
			}
		}

		if (rentalIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				for (IdItemReturnList checkInItem : itemsToCheckIn) {
					Reservation checkInReservation = new Reservation();
					checkInReservation.setIdItemReturnList(itemsToCheckIn);
					checkInReservation.setIdItem(checkInItem.getItemID());
					checkInReservation.setBorrowerId(borrowerId);
					checkInReservation.setRentalId(checkInItem.getRentalId());
					detachItem(checkInReservation, username, password);
				}

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
	} // checkoutItem

	// Detach item from rental
	public int detachItem(Reservation pReservation, String username, String password) throws SQLException {
		List<Reservation> rentals = getRentals(username, password);
		ItemDao itemDao = new ItemDao();
		List<Item> items = itemDao.getAllItems(username, password);
		boolean itemIdExists = false;
		boolean rentalIdExists = false;
		for (Item item : items) {
			if (item.getIdItem().equals(pReservation.getIdItem())) {
				itemIdExists = true;
			}
		}
		for (Reservation rental : rentals) {
			if (rental.getRentalId().equals(pReservation.getRentalId())) {
				rentalIdExists = true;
			}
		}

		if (itemIdExists && rentalIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"DELETE FROM InventoryItemDb.Rental_has_Item WHERE Rental_rentalId = ? AND Item_idItem = ? AND Rental_Student_studentId = ?");
				long borrowerId = pReservation.getBorrowerId();
				long itemId = pReservation.getIdItem();
				long rentalId = pReservation.getRentalId();

				pstmt1.setLong(1, rentalId);
				pstmt1.setLong(2, itemId);
				pstmt1.setLong(3, borrowerId);

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
	} // detachItem

	// Update reservation
	public int updateReservation(Reservation pReservation, String username, String password) throws SQLException {

		List<Reservation> reservationList = getAllReservations(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Reservation reservation : reservationList) {
			if (reservation.getReservationId().equals(pReservation.getReservationId())) {
				int index = reservationList.indexOf(reservation);
				reservationList.set(index, pReservation);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Reservation SET Reservation.ItemType_itemTypeId = ? WHERE reservationId = ?");

					long itemTypeId = pReservation.getItemTypeId();
					long reservationId = pReservation.getReservationId();
					pstmt1.setLong(1, itemTypeId);
					pstmt1.setLong(2, reservationId);

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
				System.out.println("Did not update " + reservation.getReservationId());
			}
		}

		return 0;
	} // end of updateReservation

	// Update rental
	public int updateRental(Reservation pReservation, String username, String password) throws SQLException {

		List<Reservation> reservationList = getRentals(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Reservation reservation : reservationList) {
			if (reservation.getRentalId().equals(pReservation.getRentalId())) {
				int index = reservationList.indexOf(reservation);
				reservationList.set(index, pReservation);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Rental SET Rental.signature = ?,Rental.startDate = ?, Rental.endDate = ?,Rental.Borrower_borrowerId = ? WHERE rentalId = ?");

					String signature = pReservation.getSignature();
					Date startDate = pReservation.getStartDate();
					Date endDate = pReservation.getEndDate();
					long rentalId = pReservation.getRentalId();
					long borrowerId = pReservation.getBorrowerId();

					pstmt1.setString(1, signature);
					pstmt1.setDate(2, startDate);
					pstmt1.setDate(3, endDate);
					pstmt1.setLong(4, borrowerId);
					pstmt1.setLong(5, rentalId);

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
				System.out.println("Did not update " + reservation.getRentalId());
			}
		}

		return 0;
	} // end of updateRental
}

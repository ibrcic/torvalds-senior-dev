package com.ist.services.rest;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ist.services.rest.pojo.Item;

/**
 * @author Hassan Jegan Ndow
 *
 */

public class ItemDao {

	List<Item> itemDbList = new ArrayList<Item>();

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

			// pstmt = con
			// .prepareStatement("SELECT " + "`item`.`idItem`, " +
			// "`item`.`barcode`, " + "`item`.`serialNumber`, "
			// + "`item`.`typeId`, " + "`item`.`department`, " +
			// "`item`.`aquireDate`, "
			// + "`item`.`yellowTag`, " + "`item`.`procurementOrder`, " +
			// "`item`.`cost`, "
			// + "`item`.`assetTag`, " + "`item`.`ItemType_itemTypeId` " +
			// "`itemtype`.`itemTypeId`, "
			// + "`itemtype`.`itemTypeName`, " + "`itemtype`.`image`, " +
			// "`itemtype`.`manufacturer`, "
			// + "`itemtype`.`model` " + "FROM `InventoryItemDb`.`item`" + "
			// JOIN " +
			// "`InventoryItemDb`.`item`" + " ON "
			// + "`InventoryItemDb`.`item`.`ItemType_itemTypeId` =
			// `InventoryItemDb`.`itemtype`.`itemTypeId`");

			pstmt = con.prepareStatement(
					"SELECT item.idItem,item.serialNumber,item.typeId,item.department,item.aquireDate,item.yellowTag,item.procurementOrder,item.cost,item.assetTag,itemtype.itemTypeId, itemtype.itemTypeName, itemtype.image, itemtype.manufacturer,itemtype.model from InventoryItemDb.item JOIN InventoryItemDb.itemtype ON item.ItemType_itemTypeId = itemtype.itemTypeId;");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long id = rs.getInt("idItem");
				String serialNumber = rs.getString("serialNumber");
				int typeId = rs.getInt("typeId");
				String department = rs.getString("department");
				Date acquireDate = rs.getDate("aquireDate");
				int yellowTag = rs.getInt("yellowTag");
				String procOrder = rs.getString("procurementOrder");
				double cost = rs.getDouble("cost");
				String assetTag = rs.getString("assetTag");
				long itemTypeId = rs.getInt("itemTypeId");
				String itemTypeName = rs.getString("itemTypeName");
				String itemTypeManufacturer = rs.getString("manufacturer");
				String itemTypeModel = rs.getString("model");

				loadData(id, serialNumber, typeId, department, acquireDate, yellowTag, procOrder, cost, assetTag,
						itemTypeId, itemTypeName, itemTypeManufacturer, itemTypeModel);

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

	public void loadData(long id, String serialNumber, int typeId, String department, Date acquireDate, int yellowTag,
			String procOrder, double cost, String assetTag, long itemTypeId, String itemTypeName,
			String itemTypeManufacturer, String itemTypeModel) {
		Image image = null;
		Item item = new Item(itemTypeId, itemTypeName, image, itemTypeManufacturer, itemTypeModel, id, serialNumber,
				typeId, department, acquireDate, yellowTag, procOrder, cost, assetTag);

		itemDbList.add(item);
	}

	// add item
	public int addItem(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean itemExists = false;
		for (Item item : itemList) {
			if (item.getItemId() == pItem.getItemId()) {
				itemExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!itemExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt = con.prepareStatement(
							"INSERT INTO items (id, name, barcode, serialNumber, description, image, type"
									+ ",department,acquireDate,manufacturer,model,yellowTag,procOrder,cost,assetTag,"
									+ "location, waitList) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					// long id = pItem.getId();
					// String name = pItem.getName();
					// long barcode = pItem.getBarcode();
					// long serialNumber = pItem.getSerialNumber();
					// String description = pItem.getDescription();
					// String image = pItem.getImage();
					// String type = pItem.getType();
					// String department = pItem.getDepartment();
					// String acquireDate = pItem.getAcquireDate();
					// String manufacturer = pItem.getManufacturer();
					// String model = pItem.getModel();
					// String yellowTag = pItem.getYellowTag();
					// String procOrder = pItem.getProcOrder();
					// double cost = pItem.getCost();
					// String assetTag = pItem.getAssetTag();
					// String location = pItem.getLocation();
					// long waitList = pItem.getWaitList();
					//
					// pstmt.setLong(1, id);
					// pstmt.setString(2, name);
					// pstmt.setLong(3, barcode);
					// pstmt.setLong(4, serialNumber);
					// pstmt.setString(5, description);
					// pstmt.setString(6, image);
					// pstmt.setString(7, type);
					// pstmt.setString(8, department);
					// pstmt.setString(9, acquireDate);
					// pstmt.setString(10, manufacturer);
					// pstmt.setString(11, model);
					// pstmt.setString(12, yellowTag);
					// pstmt.setString(13, procOrder);
					// pstmt.setDouble(14, cost);
					// pstmt.setString(15, assetTag);
					// pstmt.setString(16, location);
					// pstmt.setLong(17, waitList);
					//
					// pstmt.executeUpdate();
					// itemList.add(pItem);
					// System.out.println("name: " + name);

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

				return 1;
			}
		}
		return 0;
	}

	// Read
	public List<Item> getAllItems(String username, String password) throws SQLException {
		List<Item> itemList = null;
		connectDb(username, password);
		long itemTypeId;
		String itemTypeName;
		Image image;
		String manufacturer;
		String model;
		long itemId;
		String serialNumber;
		int typeId;
		String department;
		Date acquireDate;
		int yellowTag;
		String procurementOrder;
		double cost;
		String assetTag;

		if (itemDbList.size() > 0) {
			itemList = new ArrayList<Item>();
			for (Item item : itemDbList) {
				itemTypeId = item.getItemTypeId();
				itemTypeName = item.getName();
				image = item.getImage();
				manufacturer = item.getManufacturer();
				model = item.getModel();
				itemId = item.getItemId();
				serialNumber = item.getSerialNumber();
				typeId = item.getType();
				department = item.getDepartment();
				acquireDate = item.getAquireDate();
				yellowTag = item.getYellowTag();
				procurementOrder = item.getProcurementOrder();
				cost = item.getCost();
				assetTag = item.getAssetTag();

				itemList.add(new Item(itemTypeId, itemTypeName, image, manufacturer, model, itemId, serialNumber,
						typeId, department, acquireDate, yellowTag, procurementOrder, cost, assetTag));

			}

		}

		return itemList;
	}

	// Get Item
	public Item getItem(long id, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getItemId() == id) {
				return item;
			}
		}
		return null;
	}

	// Update Item
	public int updateItem(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt = null;
		for (Item item : itemList) {
			if (item.getItemId().equals(pItem.getItemId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt = con.prepareStatement("UPDATE items SET name=?, barcode=?, serialNumber=?, description=?, "
							+ "image=?, type=?,department=?,acquireDate=?,manufacturer=?,model=?,yellowTag=?"
							+ ",procOrder=?,cost=?,assetTag=?,location=?, waitList=? where id=?");

					// long id = pItem.getId();
					// String name = pItem.getName();
					// long barcode = pItem.getBarcode();
					// long serialNumber = pItem.getSerialNumber();
					// String description = pItem.getDescription();
					// String image = pItem.getImage();
					// String type = pItem.getType();
					// String department = pItem.getDepartment();
					// String acquireDate = pItem.getAcquireDate();
					// String manufacturer = pItem.getManufacturer();
					// String model = pItem.getModel();
					// String yellowTag = pItem.getYellowTag();
					// String procOrder = pItem.getProcOrder();
					// double cost = pItem.getCost();
					// String assetTag = pItem.getAssetTag();
					// String location = pItem.getLocation();
					// long waitList = pItem.getWaitList();
					//
					// pstmt.setString(1, name);
					// pstmt.setLong(2, barcode);
					// pstmt.setLong(3, serialNumber);
					// pstmt.setString(4, description);
					// pstmt.setString(5, image);
					// pstmt.setString(6, type);
					// pstmt.setString(7, department);
					// pstmt.setString(8, acquireDate);
					// pstmt.setString(9, manufacturer);
					// pstmt.setString(10, model);
					// pstmt.setString(11, yellowTag);
					// pstmt.setString(12, procOrder);
					// pstmt.setDouble(13, cost);
					// pstmt.setString(14, assetTag);
					// pstmt.setString(15, location);
					// pstmt.setLong(16, waitList);
					// pstmt.setLong(17, id);
					//
					// pstmt.executeUpdate();
					// System.out.println("Updated");

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

				return 1;
			} else {
				System.out.println("Did not update");
			}
		}

		return 0;
	}

	// Retire Item
	public int retireItem(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt = null;
		for (Item item : itemList) {
			if (item.getItemId().equals(pItem.getItemId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt = con.prepareStatement("UPDATE items SET name=? where id=?");

					// long id = pItem.getId();
					// String name = pItem.getName();
					// String retiredName = "retired:" + name;
					//
					// pstmt.setString(1, retiredName);
					// pstmt.setLong(2, id);
					//
					// pstmt.executeUpdate();
					// System.out.println("Retired");

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

				return 1;
			} else {
				System.out.println("Did not retire");
			}
		}

		return 0;
	}
}

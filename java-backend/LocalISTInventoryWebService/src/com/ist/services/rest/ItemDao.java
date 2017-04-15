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
	List<Item> damageDbList = new ArrayList<Item>();

	public List<Item> getDamages(String username, String password) throws SQLException {
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
					"SELECT  Damage.DamageId,  Damage.damageName,  Damage.damageDescription, Damage.Severity from InventoryItemDb.Damage");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long damageId = rs.getInt("DamageId");
				String damageName = rs.getString("damageName");
				String damageDescription = rs.getString("damageDescription");
				int severity = rs.getInt("Severity");

				loadData(damageId, damageName, damageDescription, severity);

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
		return damageDbList;
	}

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
				long id = rs.getInt("idItem");
				String serialNumber = rs.getString("serialNumber");
				String department = rs.getString("department");
				Date aquireDate = rs.getDate("aquireDate");
				int yellowTag = rs.getInt("yellowTag");
				String procOrder = rs.getString("procurementOrder");
				double cost = rs.getDouble("cost");
				String assetTag = rs.getString("assetTag");
				long itemTypeId = rs.getInt("ItemTypeId");
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
				Date endDate = rs.getDate("endDate");
				String warrentyDescription = rs.getString("warrantyDescription");

				loadData(id, serialNumber, department, aquireDate, yellowTag, procOrder, cost, assetTag, itemTypeId,
						itemTypeName, itemTypeManufacturer, itemTypeModel, damageId, damageName, damageDescription,
						severity, warrentyId, warrentyName, warrentyCompany, endDate, warrentyDescription);

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

	public void loadData(long id, String serialNumber, String department, Date aquireDate, int yellowTag,
			String procOrder, double cost, String assetTag, long itemTypeId, String itemTypeName,
			String itemTypeManufacturer, String itemTypeModel, long damageId, String damageName,
			String damageDescription, int severity, long warrentyId, String warrentyName, String warrentyCompany,
			Date endDate, String warrentyDescription) {
		Image image = null;
		Item item = new Item();
		item.setIdItem(id);
		item.setSerialNumber(serialNumber);
		item.setDepartment(department);
		item.setAquireDate(aquireDate);
		item.setYellowTag(yellowTag);
		item.setProcurementOrder(procOrder);
		item.setCost(cost);
		item.setAssetTag(assetTag);
		item.setItemTypeId(itemTypeId);
		item.setItemTypeName(itemTypeName);
		item.setManufacturer(itemTypeManufacturer);
		item.setModel(itemTypeModel);
		item.setDamageId(damageId);
		item.setDamageName(damageName);
		item.setDamageDescription(damageDescription);
		item.setSeverity(severity);
		item.setWarrentyId(warrentyId);
		item.setWarrentyName(warrentyName);
		item.setWarrentyDescription(warrentyDescription);
		item.setWarrentyCompany(warrentyCompany);
		item.setEndDate(endDate);

		itemDbList.add(item);
	}

	public void loadData(long damageId, String damageName, String damageDescription, int severity) {

		Item item = new Item();

		item.setDamageId(damageId);
		item.setDamageName(damageName);
		item.setDamageDescription(damageDescription);
		item.setSeverity(severity);

		damageDbList.add(item);
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
		Date endDate;
		String warrentyDescription;

		if (itemDbList.size() > 0) {
			itemList = new ArrayList<Item>();
			for (Item item : itemDbList) {
				itemTypeId = item.getItemTypeId();
				itemTypeName = item.getItemTypeName();
				manufacturer = item.getManufacturer();
				model = item.getModel();
				itemId = item.getIdItem();
				serialNumber = item.getSerialNumber();
				department = item.getDepartment();
				aquireDate = item.getAquireDate();
				yellowTag = item.getYellowTag();
				procurementOrder = item.getProcurementOrder();
				cost = item.getCost();
				assetTag = item.getAssetTag();
				damageId = item.getDamageId();
				damageName = item.getDamageName();
				damageDescription = item.getDamageDescription();
				severity = item.getSeverity();
				warrentyId = item.getWarrentyId();
				warrentyName = item.getWarrentyName();
				warrentyDescription = item.getWarrentyDescription();
				warrentyCompany = item.getWarrentyCompany();
				endDate = item.getEndDate();

				Item item2 = new Item();
				item2.setIdItem(itemId);
				item2.setSerialNumber(serialNumber);
				item2.setDepartment(department);
				item2.setAquireDate(aquireDate);
				item2.setYellowTag(yellowTag);
				item2.setProcurementOrder(procurementOrder);
				item2.setCost(cost);
				item2.setAssetTag(assetTag);
				item2.setItemTypeId(itemTypeId);
				item2.setItemTypeName(itemTypeName);
				item2.setManufacturer(manufacturer);
				item2.setModel(model);
				item2.setDamageId(damageId);
				item2.setDamageName(damageName);
				item2.setDamageDescription(damageDescription);
				item2.setSeverity(severity);
				item2.setWarrentyId(warrentyId);
				item2.setWarrentyName(warrentyName);
				item2.setWarrentyDescription(warrentyDescription);
				item2.setWarrentyCompany(warrentyCompany);
				item2.setEndDate(endDate);

				itemList.add(item2);

			}

		}

		return itemList;
	}

	// Get Item by id
	public Item getItem(long id, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getIdItem() == id) {
				return item;
			}
		}
		return null;
	}

	// Get Item by serial number
	public Item getItemBySerial(String serialNumber, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getSerialNumber().equals(serialNumber)) {
				return item;
			}
		}
		return null;
	}

	// Add item
	public int addItem(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		boolean itemExists = false;
		for (Item item : itemList) {
			if (item.getIdItem() == pItem.getIdItem()) {
				itemExists = true;
				System.out.println("Already Exists");
				break;
			}
			if (!itemExists) {
				try {

					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt1 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.ItemType (ItemTypeId, itemTypeName, manufacturer, model) VALUES (?, ?, ?, ?)");

					long itemTypeId = pItem.getItemTypeId();
					String itemTypeName = pItem.getItemTypeName();
					String manufacturer = pItem.getManufacturer();
					String model = pItem.getModel();

					pstmt1.setLong(1, itemTypeId);
					pstmt1.setString(2, itemTypeName);
					pstmt1.setString(3, manufacturer);
					pstmt1.setString(4, model);

					pstmt2 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Item (idItem, serialNumber, department, aquireDate, yellowTag, procurementOrder, cost, assetTag, ItemType_itemTypeId) VALUES (?,?,?,?,?,?,?,?,?)");

					long itemId = pItem.getIdItem();
					String serialNumber = pItem.getSerialNumber();
					String department = pItem.getDepartment();
					Date aquireDate = pItem.getAquireDate();
					int yellowTag = pItem.getYellowTag();
					String procurementOrder = pItem.getProcurementOrder();
					double cost = pItem.getCost();
					String assetTag = pItem.getAssetTag();
					long itemTypeIdFk = pItem.getItemTypeId();

					pstmt2.setLong(1, itemId);
					pstmt2.setString(2, serialNumber);
					pstmt2.setString(3, department);
					pstmt2.setDate(4, aquireDate);
					pstmt2.setInt(5, yellowTag);
					pstmt2.setString(6, procurementOrder);
					pstmt2.setDouble(7, cost);
					pstmt2.setString(8, assetTag);
					pstmt2.setLong(9, itemTypeIdFk);

					pstmt3 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Damage (DamageId, damageName, damageDescription, Severity) VALUES (?, ?, ?, ?)");

					long damageId = pItem.getDamageId();
					String damageName = pItem.getDamageName();
					String damageDescription = pItem.getDamageDescription();
					int severity = pItem.getSeverity();

					pstmt3.setLong(1, damageId);
					pstmt3.setString(2, damageName);
					pstmt3.setString(3, damageDescription);
					pstmt3.setInt(4, severity);

					pstmt4 = con.prepareStatement(
							"INSERT INTO InventoryItemDb.Warranty (warrentyId, warrentyName, warantyCompany, endDate, warrantyDescription) VALUES (?, ?, ?, ?, ?)");

					long warrentyId = pItem.getWarrentyId();
					String warrentyName = pItem.getWarrentyName();
					String warrentyCompany = pItem.getWarrentyCompany();
					Date endDate = pItem.getEndDate();
					String warrentyDescription = pItem.getWarrentyDescription();

					pstmt4.setLong(1, warrentyId);
					pstmt4.setString(2, warrentyName);
					pstmt4.setString(3, warrentyCompany);
					pstmt4.setDate(4, endDate);
					pstmt4.setString(5, warrentyDescription);

					pstmt1.executeUpdate();
					pstmt2.executeUpdate();
					pstmt3.executeUpdate();
					pstmt4.executeUpdate();
					// itemList.add(pItem);
					// System.out.println("name: " + name);

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

					if (con != null) {
						con.close();
					}

				}

				return 1;
			}
		}
		return 0;
	}

	// Update Item
	public int updateItem(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		for (Item item : itemList) {
			if (item.getIdItem().equals(pItem.getIdItem())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.ItemType SET itemTypeName = ?, manufacturer = ?, model = ? where ItemTypeId = ?");

					long itemTypeId = pItem.getItemTypeId();
					String itemTypeName = pItem.getItemTypeName();
					String manufacturer = pItem.getManufacturer();
					String model = pItem.getModel();

					pstmt1.setString(1, itemTypeName);
					pstmt1.setString(2, manufacturer);
					pstmt1.setString(3, model);
					pstmt1.setLong(4, itemTypeId);

					pstmt2 = con.prepareStatement(
							"UPDATE InventoryItemDb.Item SET serialNumber = ?, department = ?, aquireDate = ?, yellowTag = ?, procurementOrder = ?, cost = ?, assetTag = ? where idItem = ?");

					long itemId = pItem.getIdItem();
					String serialNumber = pItem.getSerialNumber();
					String department = pItem.getDepartment();
					Date aquireDate = pItem.getAquireDate();
					int yellowTag = pItem.getYellowTag();
					String procurementOrder = pItem.getProcurementOrder();
					double cost = pItem.getCost();
					String assetTag = pItem.getAssetTag();

					pstmt2.setString(1, serialNumber);
					pstmt2.setString(2, department);
					pstmt2.setDate(3, aquireDate);
					pstmt2.setInt(4, yellowTag);
					pstmt2.setString(5, procurementOrder);
					pstmt2.setDouble(6, cost);
					pstmt2.setString(7, assetTag);
					pstmt2.setLong(8, itemId);

					pstmt3 = con.prepareStatement(
							"UPDATE InventoryItemDb.Damage SET damageName = ?, damageDescription = ?, Severity = ? where DamageId = ?");

					long damageId = pItem.getDamageId();
					String damageName = pItem.getDamageName();
					String damageDescription = pItem.getDamageDescription();
					int severity = pItem.getSeverity();

					pstmt3.setString(1, damageName);
					pstmt3.setString(2, damageDescription);
					pstmt3.setInt(3, severity);
					pstmt3.setLong(4, damageId);

					pstmt4 = con.prepareStatement(
							"UPDATE InventoryItemDb.Warranty SET warrentyName = ?, warantyCompany = ?, endDate = ?, warrantyDescription = ? where warrentyId = ?");

					long warrentyId = pItem.getWarrentyId();
					String warrentyName = pItem.getWarrentyName();
					String warrentyCompany = pItem.getWarrentyCompany();
					Date endDate = pItem.getEndDate();
					String warrentyDescription = pItem.getWarrentyDescription();

					pstmt4.setString(1, warrentyName);
					pstmt4.setString(2, warrentyCompany);
					pstmt4.setDate(3, endDate);
					pstmt4.setString(4, warrentyDescription);
					pstmt4.setLong(5, warrentyId);

					pstmt1.executeUpdate();
					pstmt2.executeUpdate();
					pstmt3.executeUpdate();
					pstmt4.executeUpdate();

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
			if (item.getIdItem().equals(pItem.getIdItem())) {
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

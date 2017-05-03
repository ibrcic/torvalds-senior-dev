package com.ist.services.rest;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	List<Item> typeDbList = new ArrayList<Item>();
	List<Item> warrantyDbList = new ArrayList<Item>();
	List<Item> itemsDamagesDbList = new ArrayList<Item>();
	List<Item> itemsWarrantiesDbList = new ArrayList<Item>();

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
					"SELECT  Item.itemId, Item.barcode, Item.serialNumber, Item.department, Item.aquireDate, Item.yellowTag, Item.procurementOrder, Item.cost, Item.assetTag, ItemType.ItemTypeId,  ItemType.ItemTypeName,  ItemType.image,  ItemType.manufacturer, ItemType.model, Damage.DamageId,  Damage.damageName,  Damage.damageDescription,  Damage.Severity, Warranty.warrentyId, Warranty.warrentyName, Warranty.warrantyDescription, Warranty.warantyCompany, Warranty.endDate  from InventoryItemDb.Item JOIN InventoryItemDb.ItemType ON Item.ItemType_itemTypeId = ItemType.ItemTypeId LEFT JOIN InventoryItemDb.Item_has_Damage ON Item.itemId = Item_has_Damage.Item_itemId LEFT JOIN InventoryItemDb.Damage ON Item_has_Damage.Damage_damageId = Damage.DamageId LEFT JOIN InventoryItemDb.Item_has_Warranty ON Item.itemId = Item_has_Warranty.Item_itemId LEFT JOIN Warranty ON Item_has_Warranty.Warranty_warrentyId = Warranty.warrentyId");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long id = rs.getInt("itemId");
				Blob barcodeBlob = rs.getBlob("barcode");
				String serialNumber = rs.getString("serialNumber");
				String department = rs.getString("department");
				Date aquireDate = rs.getDate("aquireDate");
				int yellowTag = rs.getInt("yellowTag");
				String procOrder = rs.getString("procurementOrder");
				double cost = rs.getDouble("cost");
				String assetTag = rs.getString("assetTag");
				long itemTypeId = rs.getInt("ItemTypeId");
				Blob itemTypeImage = rs.getBlob("image");
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

				loadDataItems(id, barcodeBlob, serialNumber, department, aquireDate, yellowTag, procOrder, cost,
						assetTag, itemTypeId, itemTypeName, itemTypeImage, itemTypeManufacturer, itemTypeModel,
						damageId, damageName, damageDescription, severity, warrentyId, warrentyName, warrentyCompany,
						endDate, warrentyDescription);

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

	public void loadDataItems(long id, Blob barcodeBlob, String serialNumber, String department, Date aquireDate,
			int yellowTag, String procOrder, double cost, String assetTag, long itemTypeId, String itemTypeName,
			Blob itemTypeImageBlob, String itemTypeManufacturer, String itemTypeModel, long damageId, String damageName,
			String damageDescription, int severity, long warrentyId, String warrentyName, String warrentyCompany,
			Date endDate, String warrentyDescription) {

		Item item = new Item();
		item.setItemId(id);

		byte[] bytesBarcode = null;
		String barcodeString = null;
		if (barcodeBlob != null) {
			try {
				bytesBarcode = barcodeBlob.getBytes(1, (int) barcodeBlob.length());
				barcodeString = new String(bytesBarcode);
				item.setBarcode(barcodeString);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}

		byte[] bytesImage = null;
		String imageString = null;
		if (itemTypeImageBlob != null) {
			try {
				bytesImage = itemTypeImageBlob.getBytes(1, (int) itemTypeImageBlob.length());
				imageString = new String(bytesImage);
				item.setImage(imageString);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}

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

	// loads Damage data only
	public void loadDataDamages(long damageId, String damageName, String damageDescription, int severity) {

		Item item = new Item();

		item.setDamageId(damageId);
		item.setDamageName(damageName);
		item.setDamageDescription(damageDescription);
		item.setSeverity(severity);

		damageDbList.add(item);
	}

	// loads Warranty data only
	public void loadDataWarranties(long warrentyId, String warrentyName, String warrentyCompany, Date endDate,
			String warrentyDescription) {

		Item item = new Item();

		item.setWarrentyId(warrentyId);
		item.setWarrentyName(warrentyName);
		item.setWarrentyDescription(warrentyDescription);
		item.setWarrentyCompany(warrentyCompany);
		item.setEndDate(endDate);

		warrantyDbList.add(item);
	}

	// web doc
	// loads ItemType data only
	public void loadDataItemTypes(long itemTypeId, String itemTypeName, Blob itemTypeImageBlob,
			String itemTypeManufacturer, String itemTypeModel) {

		Item item = new Item();

		byte[] bytesImage = null;
		String imageString = null;
		if (itemTypeImageBlob != null) {
			try {
				bytesImage = itemTypeImageBlob.getBytes(1, (int) itemTypeImageBlob.length());
				imageString = new String(bytesImage);
				item.setImage(imageString);
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		item.setItemTypeId(itemTypeId);
		item.setItemTypeName(itemTypeName);
		item.setManufacturer(itemTypeManufacturer);
		item.setModel(itemTypeModel);

		typeDbList.add(item);
	}

	// loads items-has-damages related-info only
	public void loadDataItemsDamages(long itemId, long damageId) {

		Item itemsDamages = new Item();

		itemsDamages.setItemId(itemId);
		itemsDamages.setDamageId(damageId);

		itemsDamagesDbList.add(itemsDamages);
	}

	// loads items-has-warranties related-info only
	public void loadDataItemsWarranties(long itemId, long warrantyId) {

		Item itemsWarranties = new Item();

		itemsWarranties.setItemId(itemId);
		itemsWarranties.setWarrentyId(warrantyId);

		itemsWarrantiesDbList.add(itemsWarranties);
	}

	// gets all items and their type, damage, and warranty info
	public List<Item> getAllItems(String username, String password) throws SQLException {
		List<Item> itemList = null;
		connectDb(username, password);
		long itemTypeId;
		String itemTypeName;
		String imageString;
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
		Date endDate;
		String warrentyDescription;

		if (itemDbList.size() > 0) {
			itemList = new ArrayList<Item>();
			for (Item item : itemDbList) {
				itemTypeId = item.getItemTypeId();
				itemTypeName = item.getItemTypeName();
				manufacturer = item.getManufacturer();
				model = item.getModel();
				itemId = item.getItemId();
				barcodeString = item.getBarcode();
				imageString = item.getImage();
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
				item2.setItemId(itemId);
				item2.setBarcode(barcodeString);
				item2.setImage(imageString);
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

	// Gets item by id
	public Item getItem(long id, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getItemId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	// Attach warranty to item
	public int attachWarranty(Item pItem, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		List<Item> warranties = getWarranties(username, password);
		boolean itemIdExists = false;
		boolean warrantyIdExists = false;
		for (Item item : items) {
			if (item.getItemId().equals(pItem.getItemId())) {
				itemIdExists = true;
			}
		}
		for (Item warranty : warranties) {
			if (warranty.getWarrentyId().equals(pItem.getWarrentyId())) {
				warrantyIdExists = true;
			}
		}

		if (itemIdExists && warrantyIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT INTO InventoryItemDb.Item_has_Warranty(Item_has_Warranty.Item_itemId,Item_has_Warranty.Warranty_warrentyId) VALUES (?,?)");
				long itemId = pItem.getItemId();
				long warrantyId = pItem.getWarrentyId();

				pstmt1.setLong(1, itemId);
				pstmt1.setLong(2, warrantyId);

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
	} // attachWarranty

	// Attach damage to item
	public int attachDamage(Item pItem, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		List<Item> damages = getDamages(username, password);
		boolean itemIdExists = false;
		boolean damageIdExists = false;
		for (Item item : items) {
			if (item.getItemId().equals(pItem.getItemId())) {
				itemIdExists = true;
			}
		}
		for (Item damage : damages) {
			if (damage.getDamageId().equals(pItem.getDamageId())) {
				damageIdExists = true;
			}
		}

		if (itemIdExists && damageIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT IGNORE INTO InventoryItemDb.Item_has_Damage(Item_has_Damage.Item_itemId, Item_has_Damage.Damage_damageId) VALUES (?,?)");
				long itemId = pItem.getItemId();
				long damageId = pItem.getDamageId();

				pstmt1.setLong(1, itemId);
				pstmt1.setLong(2, damageId);

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
	} // attachDamage

	// Deletes an item type
	public int deleteItemType(Item pItem, String username, String password) throws SQLException {
		List<Item> itemTypes = getItemTypes(username, password);
		boolean itemTypeIdExists = false;
		for (Item type : itemTypes) {
			if (type.getItemId().equals(pItem.getItemId())) {
				itemTypeIdExists = true;
			}
		}

		if (itemTypeIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement("DELETE FROM InventoryItemDb.ItemType WHERE ItemTypeId = ?");
				long itemTypeId = pItem.getItemTypeId();

				pstmt1.setLong(1, itemTypeId);

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
	} // deleteItemType

	// Detach damage from item
	public int detachDamage(Item pItem, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		List<Item> damages = getDamages(username, password);
		boolean itemIdExists = false;
		boolean damageIdExists = false;
		for (Item item : items) {
			if (item.getItemId().equals(pItem.getItemId())) {
				itemIdExists = true;
			}
		}
		for (Item damage : damages) {
			if (damage.getDamageId().equals(pItem.getDamageId())) {
				damageIdExists = true;
			}
		}

		if (itemIdExists && damageIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"DELETE FROM InventoryItemDb.Item_has_Damage WHERE Item_itemId = ? AND Damage_damageId = ?");
				long itemId = pItem.getItemId();
				long damageId = pItem.getDamageId();

				pstmt1.setLong(1, itemId);
				pstmt1.setLong(2, damageId);

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
	} // detatchDamage

	// Detach warranty from item
	public int detachWarranty(Item pItem, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		List<Item> warranties = getWarranties(username, password);
		boolean itemIdExists = false;
		boolean warrantyIdExists = false;
		for (Item item : items) {
			if (item.getItemId().equals(pItem.getItemId())) {
				itemIdExists = true;
			}
		}
		for (Item warranty : warranties) {
			if (warranty.getDamageId().equals(pItem.getDamageId())) {
				warrantyIdExists = true;
			}
		}

		if (itemIdExists && warrantyIdExists) {

			Connection con = null;
			PreparedStatement pstmt1 = null;

			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"DELETE FROM InventoryItemDb.Item_has_Warranty WHERE Item_itemId = ? AND Warranty_warrentyId = ?");
				long itemId = pItem.getItemId();
				long warrantyId = pItem.getWarrentyId();

				pstmt1.setLong(1, itemId);
				pstmt1.setLong(2, warrantyId);

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
	} // detachWarranty

	// Gets item by serial number
	public Item getItemBySerial(String serialNumber, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getSerialNumber().equals(serialNumber)) {
				return item;
			}
		}
		return null;
	}

	// Gets item by barcode
	public Item getItemByBarcode(String barcode, String username, String password) throws SQLException {
		List<Item> items = getAllItems(username, password);
		for (Item item : items) {
			if (item.getBarcode() != null) {
				if (item.getBarcode().equals(barcode)) {
					return item;
				}
			}
		}
		return null;
	}

	// Gets item type by its id
	public Item getItemTypeById(long id, String username, String password) throws SQLException {
		List<Item> items = getItemTypes(username, password);
		for (Item item : items) {
			if (item.getItemTypeId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	// Get damage by its id
	public Item getDamageById(long id, String username, String password) throws SQLException {
		List<Item> items = getDamages(username, password);
		for (Item item : items) {
			if (item.getDamageId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	// Get damages of an item
	public List<Item> getDamagesByItem(long id, String username, String password) throws SQLException {
		List<Item> damages = getItemsDamages(username, password);
		List<Item> itemDamages = new ArrayList<Item>();
		for (Item item : damages) {
			if (item.getItemId().equals(id)) {
				itemDamages.add(item);
			}
		}
		return itemDamages;
	}

	// Get warranties of an item
	public List<Item> getWarrantiesByItem(long id, String username, String password) throws SQLException {
		List<Item> warranties = getItemsWarranties(username, password);
		List<Item> itemWarranties = new ArrayList<Item>();
		for (Item item : warranties) {
			if (item.getItemId().equals(id)) {
				itemWarranties.add(item);
			}
		}
		return itemWarranties;
	}

	// Get warranty by its id
	public Item getWarrantyById(long id, String username, String password) throws SQLException {
		List<Item> items = getWarranties(username, password);
		for (Item item : items) {
			if (item.getWarrentyId().equals(id)) {
				return item;
			}
		}
		return null;
	}

	// Add item
	public int addItem(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getAllItems(username, password);
		List<Item> itemTypeList = getItemTypes(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		boolean itemExists = false;
		int count = 0;
		for (Item item : itemTypeList) {
			if (item.getItemTypeId().equals(pItem.getItemTypeId())) {
				count++;

				System.out.println(item.getItemTypeId());
			}
		}

		for (Item item : itemList) {
			if (item.getBarcode() != null && item.getSerialNumber() != null) {
				System.out.println(item.getBarcode());
				if ((item.getBarcode().equals(pItem.getBarcode())
						|| item.getSerialNumber().equals(pItem.getSerialNumber()))) {
					itemExists = true;
					break;

				}
			}

		}

		if (!itemExists && count > 0) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();

				pstmt1 = con.prepareStatement(
						"INSERT IGNORE INTO InventoryItemDb.Item (serialNumber, department, aquireDate, yellowTag, procurementOrder, cost, assetTag, ItemType_itemTypeId, barcode) VALUES (?,?,?,?,?,?,?,?,?)");

				String barcodeString = pItem.getBarcode();
				byte[] byteContent = barcodeString.getBytes();
				Blob blob = con.createBlob();

				String serialNumber = pItem.getSerialNumber();
				String department = pItem.getDepartment();
				Date aquireDate = pItem.getAquireDate();
				int yellowTag = pItem.getYellowTag();
				String procurementOrder = pItem.getProcurementOrder();
				double cost = pItem.getCost();
				String assetTag = pItem.getAssetTag();
				long itemTypeId = pItem.getItemTypeId();

				pstmt1.setString(1, serialNumber);
				pstmt1.setString(2, department);
				pstmt1.setDate(3, aquireDate);
				pstmt1.setInt(4, yellowTag);
				pstmt1.setString(5, procurementOrder);
				pstmt1.setDouble(6, cost);
				pstmt1.setString(7, assetTag);
				pstmt1.setLong(8, itemTypeId);
				blob.setBytes(1, byteContent);
				pstmt1.setBlob(9, blob);

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
		}

		return 0;
	}

	// web doc
	// Add itemType
	public int addItemType(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getItemTypes(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean itemExists = false;
		for (Item item : itemList) {
			if (item.getItemTypeName() != null && item.getManufacturer() != null && item.getModel() != null) {
				if (item.getItemTypeName().equals(pItem.getItemTypeName())
						&& item.getManufacturer().equals(pItem.getManufacturer())
						&& item.getModel().equals(pItem.getModel())) {
					itemExists = true;
					System.out.println("Already Exists");
					return 0;
				}
			}
		}
		if (!itemExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();
				pstmt1 = con.prepareStatement(
						"INSERT IGNORE INTO InventoryItemDb.ItemType (itemTypeName, manufacturer, model, image) VALUES (?, ?, ?, ?)");

				String imageString = pItem.getImage();
				byte[] byteImageContent = imageString.getBytes();
				Blob imageBlob = con.createBlob();

				String itemTypeName = pItem.getItemTypeName();
				String manufacturer = pItem.getManufacturer();
				String model = pItem.getModel();

				pstmt1.setString(1, itemTypeName);
				pstmt1.setString(2, manufacturer);
				pstmt1.setString(3, model);
				imageBlob.setBytes(1, byteImageContent);
				pstmt1.setBlob(4, imageBlob);

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
	}

	// web doc
	// Add damage
	public int addDamage(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getDamages(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		boolean itemExists = false;
		for (Item item : itemList) {
			if (item.getDamageName() != null && item.getDamageDescription() != null) {
				if (item.getDamageName().equals(pItem.getDamageName())
						&& item.getDamageDescription().equals(pItem.getDamageDescription())
						&& item.getSeverity() == pItem.getSeverity()) {
					itemExists = true;
					System.out.println("Already Exists");
					return 0;
				}
			}
		}
		if (!itemExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();
				pstmt1 = con.prepareStatement(
						"INSERT IGNORE INTO InventoryItemDb.Damage (damageName, damageDescription, Severity) VALUES (?, ?, ?)");

				String damageName = pItem.getDamageName();
				String damageDescription = pItem.getDamageDescription();
				int severity = pItem.getSeverity();

				pstmt1.setString(1, damageName);
				pstmt1.setString(2, damageDescription);
				pstmt1.setInt(3, severity);

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
	} // end of addDamage

	// web doc
	// Add damage
	public int addWarranty(Item pItem, String username, String password) throws SQLException {
		List<Item> itemList = getWarranties(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		long passedInDate = pItem.getEndDate().getTime();
		String pEndDate = df.format(passedInDate);
		boolean itemExists = false;
		for (Item item : itemList) {

			DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
			long passedInDate2 = item.getEndDate().getTime();
			String endDate = df2.format(passedInDate2);
			if (item.getWarrentyName() != null && item.getWarrentyDescription() != null
					&& item.getWarrentyCompany() != null && item.getEndDate() != null) {
				if (item.getWarrentyName().equals(pItem.getWarrentyName())
						&& item.getWarrentyDescription().equals(pItem.getWarrentyDescription())
						&& item.getWarrentyCompany().equals(pItem.getWarrentyCompany()) && endDate.equals(pEndDate)) {
					itemExists = true;
					System.out.println("Already Exists");
					return 0;
				}
			}
		}
		if (!itemExists) {
			try {

				ConnectDb connectDb = new ConnectDb(username, password);
				con = connectDb.getConn();
				pstmt1 = con.prepareStatement(
						"INSERT IGNORE INTO InventoryItemDb.Warranty(warrentyName, warantyCompany, endDate,warrantyDescription) VALUES (?, ?, ?, ?)");

				String warrentyName = pItem.getWarrentyName();
				String warrentyCompany = pItem.getWarrentyCompany();
				Date endDate = pItem.getEndDate();
				String warrentyDescription = pItem.getWarrentyDescription();

				pstmt1.setString(1, warrentyName);
				pstmt1.setString(2, warrentyCompany);
				pstmt1.setDate(3, endDate);
				pstmt1.setString(4, warrentyDescription);

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
	} // end of addWarranty

	// Update Item
	public int updateItem(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getAllItems(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Item item : itemList) {
			if (item.getItemId().equals(pItem.getItemId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Item SET serialNumber = ?, department = ?, aquireDate = ?, yellowTag = ?, procurementOrder = ?, cost = ?, assetTag = ?, barcode = ? where itemId = ?");

					long itemId = pItem.getItemId();
					String barcode = pItem.getBarcode();
					String serialNumber = pItem.getSerialNumber();
					String department = pItem.getDepartment();
					Date aquireDate = pItem.getAquireDate();
					int yellowTag = pItem.getYellowTag();
					String procurementOrder = pItem.getProcurementOrder();
					double cost = pItem.getCost();
					String assetTag = pItem.getAssetTag();
					byte[] byteContent = barcode.getBytes();
					Blob blob = con.createBlob();

					pstmt1.setString(1, serialNumber);
					pstmt1.setString(2, department);
					pstmt1.setDate(3, aquireDate);
					pstmt1.setInt(4, yellowTag);
					pstmt1.setString(5, procurementOrder);
					pstmt1.setDouble(6, cost);
					pstmt1.setString(7, assetTag);
					blob.setBytes(1, byteContent);
					pstmt1.setBlob(8, blob);
					pstmt1.setLong(9, itemId);

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
	} // updateItem

	// Update item type
	public int updateItemType(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getItemTypes(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Item item : itemList) {
			if (item.getItemTypeId().equals(pItem.getItemTypeId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();
					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.ItemType SET itemTypeName = ?,manufacturer = ?, model = ?, image = ? where ItemTypeId =?");

					long itemTypeId = pItem.getItemTypeId();
					String itemTypeName = pItem.getItemTypeName();
					String image = pItem.getImage();
					String manufacturer = pItem.getManufacturer();
					String model = pItem.getModel();
					byte[] byteImageContent = image.getBytes();
					Blob imageBlob = con.createBlob();

					pstmt1.setString(1, itemTypeName);
					pstmt1.setString(2, manufacturer);
					pstmt1.setString(3, model);
					imageBlob.setBytes(1, byteImageContent);
					pstmt1.setBlob(4, imageBlob);
					pstmt1.setLong(5, itemTypeId);

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
	} // updateItemType

	// Update Damage
	public int updateDamage(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getDamages(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Item item : itemList) {
			if (item.getDamageId().equals(pItem.getDamageId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Damage SET damageName = ?,damageDescription = ?, Severity = ? where DamageId = ?");

					long damageId = pItem.getDamageId();
					String damageName = pItem.getDamageName();
					String damageDescription = pItem.getDamageDescription();
					int severity = pItem.getSeverity();

					pstmt1.setString(1, damageName);
					pstmt1.setString(2, damageDescription);
					pstmt1.setInt(3, severity);
					pstmt1.setLong(4, damageId);

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
	} // update damage

	// Update Warranty
	public int updateWarranty(Item pItem, String username, String password) throws SQLException {

		List<Item> itemList = getWarranties(username, password);
		Connection con = null;
		PreparedStatement pstmt1 = null;
		for (Item item : itemList) {
			if (item.getWarrentyId().equals(pItem.getWarrentyId())) {
				int index = itemList.indexOf(item);
				itemList.set(index, pItem);
				try {
					ConnectDb connectDb = new ConnectDb(username, password);
					con = connectDb.getConn();

					pstmt1 = con.prepareStatement(
							"UPDATE InventoryItemDb.Warranty SET warrentyName = ?,warantyCompany = ?, endDate = ?, warrantyDescription = ?where warrentyId = ?");

					long warrentyId = pItem.getWarrentyId();
					String warrentyName = pItem.getWarrentyName();
					String warrentyCompany = pItem.getWarrentyCompany();
					Date endDate = pItem.getEndDate();
					String warrentyDescription = pItem.getWarrentyDescription();

					pstmt1.setString(1, warrentyName);
					pstmt1.setString(2, warrentyCompany);
					pstmt1.setDate(3, endDate);
					pstmt1.setString(4, warrentyDescription);
					pstmt1.setLong(5, warrentyId);

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
	} // update warranty

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

	// web doc
	// gets list of all item-types
	public List<Item> getItemTypes(String username, String password) throws SQLException {
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
					"SELECT ItemType.ItemTypeId,  ItemType.ItemTypeName,  ItemType.image,  ItemType.manufacturer, ItemType.model from InventoryItemDb.ItemType");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long itemTypeId = rs.getInt("ItemTypeId");
				Blob itemTypeImageBlob = rs.getBlob("image");
				String itemTypeName = rs.getString("ItemTypeName");
				String itemTypeManufacturer = rs.getString("manufacturer");
				String itemTypeModel = rs.getString("model");

				loadDataItemTypes(itemTypeId, itemTypeName, itemTypeImageBlob, itemTypeManufacturer, itemTypeModel);

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
		return typeDbList;
	}

	// gets list of all damages
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

				loadDataDamages(damageId, damageName, damageDescription, severity);

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

	// gets list of items-damages related info only
	public List<Item> getItemsDamages(String username, String password) throws SQLException {
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
					"SELECT Item_has_Damage.Item_itemId, Item_has_Damage.Damage_damageId FROM InventoryItemDb.Item_has_Damage");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long itemId = rs.getInt("Item_itemId");
				long damageId = rs.getInt("Damage_damageId");

				loadDataItemsDamages(itemId, damageId);

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
		return itemsDamagesDbList;
	}

	// gets list of items-damages related info only
	public List<Item> getItemsWarranties(String username, String password) throws SQLException {
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
					"SELECT Item_has_Warranty.Item_itemId, Item_has_Warranty.Warranty_warrentyId FROM InventoryItemDb.Item_has_Warranty");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long itemId = rs.getInt("Item_itemId");
				long warrantyId = rs.getInt("Warranty_warrentyId");

				loadDataItemsWarranties(itemId, warrantyId);

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
		return itemsWarrantiesDbList;
	}

	// gets list of all warranties
	public List<Item> getWarranties(String username, String password) throws SQLException {
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
					"SELECT  Warranty.warrentyId, Warranty.warrentyName, Warranty.warrantyDescription, Warranty.warantyCompany, Warranty.endDate from InventoryItemDb.Warranty");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				long warrentyId = rs.getInt("warrentyId");
				String warrentyName = rs.getString("warrentyName");
				String warrentyCompany = rs.getString("warantyCompany");
				Date endDate = rs.getDate("endDate");
				String warrentyDescription = rs.getString("warrantyDescription");

				loadDataWarranties(warrentyId, warrentyName, warrentyCompany, endDate, warrentyDescription);

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
		return warrantyDbList;
	}
}

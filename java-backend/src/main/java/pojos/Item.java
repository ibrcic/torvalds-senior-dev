package pojos;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Item{
   
	int itemId;
	String serialNumber;
	ItemType itemType;
	String department;
	Date aquireDate;
	int yellowTag;
	String procurementOrder;
	double cost;
	String assetTag;

	/**
	 * @param itemTypeId
	 * @param itemTypeName
	 * @param image
	 * @param manufacturer
	 * @param model
	 * @param itemId
	 * @param serialNumber
	 * @param typeId
	 * @param department
	 * @param aquireDate
	 * @param yellowTag
	 * @param procurementOrder
	 * @param cost
	 * @param assetTag
	 */
	public Item(int itemTypeId, String itemTypeName, Image image, String manufacturer, String model, int itemId,
			String serialNumber, int typeId, String department, Date aquireDate, int yellowTag, String procurementOrder,
			double cost, String assetTag) {
		this.itemType = new ItemType(itemTypeId, itemTypeName, image, manufacturer, model);
		this.itemId = itemId;
		this.serialNumber = serialNumber;
		this.department = department;
		this.aquireDate = aquireDate;
		this.yellowTag = yellowTag;
		this.procurementOrder = procurementOrder;
		this.cost = cost;
		this.assetTag = assetTag;
	}   
	
	public Item(int itemId) {
		// TODO Auto-generated constructor stub
		this.itemId = itemId;
	}

	protected Item sync(Connection conn) throws SQLException{
		return null; 
	}
	public void fetch(Connection conn) throws SQLException{
		ResultSet rs;
		PreparedStatement getItemFromId;
		getItemFromId = conn.prepareStatement("SELECT "
				+ "`item`.`idItem`, "
				+ "`item`.`barcode`, "
				+ "`item`.`serialNumber`, "
				+ "`item`.`typeId`, "
				+ "`item`.`department`, "
				+ "`item`.`aquireDate`, "
				+ "`item`.`yellowTag`, "
				+ "`item`.`procurementOrder`, "
				+ "`item`.`cost`, "
				+ "`item`.`assetTag`, "
				+ "`item`.`ItemType_itemTypeId` "
				+ "FROM `inventoryitemdb`.`item`"
				+ "WHERE idItem = " + this.itemId);
		rs = getItemFromId.executeQuery();
		new Item(rs.getInt("idItem"), assetTag, null, assetTag, assetTag, itemId, assetTag, itemId, assetTag, aquireDate, itemId, assetTag, cost, assetTag);
		
	}
}

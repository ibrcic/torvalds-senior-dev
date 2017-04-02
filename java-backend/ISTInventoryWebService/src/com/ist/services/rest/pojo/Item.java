package com.ist.services.rest.pojo;

import java.awt.Image;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Item {
	long itemId;
	String serialNumber;
	ItemType itemType;
	String department;
	Date aquireDate;
	int yellowTag;
	int typeId;
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
	public Item(long itemTypeId, String itemTypeName, Image image, String manufacturer, String model, long itemId,
			String serialNumber, int typeId, String department, Date aquireDate, int yellowTag, String procurementOrder,
			double cost, String assetTag) {
		this.itemType = new ItemType(itemTypeId, itemTypeName, image, manufacturer, model);
		this.itemId = itemId;
		this.serialNumber = serialNumber;
		this.department = department;
		this.aquireDate = aquireDate;
		this.yellowTag = yellowTag;
		this.typeId = typeId;
		this.procurementOrder = procurementOrder;
		this.cost = cost;
		this.assetTag = assetTag;
	}

	public Item(int itemId) {
		// TODO Auto-generated constructor stub
		this.itemId = itemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public Long getItemTypeId() {
		return itemType.getItemTypeId();
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getType() {
		return typeId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getAquireDate() {
		return aquireDate;
	}

	public void setAquireDate(Date aquireDate) {
		this.aquireDate = aquireDate;
	}

	public int getYellowTag() {
		return yellowTag;
	}

	public void setYellowTag(int yellowTag) {
		this.yellowTag = yellowTag;
	}

	public String getManufacturer() {
		return itemType.getManufacturer();
	}

	public String getModel() {
		return itemType.getModel();
	}

	public Image getImage() {
		return itemType.getImage();
	}

	public String getName() {
		return itemType.getItemTypeName();
	}

	public String getProcurementOrder() {
		return procurementOrder;
	}

	public void setProcurementOrder(String procurementOrder) {
		this.procurementOrder = procurementOrder;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public ItemType getItemTypeObj() {
		return itemType;
	}

	protected Item sync(Connection conn) throws SQLException {
		return null;
	}

	public void fetch(Connection conn) throws SQLException {

	}

}

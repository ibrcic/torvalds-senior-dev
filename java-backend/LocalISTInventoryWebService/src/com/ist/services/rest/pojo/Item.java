package com.ist.services.rest.pojo;

import java.awt.Image;
import java.sql.Date;

public class Item {
	long itemId;
	String serialNumber;
	int typeId;
	String department;
	Date aquireDate;
	int yellowTag;
	String procurementOrder;
	double cost;
	String assetTag;

	long itemTypeId;
	String itemTypeName;
	Image image;
	String manufacturer;
	String model;

	public Long getIdItem() {
		return itemId;
	}

	public void setIdItem(Long itemId) {
		this.itemId = itemId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}

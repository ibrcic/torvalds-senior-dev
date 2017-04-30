package com.ist.services.rest.pojo;

import java.sql.Date;

public class Item {

	// item
	long itemId;
	String barcode;
	String serialNumber;
	int typeId;
	String department;
	Date aquireDate;
	int yellowTag;
	String procurementOrder;
	double cost;
	String assetTag;

	// itemtype
	long itemTypeId;
	String itemTypeName;
	String image;
	String manufacturer;
	String model;

	// damage
	long damageId;
	String damageName;
	String damageDescription;
	int severity;

	// warranty
	long warrentyId;
	String warrentyName;
	String warrentyCompany;
	Date endDate;
	String warrentyDescription;

	public Long getDamageId() {
		return damageId;
	}

	public void setDamageId(Long damageId) {
		this.damageId = damageId;
	}

	public String getDamageName() {
		return damageName;
	}

	public void setDamageName(String damageName) {
		this.damageName = damageName;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public Long getWarrentyId() {
		return warrentyId;
	}

	public void setWarrentyId(Long warrentyId) {
		this.warrentyId = warrentyId;
	}

	public String getWarrentyName() {
		return warrentyName;
	}

	public void setWarrentyName(String warrentyName) {
		this.warrentyName = warrentyName;
	}

	public String getWarrentyDescription() {
		return warrentyDescription;
	}

	public void setWarrentyDescription(String warrentyDescription) {
		this.warrentyDescription = warrentyDescription;
	}

	public String getWarrentyCompany() {
		return warrentyCompany;
	}

	public void setWarrentyCompany(String warrentyCompany) {
		this.warrentyCompany = warrentyCompany;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getIdItem() {
		return itemId;
	}

	public void setIdItem(Long itemId) {
		this.itemId = itemId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

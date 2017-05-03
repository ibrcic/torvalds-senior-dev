package com.ist.services.rest.pojo;

import java.awt.Image;
import java.sql.Date;
import java.util.List;

public class Reservation {

	long reservationId;
	long userId;
	long rentalId;
	long itemTypeId;
	String signature;
	Date startDate;
	Date endDate;

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

	String itemTypeName;
	Image image;
	String manufacturer;
	String model;

	private List<Long> idItemList = null;
	private List<IdItemReturnList> idItemReturnList = null;

	// damage
	long damageId;
	String damageName;
	String damageDescription;
	int severity;

	// warranty
	long warrentyId;
	String warrentyName;
	String warrentyCompany;
	Date warrentyEndDate;
	String warrentyDescription;

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getIdItemList() {
		return idItemList;
	}

	public void setIdItemList(List<Long> idItemList) {
		this.idItemList = idItemList;
	}

	public List<IdItemReturnList> getIdItemReturnList() {
		return idItemReturnList;
	}

	public void setIdItemReturnList(List<IdItemReturnList> idItemReturnList) {
		this.idItemReturnList = idItemReturnList;
	}

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

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

	public Date getWarrentyEndDate() {
		return endDate;
	}

	public void setWarrentyEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
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

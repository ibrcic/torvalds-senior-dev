package com.ist.services.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hassan Jegan Ndow
 *
 */

public class ItemTest {

	private String image;
	private Long serialNumber;
	private Double cost;
	private Long waitList;
	private String description;
	private String yellowTag;
	private String type;
	private String assetTag;
	private String manufacturer;
	private String procOrder;
	private String acquireDate;
	private String name;
	private String model;
	private String location;
	private Long id;
	private String department;
	private Long barcode;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Long getWaitList() {
		return waitList;
	}

	public void setWaitList(Long waitList) {
		this.waitList = waitList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYellowTag() {
		return yellowTag;
	}

	public void setYellowTag(String yellowTag) {
		this.yellowTag = yellowTag;
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProcOrder() {
		return procOrder;
	}

	public void setProcOrder(String procOrder) {
		this.procOrder = procOrder;
	}

	public String getAcquireDate() {
		return acquireDate;
	}

	public void setAcquireDate(String acquireDate) {
		this.acquireDate = acquireDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

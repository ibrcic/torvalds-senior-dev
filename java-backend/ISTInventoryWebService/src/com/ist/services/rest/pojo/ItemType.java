package com.ist.services.rest.pojo;

import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;

public class ItemType {

	long itemTypeId;
	String itemTypeName;
	Image image;
	String manufacturer;
	String model;

	/**
	 * @param itemTypeId
	 * @param itemTypeName
	 * @param image
	 * @param manufacturer
	 * @param model
	 */
	public ItemType(long itemTypeId, String itemTypeName, Image image, String manufacturer, String model) {
		this.itemTypeId = itemTypeId;
		this.itemTypeName = itemTypeName;
		this.image = null;
		this.manufacturer = manufacturer;
		this.model = model;

		// prepared statements

	}

	protected Item sync(Connection conn) throws SQLException {
		return null;

	}

	/**
	 * @param itemTypeId
	 */
	public ItemType(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	/**
	 * @return the itemTypeId
	 */
	protected long getItemTypeId() {
		return itemTypeId;
	}

	/**
	 * @return the itemTypeName
	 */
	protected String getItemTypeName() {
		return itemTypeName;
	}

	/**
	 * @return the image
	 */
	protected Image getImage() {
		return image;
	}

	/**
	 * @return the manufacturer
	 */
	protected String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @return the model
	 */
	protected String getModel() {
		return model;
	}

	/**
	 * @param itemTypeId
	 *            the itemTypeId to set
	 */
	protected void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	/**
	 * @param itemTypeName
	 *            the itemTypeName to set
	 */
	protected void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	/**
	 * @param string
	 *            the image to set
	 */
	protected void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	protected void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	protected void setModel(String model) {
		this.model = model;
	}

}

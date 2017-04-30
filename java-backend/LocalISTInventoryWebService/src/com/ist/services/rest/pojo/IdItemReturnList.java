package com.ist.services.rest.pojo;

import java.util.HashMap;
import java.util.Map;

public class IdItemReturnList {

	private long itemID;
	private long rentalId;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public Long getRentalId() {
		return rentalId;
	}

	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}
}

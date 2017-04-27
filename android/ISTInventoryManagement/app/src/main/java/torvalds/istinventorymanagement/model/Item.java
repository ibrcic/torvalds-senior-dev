package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 1/29/2017.
 * Provides methods to store and retrieve an item's information
 * Item object
 */


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Item implements Serializable{

    /*Instance variables*/

    //constant values to use as placeholders until database administrator implements/adjusts these fields
    private static final long BARCODE = 345345345;
    private static final String DESCRIPTION = "An item belonging to the IST Inventory Cage.";
    private static final String IMAGE = "http://cdn2.gsmarena.com/vv/pics/apple/apple-iphone-7-1.jpg";
    private static final String LOCATION = "Back row - D4";

    public Item(String name, String serialNumber) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    private long barcode;
    private String description;
    private String image;
    private String location;
    private long waitList;
    @SerializedName("idItem")
    private long id;
    private String procOrder;
    private String serialNumber;
    private double cost;
    private String acquireDate;
    private int yellowTag;
    @SerializedName("itemTypeName")
    private String name;
    private String model;
    private String assetTag;
    private long itemTypeId;
    private String department;
    private String manufacturer;

    public long getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public long getWaitList() {
        return waitList;
    }

    public long getId() {
        return id;
    }

    public String getProcOrder() {
        return procOrder;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getCost() {
        return cost;
    }

    public String getAcquireDate() {
        return acquireDate;
    }

    public int getYellowTag() {
        return yellowTag;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public long getItemTypeId() {
        return itemTypeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getManufacturer() {
        return manufacturer;
    }

}


package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 1/29/2017.
 * Provides methods to store and retrieve an item's information
 * Item object
 */

import com.google.gson.annotations.SerializedName;


public class Item extends ItemInterface {

    /*Instance variables*/

    //@SerializedName("")
    private String name;
    //@SerializedName("")
    private long id;
    //@SerializedName("")
    private long barcode;
    //@SerializedName("")
    private long serialNumber;
    //@SerializedName("")
    private String description;
    //@SerializedName("")
    private String image;
    @SerializedName("itemTypeId")
    private String type;
    //@SerializedName("")
    private String department;
    //@SerializedName("")
    private String aquireDate;
    //@SerializedName("")
    private String manufacturer;
    //@SerializedName("")
    private String model;
    //@SerializedName("")
    private String yellowTag;
    //@SerializedName("")
    private String procOrder;
    //@SerializedName("")
    private double cost;
    //@SerializedName("")
    private String assetTag;
    //@SerializedName("")
    private String location;
    //@SerializedName("")
    private long waitList;

    /*Constructor*/
    public Item(String name, long id, long barcode, long serialNumber, String description, String image, String
            type, String department, String aquireDate, String manufacturer, String model, String
                        yellowTag, String procOrder, double cost, String assetTag, String location, long
                        waitList){

        this.name = name;
        this.id = id;
        this.barcode = barcode;
        this.serialNumber = serialNumber;
        this.description = description;
        this.image = image;
        this.type = type;
        this.department = department;
        this.aquireDate = aquireDate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yellowTag = yellowTag;
        this.procOrder = procOrder;
        this.cost = cost;
        this.assetTag = assetTag;
        this.location = location;
        this.waitList = waitList;
    }

    /*Returns the name of an Item*/
    public String getItemName() {
        return name;
    }

    /*Returns the id of an Item*/
    long getItemId() {
        return id;
    }

    /*Returns the barcode of an Item*/
    long getItemBarcode() {
        return barcode;
    }

    /*Returns the serial number of an Item*/
    long getItemSerialNumber() {
        return serialNumber;
    }

    /*Returns the description of an Item*/
    String getItemDescription() {
        return description;
    }

    /*Returns the image of an Item*/
    String getItemImage() {
        return image;
    }

    /*Returns the type of an Item*/
    String getItemType() {
        return type;
    }

    /*Returns the department of an Item*/
    String getItemDepartment() {
        return department;
    }

    /*Returns the aquired date of an Item*/
    String getItemAquireDate() {
        return aquireDate;
    }

    /*Returns the manufacturer of an Item*/
    String getItemManufacturer() {
        return manufacturer;
    }

    /*Returns the model of an Item*/
    String getItemModel() {
        return model;
    }

    /*Returns the yellow tag's info of an Item*/
    String getItemYellowTag() {
        return yellowTag;
    }

    /*Returns the procurement order of an Item*/
    String getItemProcOrder() {
        return procOrder;
    }

    /*Returns the cost of an Item*/
    double getItemCost() {
        return cost;
    }

    /*Returns the asset tag's info of an Item*/
    String getItemAssetTag() {
        return assetTag;
    }

    /*Returns the location of an Item*/
    String getItemLocation() {
        return location;
    }

    /*Returns the waitlist of an Item*/
    long getItemWaitlist() {
        return waitList;
    }

    /*makes items comparable to each other*/
    public int compareTo(ItemInterface comparedItem) {
        return comparedItem.getItemDescription().compareTo(this.getItemDescription());
    }
}


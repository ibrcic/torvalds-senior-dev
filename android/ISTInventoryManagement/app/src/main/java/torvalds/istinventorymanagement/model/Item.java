package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 1/29/2017.
 * Provides methods to store and retrieve an item's information
 * Item object
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Item extends ItemInterface implements Serializable{

    /*Instance variables*/

    //constant values to use as placeholders until database administrator implements/adjusts these fields
    private static final long BARCODE = 345345345;
    private static final String DESCRIPTION = "An item belonging to the IST Inventory Cage.";
    private static final String IMAGE = "http://cdn2.gsmarena.com/vv/pics/apple/apple-iphone-7-1.jpg";
    private static final String LOCATION = "Back row - D4";


    //@SerializedName("")
    private long barcode;
    //@SerializedName("")
    private String description;
    //@SerializedName("")
    private String image;
    //@SerializedName("")
    private String location;
    //@SerializedName("")
    private long waitList;

    @SerializedName("idItem")
    @Expose
    private long id;
    @SerializedName("procOrder")
    @Expose
    private String procOrder;
    @SerializedName("serialNumber")
    @Expose
    private String serialNumber;
    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("acquireDate")
    @Expose
    private String acquireDate;
    @SerializedName("yellowTag")
    @Expose
    private int yellowTag;
    @SerializedName("itemTypeName")
    @Expose
    private String name;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("typeId")
    @Expose
    private int typeId;
    @SerializedName("assetTag")
    @Expose
    private String assetTag;
    @SerializedName("itemTypeId")
    @Expose
    private long itemTypeId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;

    /*Constructor*/
//    public Item(String name, long id, long barcode, long serialNumber, String description, String image, String
//            type, String department, String aquireDate, String manufacturer, String model, String
//                        yellowTag, String procOrder, double cost, String assetTag, String location, long
//                        waitList){
//
//        this.name = name;
//        this.id = id;
//        this.barcode = barcode;
//        this.serialNumber = serialNumber;
//        this.description = description;
//        this.image = image;
//        this.type = type;
//        this.department = department;
//        this.aquireDate = aquireDate;
//        this.manufacturer = manufacturer;
//        this.model = model;
//        this.yellowTag = yellowTag;
//        this.procOrder = procOrder;
//        this.cost = cost;
//        this.assetTag = assetTag;
//        this.location = location;
//        this.waitList = waitList;
//    }

    /*Returns the name of an Item*/
    public String getItemName() {
        return name;
    }

    /*Returns the id of an Item*/
    long getItemId() {
        return id;
    }

    /*Returns the barcode of an Item*/
    public long getItemBarcode() {
        return BARCODE;
    }

    /*Returns the serial number of an Item*/
    public String getItemSerialNumber() {
        return serialNumber;
    }

    /*Returns the description of an Item*/
    public String getItemDescription() {
        return DESCRIPTION;
    }

    /*Returns the image of an Item*/
    public String getItemImage() {
        return IMAGE;
    }

    /*Returns the type of an Item*/
    public int getItemType() {
        return typeId;
    }

    /*Returns the department of an Item*/
    public String getItemDepartment() {
        return department;
    }

    /*Returns the aquired date of an Item*/
    String getItemAquireDate() {
        return acquireDate;
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
    int getItemYellowTag() {
        return yellowTag;
    }

    /*Returns the procurement order of an Item*/
    String getItemProcOrder() {
        return procOrder;
    }

    /*Returns the cost of an Item*/
    public double getItemCost() {
        return cost;
    }

    /*Returns the asset tag's info of an Item*/
    public String getItemAssetTag() {
        return assetTag;
    }

    /*Returns the location of an Item*/
    public String getItemLocation() {
        return LOCATION;
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


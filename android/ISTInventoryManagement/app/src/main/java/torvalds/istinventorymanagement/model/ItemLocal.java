package torvalds.istinventorymanagement.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 */

public class ItemLocal {

    @SerializedName("idItem")
    @Expose
    private Integer idItem;
    @SerializedName("procOrder")
    @Expose
    private String procOrder;
    @SerializedName("serialNumber")
    @Expose
    private String serialNumber;
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("acquireDate")
    @Expose
    private String acquireDate;
    @SerializedName("yellowTag")
    @Expose
    private Integer yellowTag;
    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("typeId")
    @Expose
    private Integer typeId;
    @SerializedName("assetTag")
    @Expose
    private String assetTag;
    @SerializedName("itemTypeId")
    @Expose
    private Integer itemTypeId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;


    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer yellowTag) {
        this.idItem = idItem;
    }
    public String getProcOrder() {
        return procOrder;
    }

    public void setProcOrder(String procOrder) {
        this.procOrder = procOrder;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getAcquireDate() {
        return acquireDate;
    }

    public void setAcquireDate(String acquireDate) {
        this.acquireDate = acquireDate;
    }

    public Integer getYellowTag() {
        return yellowTag;
    }

    public void setYellowTag(Integer yellowTag) {
        this.yellowTag = yellowTag;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return itemTypeName;
    }
}

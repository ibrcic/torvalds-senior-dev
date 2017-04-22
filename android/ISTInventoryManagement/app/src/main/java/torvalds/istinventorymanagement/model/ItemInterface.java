package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 1/29/2017.
 * Creates methods for an Item
 * Enables an Item to become sortable
 */

public abstract class ItemInterface implements Comparable<ItemInterface> {

    /*Returns the id of an Item*/
    abstract String getItemName();

    /*Returns the id of an Item*/
    abstract long getItemId();

    /*Returns the barcode of an Item*/
    abstract long getItemBarcode();

    /*Returns the serial number of an Item*/
    abstract String getItemSerialNumber();

    /*Returns the description of an Item*/
    abstract String getItemDescription();

    /*Returns the image of an Item*/
    abstract String getItemImage();

    /*Returns the department of an Item*/
    abstract String getItemDepartment();

    /*Returns the aquired date of an Item*/
    abstract String getItemAquireDate();

    /*Returns the manufacturer of an Item*/
    abstract String getItemManufacturer();

    /*Returns the model of an Item*/
    abstract String getItemModel();

    /*Returns the yellow tag's info of an Item*/
    abstract int getItemYellowTag();

    /*Returns the procurement order of an Item*/
    abstract String getItemProcOrder();

    /*Returns the cost of an Item*/
    abstract double getItemCost();

    /*Returns the asset tag's info of an Item*/
    abstract String getItemAssetTag();

    /*Returns the location of an Item*/
    abstract String getItemLocation();

    /*Returns the waitlist of an Item*/
    abstract long getItemWaitlist();
}

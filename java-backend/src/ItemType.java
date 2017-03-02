import java.awt.Image;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Noah
 *
 */
public class ItemType {
	int itemTypeId;
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
	public ItemType(int itemTypeId, String itemTypeName, Image image, String manufacturer, String model) {
		this.itemTypeId = itemTypeId;
		this.itemTypeName = itemTypeName;
		this.image = image;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	protected void sync(){ 
	
	}
	
	/**
	 * @param itemTypeId
	 */
	public ItemType(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	
	/**
	 * @return the itemTypeId
	 */
	protected int getItemTypeId() {
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
	 * @param itemTypeId the itemTypeId to set
	 */
	protected void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	/**
	 * @param itemTypeName the itemTypeName to set
	 */
	protected void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	/**
	 * @param image the image to set
	 */
	protected void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	protected void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * @param model the model to set
	 */
	protected void setModel(String model) {
		this.model = model;
	}
	
	
}

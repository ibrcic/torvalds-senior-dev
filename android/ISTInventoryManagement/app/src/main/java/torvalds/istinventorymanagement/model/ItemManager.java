package torvalds.istinventorymanagement.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hassan Jegan Ndow on 2/11/2017.
 * Handles the setting and getting of Item objects
 */

public class ItemManager {

    private ItemCollection<String, Item> itemCollection = new ItemCollection<>();

    public ItemManager(){

    }

    /*Creates an Item object and puts it in the collection map*/
    void setItem(String name, long id, long barcode, long serialNumber, String description, String image, String
            type, String department, String aquireDate, String manufacturer, String model, String
                         yellowTag, String procOrder, double cost, String assetTag, String location, long
                         waitList){

        itemCollection.put(name, new Item(name,id,barcode,serialNumber,description,image,type,
                department,aquireDate,manufacturer,model,yellowTag,procOrder,cost,assetTag,location
                ,waitList));
    }

    /*Gets item object by it's respective name*/
    Item getItemByName(String itemName){
        return itemCollection.get(itemName);
    }
}

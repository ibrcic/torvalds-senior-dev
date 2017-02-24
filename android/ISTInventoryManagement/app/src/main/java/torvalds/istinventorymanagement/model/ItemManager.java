package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hassan Jegan Ndow on 2/11/2017.
 * Creates a Map collection of items
 * Handles the setting and retrieving of item information
 */

public class ItemManager {

    private ItemCollection<Long, Item> itemCollection = new ItemCollection<>();

    /*ItemManager constructor*/
    public ItemManager(){

    }

    /*Creates an Item object and puts it in the collection map*/
    void setItem(String name, long id, long barcode, long serialNumber, String description, String image, String
            type, String department, String aquireDate, String manufacturer, String model, String
                         yellowTag, String procOrder, double cost, String assetTag, String location, long
                         waitList){

            itemCollection.put(id, new Item(name,id,barcode,serialNumber,description,image,type,
                    department,aquireDate,manufacturer,model,yellowTag,procOrder,cost,assetTag,location
                    ,waitList));


    }

    /*Gets item object by its respective id*/
    Item getItemByID(long itemID){
        return itemCollection.get(itemID);
    }

    /*Returns a String list of all the item names  */
    List<String> listOfItemsByName() {
        List <String> itemList = new ArrayList<String>();
        for (long key : itemCollection.keySet()) {
            itemList.add(itemCollection.get(key).getItemName());
        }
        return itemList;
    }

    /*Returns a long list of all the item IDs  */
    List<Long> listOfItemsByID() {
        List <Long> itemList = new ArrayList<Long>();
        for (long key : itemCollection.keySet()) {
            itemList.add(key);
        }
        return itemList;
    }
}

package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hassan Jegan Ndow on 2/11/2017.
 * Handles the setting and getting of Item objects
 */

public class ItemManager {

    private ItemCollection<String, Item> itemCollection = new ItemCollection<>();
    String tempName = "";
    int count = 1;

    public ItemManager(){

    }

    /*Creates an Item object and puts it in the collection map*/
    void setItem(String name, long id, long barcode, long serialNumber, String description, String image, String
            type, String department, String aquireDate, String manufacturer, String model, String
                         yellowTag, String procOrder, double cost, String assetTag, String location, long
                         waitList){



        if(tempName.equals(name)){
            ++count;
            itemCollection.put(name+"#"+count, new Item(name+"#"+count,id,barcode,serialNumber,description,image,type,
                    department,aquireDate,manufacturer,model,yellowTag,procOrder,cost,assetTag,location
                    ,waitList));
        }
        else {
            tempName = name;
            itemCollection.put(name, new Item(name, id, barcode, serialNumber, description, image, type,
                    department, aquireDate, manufacturer, model, yellowTag, procOrder, cost, assetTag, location
                    , waitList));
        }
    }

    /*Gets item object by it's respective name*/
    Item getItemByName(String itemName){
        return itemCollection.get(itemName);
    }

    /*Returns a String list of all the item names  */
    List<String> listOfItems() {
        List <String> itemList = new ArrayList<String>();
        for (String key : itemCollection.keySet()) {
            itemList.add(itemCollection.get(key).getItemName());
        }
        return itemList;
    }
}

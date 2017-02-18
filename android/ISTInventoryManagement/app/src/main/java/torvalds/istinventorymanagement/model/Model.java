package torvalds.istinventorymanagement.model;

import java.util.Observable;

/**
 * Created by Hassan Jegan Ndow on 2/18/2017.
 * A class to serve as a mediator between the ItemManager
 * and other Manager classes
 */

public class Model extends Observable {

    /*Instantiates manager classes*/
    private ItemManager itemManager = null;
    private static Model model;

    private Model(){
        itemManager = new ItemManager();
    }

    /*single instance of  Model class*/
    public static Model getModel(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    /*add a new item*/
    public void addNewItem(String name, long id, long barcode, long serialNumber, String description, String image, String
            type, String department, String aquireDate, String manufacturer, String model, String
                                        yellowTag, String procOrder, double cost, String assetTag, String location, long
                                        waitList){
        itemManager.setItem(name, id, barcode, serialNumber, description, image, type,
                department, aquireDate, manufacturer, model, yellowTag, procOrder, cost, assetTag, location
                , waitList);
    }
}

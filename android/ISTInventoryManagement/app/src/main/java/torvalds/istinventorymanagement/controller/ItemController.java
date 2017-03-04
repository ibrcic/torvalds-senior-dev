package torvalds.istinventorymanagement.controller;

import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Model;

/**
 * Created by Hassan Jegan Ndow on 2/23/2017.
 * Handles the I/O between the Model and Item View
 */

public class ItemController {
    Model modelInstance;

    public ItemController(){
        modelInstance = Model.getModel();
    }

    /*Retrieves a JSON object containing a list of all items - GET*/
    public void getAllItems (String urlRequest, String type){

    }

    /*Retrieves a JSON object containing the specified item - GET{item}*/
    public void getItem (String urlRequest, String type, String id){

    }

    /*Sends request to store a newly created item - PUT*/
    public void addItem(String urlRequest, String type, Item item){

    }

    /*Sends a request to update a specific item - POST*/
    public void updateItem (String urlRequest, String type, Item item){

    }

    /*Sends a request to delete a specific item - DELETE*/
    public void deleteItem (String urlRequest, String type, String id){

    }

    /*Parses a JSON object's data to conform to the Model's ORM structure*/
    public void parseJson(){

    }

    /*Accepts and validates user input for a new item*/
    public boolean submitItem(String name, String id, String barcode, String serialNumber,
                              String description, String image, String type, String department,
                              String acquireDate, String manufacturer, String model, String
                                      yellowTag, String procOrder, String cost, String assetTag,
                              String location, String
                                      waitList) {

        //A series of validation checks that verifies user has entered some data in

        if(name.length() == 0){
            System.out.println("Name required");
            return false;
        }

        if(id.length() == 0){
            System.out.println("ID required");
            return false;
        }

        if(barcode.length() == 0){
            System.out.println("Barcode required");
            return false;
        }

        if(serialNumber.length() == 0){
            System.out.println("Serial number required");
            return false;
        }

        if(description.length() == 0){
            System.out.println("Description required");
            return false;
        }


        if(type.length() == 0){
            System.out.println("Type required");
            return false;
        }

        if(department.length() == 0){
            System.out.println("Department required");
            return false;
        }

        if(acquireDate.length() == 0){
            System.out.println("Acquired date required");
            return false;
        }

        if(manufacturer.length() == 0){
            System.out.println("Manufacturer required");
            return false;
        }

        if(model.length() == 0){
            System.out.println("Model required");
            return false;
        }

        if(yellowTag.length() == 0){
            System.out.println("Yellow tag information required");
            return false;
        }

        if(procOrder.length() == 0){
            System.out.println("Procurement order required");
            return false;
        }

        if(cost.length() == 0){
            System.out.println("Cost required");
            return false;
        }

        if(assetTag.length() == 0){
            System.out.println("Asset tag information required");
            return false;
        }

        if(location.length() == 0){
            System.out.println("Location required");
            return false;
        }

        if(waitList.length() == 0){
            System.out.println("Waitlist required");
            return false;
        }

        //parses inputted numerical data properly

        long idParsed;
        long barcodeParsed;
        long serialNumberParsed;
        double costParsed;
        long waitListParsed;

        try{
            idParsed = Long.parseLong(id);
            barcodeParsed = Long.parseLong(barcode);
            serialNumberParsed = Long.parseLong(serialNumber);
            costParsed = Double.parseDouble(cost);
            waitListParsed = Long.parseLong(waitList);
        }
        catch(NumberFormatException nfe){
            System.out.println("ID, Barcode, Serial Number, Cost, and Waitlist" +
                    "fields must all be numerical");
            return false;
        }

        //creates new item and adds it to the item collection
        modelInstance.addNewItem(name, idParsed, barcodeParsed, serialNumberParsed, description, image, type,
                department, acquireDate, manufacturer, model, yellowTag, procOrder, costParsed, assetTag, location
                , waitListParsed);

        //invokes request to send newly created item information to web service;
        Item requestedItem = modelInstance.getItemByID(idParsed);
        String request = ""; //TBD
        String formatType = "application/json";
        addItem(request, formatType, requestedItem);

        return true;
    }
}

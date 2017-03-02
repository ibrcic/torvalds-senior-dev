package torvalds.istinventorymanagement.controller;

import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 2/23/2017.
 * Handles the I/O between the Model and Item View
 */

public class ItemController {

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
}

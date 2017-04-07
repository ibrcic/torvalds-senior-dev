//package torvalds.istinventorymanagement.model;
//
//import java.util.List;
//import java.util.Observable;
//
///**
// * Created by Hassan Jegan Ndow on 2/18/2017.
// * A class to serve as a mediator between the ItemManager class and other Manager classes
// */
//
//public class Model extends Observable {
//
//    /*Instantiates manager classes*/
//    private ItemManager itemManager = null;
//    private static Model model;
//
//    private Model(){
//        itemManager = new ItemManager();
//    }
//
//    /*single instance of  Model class - Singleton Pattern*/
//    public static Model getModel(){
//        if (model == null){
//            model = new Model();
//        }
//        return model;
//    }
//
//    /*add a new item to the Model*/
//    public void addNewItem(String name, long id, long barcode, long serialNumber, String description, String image, String
//            type, String department, String aquireDate, String manufacturer, String model, String
//                                        yellowTag, String procOrder, double cost, String assetTag, String location, long
//                                        waitList){
//        itemManager.setItem(name, id, barcode, serialNumber, description, image, type,
//                department, aquireDate, manufacturer, model, yellowTag, procOrder, cost, assetTag, location
//                , waitList);
//    }
//
//    /*Gets an item object by its respective id*/
//    public Item getItemByID(long id){
//        return itemManager.getItemByID(id);
//    }
//
//    /*Returns a long list of all the item IDs  */
//    public List<Long> listOfItemsByID() {
//        return itemManager.listOfItemsByID();
//    }
//
//    /*Returns a String list of all the item names  */
//    public List<String> listOfItemsByName() {
//        return itemManager.listOfItemsByName();
//    }
//}

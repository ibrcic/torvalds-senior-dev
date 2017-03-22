package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 2/11/2017.
 * A Test class for the functionality of ItemManager
 */



public class Tester {

    public static void main (String[] args){
        ItemManager im = new ItemManager();
        Model model;
        model = Model.getModel();

        model.addNewItem("iphone",4645654,234234234,43534543,"iphone 6s 64gb","iphone.jpg","6s","ISTE","11/12/16",
                "Apple","ios 6s","yellow","proc",799.99,"asset","front",0);

        model.addNewItem("android 1",1123213,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);

        model.addNewItem("android 2",9999999,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);


        System.out.println(model.listOfItemsByName());
        System.out.println(model.listOfItemsByID());

        System.out.println(model.getItemByID(1123213).getItemName());

        System.out.println(model.getItemByID(4645654).getItemName());
    }


}

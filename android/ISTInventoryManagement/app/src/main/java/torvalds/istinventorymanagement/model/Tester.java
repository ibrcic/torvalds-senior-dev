package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 2/11/2017.
 * A Test class for the functionality of ItemManager
 */



public class Tester {

    public static void main (String[] args){
        ItemManager im = new ItemManager();

        im.setItem("iphone",4645654,234234234,43534543,"iphone 6s 64gb","iphone.jpg","6s","ISTE","11/12/16",
                "Apple","ios 6s","yellow","proc",799.99,"asset","front",0);

        im.setItem("android",1123213,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);

        im.setItem("android",9999999,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);


        System.out.println(im.listOfItems());

        System.out.println(im.getItemByID(1123213).getItemName());

        System.out.println(im.getItemByID(4645654).getItemName());
    }


}

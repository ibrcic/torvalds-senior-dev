package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 */

public class DummyContent {

    ItemManager im = new ItemManager();
    static Model model;


    public DummyContent(){
        model = Model.getModel();
    }


    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        //temporary hard coded values
        addItem((new DummyItem("iphone","4645654",234234234,43534543,"iphone 6s 64gb","iphone.jpg","6s","ISTE","11/12/16",
                "Apple","ios 6s","yellow","proc",799.99,"asset","front",0)));

        addItem((new DummyItem("android 1","1123213",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));

        addItem((new DummyItem("android 2","9999999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));

        addItem((new DummyItem("android 6","9933999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 5","9999909",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 8","9919999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 10","9229999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 9","9567999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("iPad","9192999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 11","9456999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("iphone 2","9978999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("iphone 4","9999099",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 22","12111999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 23","4234324",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 24","1123999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 25","11787999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 26","1411999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 27","1111089",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
        addItem((new DummyItem("android 28","110999",1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0)));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }



    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {


        //@SerializedName("")
        public String name;
        //@SerializedName("")
        public String id;
        //@SerializedName("")
        public long barcode;
        //@SerializedName("")
        public long serialNumber;
        //@SerializedName("")
        public String description;
        //@SerializedName("")
        public String image;
        public String type;
        //@SerializedName("")
        public String department;
        //@SerializedName("")
        public String acquireDate;
        //@SerializedName("")
        public String manufacturer;
        //@SerializedName("")
        public String model;
        //@SerializedName("")
        public String yellowTag;
        //@SerializedName("")
        public String procOrder;
        //@SerializedName("")
        public double cost;
        //@SerializedName("")
        public String assetTag;
        //@SerializedName("")
        public String location;
        //@SerializedName("")
        public long waitList;

        public DummyItem(String name, String id, long barcode, long serialNumber, String description, String image, String
                type, String department, String acquireDate, String manufacturer, String model, String
                                 yellowTag, String procOrder, double cost, String assetTag, String location, long
                                 waitList) {
            this.name = name;
            this.id = id;
            this.barcode = barcode;
            this.serialNumber = serialNumber;
            this.description = description;
            this.image = image;
            this.type = type;
            this.department = department;
            this.acquireDate = acquireDate;
            this.manufacturer = manufacturer;
            this.model = model;
            this.yellowTag = yellowTag;
            this.procOrder = procOrder;
            this.cost = cost;
            this.assetTag = assetTag;
            this.location = location;
            this.waitList = waitList;
        }

        @Override
        public String toString() {
            return name;
        }


    }


}

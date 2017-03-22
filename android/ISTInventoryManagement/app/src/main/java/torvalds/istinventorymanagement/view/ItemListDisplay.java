package torvalds.istinventorymanagement.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.ItemManager;
import torvalds.istinventorymanagement.model.Model;

/**
 * Created by Hassan Jegan Ndow on 3/22/2017.
 * Class mean to test functionality of displaying list of items
 */

public class ItemListDisplay extends Activity {
    List<String> itemsList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_display);
        loadItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsList);
        ListView lv = (ListView) findViewById(R.id.itemsList);
        lv.setAdapter(adapter);

    }

    //test method to load item list
    void loadItems() {
        ItemManager im = new ItemManager();
        Model model;
        model = Model.getModel();

        model.addNewItem("iphone",4645654,234234234,43534543,"iphone 6s 64gb","iphone.jpg","6s","ISTE","11/12/16",
                "Apple","ios 6s","yellow","proc",799.99,"asset","front",0);

        model.addNewItem("android 1",1123213,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);

        model.addNewItem("android 2",9999999,1667767,721676757,"nexus galaxy","nexus.jpg","6s","ISTE","12/12/16",
                "Manufacturer","model","yellow","proc",699.99,"asset","back",0);

         itemsList = model.listOfItemsByName();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

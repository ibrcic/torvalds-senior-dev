package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 */

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemListLocal {

    @SerializedName("ItemList")
    @Expose
    private ArrayList<ItemLocal> itemList = new ArrayList<>();


    public ArrayList<ItemLocal> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemLocal> itemList) {
        this.itemList = itemList;
    }

}


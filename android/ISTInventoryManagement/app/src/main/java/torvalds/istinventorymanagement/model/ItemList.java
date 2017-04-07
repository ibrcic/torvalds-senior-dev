package torvalds.istinventorymanagement.model;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 */

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList {

    @SerializedName("ItemList")
    @Expose
    private ArrayList<Item> itemList = new ArrayList<>();


    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

}


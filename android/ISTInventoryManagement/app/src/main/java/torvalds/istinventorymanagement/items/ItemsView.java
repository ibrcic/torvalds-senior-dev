package torvalds.istinventorymanagement.items;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/6/17.
 */

interface ItemsView extends MvpView {
    void showItems(List<Item> items);
    void openDetailView(Item item);
    void addItemToCart(Item item);
}

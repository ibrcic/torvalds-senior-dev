package torvalds.istinventorymanagement.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/6/17.
 */

interface ItemsView extends MvpView {
    void showItems(List<Item> items);
}

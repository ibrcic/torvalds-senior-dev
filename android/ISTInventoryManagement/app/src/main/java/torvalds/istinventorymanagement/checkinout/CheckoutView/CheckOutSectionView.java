package torvalds.istinventorymanagement.checkinout.CheckoutView;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/12/17.
 */

interface CheckOutSectionView extends MvpView{
    void addBorrowedItems(List<Item> body);
    void showItemDetail(Item item);
    void showNoUserSelectedView();
    void showNoBorrowedItemsView();
    void showBorrowedItemsView();
}

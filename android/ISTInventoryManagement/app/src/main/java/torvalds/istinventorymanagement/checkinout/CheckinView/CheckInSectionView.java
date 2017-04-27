package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpView;

import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/12/17.
 */

interface CheckInSectionView extends MvpView {
    void addItem(Item item);
    void showContentContainer();
    void showEmptyContainer();
    void removeItem(int position);
    void showItemDetail(Item item);
}

package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/12/17.
 */

interface CheckInSectionView extends MvpView {
    void addItem(Item item);
    void showContentContainer();
    void showEmptyContainer();
    void removeItem(int position);
    void showItemDetail(Item item);
    void showSignDialog(List<Item> items, Student student);
    void showNoUserSelectedError();
}

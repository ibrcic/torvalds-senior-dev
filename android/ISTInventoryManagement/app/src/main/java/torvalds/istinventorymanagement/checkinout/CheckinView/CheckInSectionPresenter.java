package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import torvalds.istinventorymanagement.bus.RxBusItem;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/12/17.
 */

public class CheckInSectionPresenter extends MvpBasePresenter<CheckInSectionView> {

    @Override
    public void attachView(CheckInSectionView view) {
        super.attachView(view);
        subscribeNewItems();
    }

    private void subscribeNewItems() {
        RxBusItem.instanceOf().getNewItems().subscribe(item -> {
            addNewItem(item);
        });
    }

    private void addNewItem(Item item) {
        if(isViewAttached()) {
            getView().addItem(item);
        }
    }
}

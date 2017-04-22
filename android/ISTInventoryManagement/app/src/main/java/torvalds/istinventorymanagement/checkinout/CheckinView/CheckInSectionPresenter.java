package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import io.reactivex.functions.Consumer;
import torvalds.istinventorymanagement.RxBus;
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
        RxBus.instanceOf().getNewItems().subscribe(item -> {
            addNewItem(item);
        });
    }

    private void addNewItem(Item item) {
        if(isViewAttached()) {
            getView().addItem(item);
        }
    }
}

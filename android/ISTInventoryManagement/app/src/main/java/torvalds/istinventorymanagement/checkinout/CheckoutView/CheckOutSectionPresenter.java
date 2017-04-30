package torvalds.istinventorymanagement.checkinout.CheckoutView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusBorrower;
import torvalds.istinventorymanagement.bus.RxBusReservation;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/12/17.
 */

class CheckOutSectionPresenter extends MvpBasePresenter<CheckOutSectionView> {

    @Override
    public void attachView(CheckOutSectionView view) {
        super.attachView(view);
        RxBusBorrower.instanceOf().getSelectedUser().subscribe(student -> getBorrowedItemd(student.getUserId()));
        RxBusReservation.instanceOf().getReservationUpdates().subscribe(id -> getBorrowedItemd(id));
    }

    private void getBorrowedItemd(long id) {

        ISTInventoryClient.getApi().getBorrowedItems(id).enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(isViewAttached()) {
                    List<Item> checkedOutItems = getCheckedOutItems(response.body());
                    if(checkedOutItems.size() != 0) {
                        getView().showBorrowedItemsView();
                        getView().addBorrowedItems(checkedOutItems);
                    } else if (response.body() == null || checkedOutItems.size() == 0) {
                        getView().showNoBorrowedItemsView();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }

    private List<Item> getCheckedOutItems(List<Item> allitems) {
        List<Item> checkedOut = new ArrayList<>();

        for (Item item : allitems) {
            if(item.getId() != 0) {
                checkedOut.add(item);
            }
        }

        return checkedOut;
    }


    public void itemClicked(Item item) {
        if(isViewAttached()) {
            getView().showItemDetail(item);
        }
    }
}

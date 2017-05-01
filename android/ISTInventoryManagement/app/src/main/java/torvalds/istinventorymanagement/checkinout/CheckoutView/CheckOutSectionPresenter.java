package torvalds.istinventorymanagement.checkinout.CheckoutView;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusBorrower;
import torvalds.istinventorymanagement.bus.RxBusReservation;
import torvalds.istinventorymanagement.bus.RxBusReturn;
import torvalds.istinventorymanagement.model.Checkin;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.ReservationResponse;

/**
 * Created by ivan on 4/12/17.
 */

class CheckOutSectionPresenter extends MvpBasePresenter<CheckOutSectionView> {

    @Override
    public void attachView(CheckOutSectionView view) {
        super.attachView(view);
        RxBusBorrower.instanceOf().getSelectedUser().subscribe(student -> getBorrowedItems(student.getUserId()));
        RxBusReservation.instanceOf().getReservationUpdates().subscribe(id -> getBorrowedItems(id));
        RxBusReturn.instanceOf().getReturnUpdate().subscribe(id -> getBorrowedItems(id));
    }

    private void getBorrowedItems(long id) {

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

    public void removeItems(List<Item> items) {
        Checkin checkin = new Checkin(items.get(0).getBorrowerId());
        for (Item item : items) {
            checkin.addItemRental(new Checkin.ItemRental(item.getId(), item.getRentalId()));
        }
        System.out.println(new Gson().toJson(checkin));
        ISTInventoryClient.getApi().checkinItems(checkin).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                RxBusReturn.instanceOf().returnMade(items.get(0).getBorrowerId());
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {

            }
        });
    }


}

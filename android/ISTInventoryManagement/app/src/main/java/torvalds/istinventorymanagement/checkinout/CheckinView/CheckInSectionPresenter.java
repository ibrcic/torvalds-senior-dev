package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusBarcodeScan;
import torvalds.istinventorymanagement.bus.RxBusBorrower;
import torvalds.istinventorymanagement.bus.RxBusItem;
import torvalds.istinventorymanagement.bus.RxBusReservation;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/12/17.
 */

public class CheckInSectionPresenter extends MvpBasePresenter<CheckInSectionView> {

    private Student selectedStudent;

    @Override
    public void attachView(CheckInSectionView view) {
        super.attachView(view);
        subscribeListeners();
    }

    private void subscribeListeners() {
        RxBusItem.instanceOf().getNewItems().subscribe(this::addNewItem);
        RxBusBarcodeScan.instanceOf().getBarcodeScans(Constants.ScanType.ITEM).subscribe(this::getItemByBarcode);
        RxBusBorrower.instanceOf().getSelectedUser().subscribe(student -> this.selectedStudent = student);
        RxBusReservation.instanceOf().getReservationUpdates().subscribe(id -> clearCart());
    }

    private void clearCart() {
        if(isViewAttached()) {
            getView().emptyCart();
            getView().showEmptyContainer();
        }
    }

    private void getItemByBarcode(String barcode) {
        ISTInventoryClient.getApi().getItemByBarcode(barcode).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                addNewItem(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                //TODO: Handle error
            }
        });
    }

    private void addNewItem(Item item) {
        if(isViewAttached()) {
            getView().showContentContainer();
            getView().addItem(item);
        }
    }

    public void itemRemoveClicked(int position, int newSize) {
        if(isViewAttached()) {
            getView().removeItem(position);
            if(newSize == 0) {
                getView().showEmptyContainer();
            }
        }
    }

    public void itemClicked(Item item) {
        if(isViewAttached()) {
            getView().showItemDetail(item);
        }
    }

    public void btnCheckoutClicked(List<Item> items) {
        if(!isViewAttached()) {
            return;
        }

        if(selectedStudent == null) {
            getView().showNoUserSelectedError();
        } else {
            getView().showSignDialog(items, selectedStudent);
        }
    }
}

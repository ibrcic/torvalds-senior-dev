package torvalds.istinventorymanagement.checkinout.CheckinView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusBarcodeScan;
import torvalds.istinventorymanagement.bus.RxBusItem;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/12/17.
 */

public class CheckInSectionPresenter extends MvpBasePresenter<CheckInSectionView> {

    @Override
    public void attachView(CheckInSectionView view) {
        super.attachView(view);
        subscribeListeners();
    }

    private void subscribeListeners() {
        RxBusItem.instanceOf().getNewItems().subscribe(this::addNewItem);
        RxBusBarcodeScan.instanceOf().getBarcodeScans(Constants.ScanType.ITEM).subscribe(this::getItemByBarcode);
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
}

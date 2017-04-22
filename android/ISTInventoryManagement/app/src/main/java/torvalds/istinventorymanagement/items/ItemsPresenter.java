package torvalds.istinventorymanagement.items;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.model.Item;


/**
 * Created by ivan on 4/6/17.
 */

class ItemsPresenter extends MvpBasePresenter<ItemsView> {

    @Override
    public void attachView(ItemsView view) {
        super.attachView(view);
        loadItems();
    }

    public void loadItems() {
        ISTInventoryClient.getApi().getItemList().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                showItems(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                //TODO: Handle error
                Log.e("ItemsPresenter", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showItems(List<Item> items) {
        if(isViewAttached()) {
            getView().showItems(items);
        }
    }

    public void itemClicked(Item item) {
        if(isViewAttached()) {
            getView().openDetailView(item);
        }
    }

    public void addToCartClicked(Item item) {
        if(isViewAttached()) {
            getView().addItemToCart(item);
        }
    }
}

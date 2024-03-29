package torvalds.istinventorymanagement.items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.bus.RxBusItem;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemListFragment extends MvpFragment<ItemsView, ItemsPresenter> implements ItemsView {

    private ItemListAdapter listAdapter;
    private EditText search;

    public ItemListFragment() {

    }

    @Override
    public ItemsPresenter createPresenter() {
        return new ItemsPresenter();
    }

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        search = (EditText) view.findViewById(R.id.et_search);
        this.listAdapter = new ItemListAdapter();
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        setUpSearch();
        return view;
    }

    private void setUpSearch() {
        Observable<String> obs = RxTextView.textChanges(search)
                .filter(charSequence -> charSequence.length() > 2 || charSequence.length() == 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString());

        obs.observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchText -> {
            presenter.searchValueEntered(searchText);
        });
    }

    @Override
    public void showItems(List<Item> items) {
        this.listAdapter.updateItems(items);
    }

    @Override
    public void openDetailView(Item item) {
        Intent i = new Intent(getActivity(), ItemDetailActivity.class);
        i.putExtra(Constants.ITEM_KEY, item);
        startActivity(i);
    }

    @Override
    public void addItemToCart(Item item) {
        RxBusItem.instanceOf().addItem(item);
        Toast.makeText(getActivity(), R.string.added_to_cart, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void filterItems(String searchText) {
        listAdapter.filterItems(searchText);
    }

    @Override
    public void showAllItems() {
        listAdapter.clearFilter();
    }

    private class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

        private List<Item> items;
        private List<Item> itemsBackup;

        private ItemListAdapter() {
            items = new ArrayList<>();
            itemsBackup = new ArrayList<>();
        }

        @Override
        public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_items, parent, false);
            return new ItemListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemListAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);

            holder.itemName.setText(item.getName());
            holder.serialNum.setText("S/N: " + item.getSerialNumber());

            holder.view.setOnClickListener(v -> presenter.itemClicked(item));
            holder.btnCheckout.setOnClickListener(view -> presenter.addToCartClicked(item));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void filterItems(String searchText) {
            ArrayList<Item> filtered = new ArrayList<>();
            for (Item item : this.items) {
                if(Pattern.compile(Pattern.quote(searchText), Pattern.CASE_INSENSITIVE)
                        .matcher(item.getName() + item.getSerialNumber()).find()) {
                    filtered.add(item);
                }
            }

            this.items = filtered;
            notifyDataSetChanged();
        }

        public void clearFilter() {
            this.items = new ArrayList<>(this.itemsBackup);
            notifyDataSetChanged();;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final View view;
            private final TextView itemName;
            private final TextView serialNum;
            private final Button btnCheckout;

            private ViewHolder(View view) {
                super(view);
                this.view = view;
                this.itemName = (TextView) view.findViewById(R.id.item_name);
                this.serialNum = (TextView) view.findViewById(R.id.serial_number);
                this.btnCheckout = (Button) view.findViewById(R.id.btn_checkout);
            }
        }

        public void updateItems(List<Item> items) {
            this.items = items;
            this.itemsBackup = new ArrayList<>(this.items);
            notifyDataSetChanged();
        }
    }

}

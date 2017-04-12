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
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemListFragment extends MvpFragment<ItemsView, ItemsPresenter> implements ItemsView {

    private ItemListAdapter listAdapter;

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
        RecyclerView recyclerView = (RecyclerView) view;
        this.listAdapter = new ItemListAdapter();
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    @Override
    public void showItems(List<Item> items) {
        this.listAdapter.updateItems(items);
    }

    private class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

        private List<Item> items;

        private ItemListAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ItemListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemListAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);

            holder.itemName.setText(item.getItemName());
            holder.serialNum.setText("S/N: " + item.getItemSerialNumber());

            holder.view.setOnClickListener(v -> {
                Intent i = new Intent(getActivity(), ItemDetailActivity.class);
                i.putExtra(Constants.ITEM_KEY, item);
                startActivity(i);
            });

            holder.btnCheckout.setOnClickListener(view -> {
                //TODO: Go to first tab, start checkout process.
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
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
            notifyDataSetChanged();
        }
    }

}

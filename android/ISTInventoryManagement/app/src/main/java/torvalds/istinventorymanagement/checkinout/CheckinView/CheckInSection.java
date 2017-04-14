package torvalds.istinventorymanagement.checkinout.CheckinView;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.items.ItemDetailActivity;
import torvalds.istinventorymanagement.items.ItemListFragment;
import torvalds.istinventorymanagement.model.Item;

public class CheckInSection extends MvpRelativeLayout<CheckInSectionView, CheckInSectionPresenter>
        implements CheckInSectionView {

    @BindView(R.id.check_in_list) RecyclerView recyclerView;
    private ItemListAdapter listAdapter;

    public CheckInSection(Context context) {
        super(context);
        init();
    }

    public CheckInSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public CheckInSectionPresenter createPresenter() {
        return new CheckInSectionPresenter();
    }

    private void init() {
        View rootView = inflate(getContext(), R.layout.section_checkin, this);
        ButterKnife.bind(this, rootView);
        listAdapter = new ItemListAdapter();
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void addItem(Item item) {
        listAdapter.addItem(item);
    }

    private class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

        private List<Item> items;

        private ItemListAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_items_check_in, parent, false);
            return new ItemListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemListAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);

            // Open item details view
            holder.view.setOnClickListener(v -> {
                Intent i = new Intent(getContext(), ItemDetailActivity.class);
                i.putExtra(Constants.ITEM_KEY, item);
                getContext().startActivity(i);
            });

            // Remove the item from the list
            holder.btnRemove.setOnClickListener(v -> {
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            });

            holder.itemName.setText(item.getItemName());
            holder.serialNum.setText("S/N: " + item.getItemSerialNumber());

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void addItem(Item item) {
            items.add(item);
            notifyItemInserted(items.size() + 1);
            notifyItemRangeChanged(items.size() + 1, 1);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final View view;
            private final TextView itemName;
            private final TextView serialNum;
            private final FloatingActionButton btnRemove;

            private ViewHolder(View view) {
                super(view);
                this.view = view;
                this.itemName = (TextView) view.findViewById(R.id.item_name);
                this.serialNum = (TextView) view.findViewById(R.id.serial_number);
                this.btnRemove = (FloatingActionButton) view.findViewById(R.id.btn_remove);
            }
        }

    }
}

package torvalds.istinventorymanagement.checkinout.CheckoutView;

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
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.items.ItemDetailActivity;
import torvalds.istinventorymanagement.model.Item;

public class CheckOutSection extends MvpRelativeLayout<CheckOutSectionView, CheckOutSectionPresenter>
        implements CheckOutSectionView {

    @BindView(R.id.check_in_list) RecyclerView recyclerView;
    @BindView(R.id.no_items_container) ViewGroup emptyContainer;
    @BindView(R.id.select_user_container) ViewGroup noUserContainer;
    @BindView(R.id.content_container) ViewGroup contentContainer;

    private BorrowedItemListAdapter listAdapter;


    public CheckOutSection(Context context) {
        super(context);
        init();
    }

    public CheckOutSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public CheckOutSectionPresenter createPresenter() {
        return new CheckOutSectionPresenter();
    }


    private void init() {
        View rootView = inflate(getContext(), R.layout.section_checkout, this);
        ButterKnife.bind(this, rootView);
        listAdapter = new BorrowedItemListAdapter();
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showItemDetail(Item item) {
        Intent i = new Intent(getContext(), ItemDetailActivity.class);
        i.putExtra(Constants.ITEM_KEY, item);
        getContext().startActivity(i);
    }

    @Override
    public void showNoUserSelectedView() {
        emptyContainer.setVisibility(GONE);
        contentContainer.setVisibility(GONE);
        noUserContainer.setVisibility(VISIBLE);
    }

    @Override
    public void showNoBorrowedItemsView() {
        contentContainer.setVisibility(GONE);
        noUserContainer.setVisibility(GONE);
        emptyContainer.setVisibility(VISIBLE);
    }

    @Override
    public void showBorrowedItemsView() {
        contentContainer.setVisibility(VISIBLE);
        emptyContainer.setVisibility(GONE);
        noUserContainer.setVisibility(GONE);
    }

    @Override
    public void addBorrowedItems(List<Item> items) {
        listAdapter.addItems(items);
    }

    private class BorrowedItemListAdapter extends RecyclerView.Adapter<BorrowedItemListAdapter.ViewHolder> {

        private List<Item> items;

        private BorrowedItemListAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public BorrowedItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_items_borrowed, parent, false);
            return new BorrowedItemListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BorrowedItemListAdapter.ViewHolder holder, int position) {
            Item item = items.get(position);
            holder.view.setOnClickListener(v -> presenter.itemClicked(item));
            holder.itemName.setText(item.getName() + " " + item.getManufacturer() + " " + item.getModel());
            holder.serialNum.setText("S/N: " + item.getSerialNumber());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void addItems(List<Item> newItems) {
            items.clear();
            items.addAll(newItems);
            notifyItemRangeInserted(0, newItems.size());
            notifyDataSetChanged();
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

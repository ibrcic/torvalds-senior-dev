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
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.SimpleScannerActivity;
import torvalds.istinventorymanagement.items.ItemDetailActivity;
import torvalds.istinventorymanagement.model.Item;

import static torvalds.istinventorymanagement.Constants.SCAN_TYPE_KEY;

public class CheckInSection extends MvpRelativeLayout<CheckInSectionView, CheckInSectionPresenter>
        implements CheckInSectionView {

    @BindView(R.id.check_in_list) RecyclerView recyclerView;
    @BindView(R.id.select_user_container) ViewGroup emptyContainer;
    @BindView(R.id.content_container) ViewGroup contentContainer;
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

    @OnClick({R.id.btn_scan, R.id.btn_scan_empty})
    public void btnScanClicked() {
        Intent i = new Intent(getContext(), SimpleScannerActivity.class);
        i.putExtra(SCAN_TYPE_KEY, Constants.ScanType.ITEM);
        getContext().startActivity(i);
    }

    @Override
    public void addItem(Item item) {
        listAdapter.addItem(item);
    }

    @Override
    public void showContentContainer() {
        emptyContainer.setVisibility(GONE);
        contentContainer.setVisibility(VISIBLE);
    }

    @Override
    public void showEmptyContainer() {
        contentContainer.setVisibility(GONE);
        emptyContainer.setVisibility(VISIBLE);
    }

    @Override
    public void removeItem(int position) {
        listAdapter.removeItem(position);
    }

    @Override
    public void showItemDetail(Item item) {
        Intent i = new Intent(getContext(), ItemDetailActivity.class);
        i.putExtra(Constants.ITEM_KEY, item);
        getContext().startActivity(i);
    }


    private class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

        private List<Item> items;

        private ItemListAdapter() {
            items = new ArrayList<>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_items_check_in, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = items.get(position);
            holder.view.setOnClickListener(v -> presenter.itemClicked(item));
            holder.btnRemove.setOnClickListener(v -> presenter.itemRemoveClicked(position, items.size() - 1));
            holder.itemName.setText(item.getName() + " " + item.getManufacturer() + " " + item.getModel());
            holder.serialNum.setText("S/N: " + item.getSerialNumber());
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

        public void removeItem(int position) {
            items.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
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

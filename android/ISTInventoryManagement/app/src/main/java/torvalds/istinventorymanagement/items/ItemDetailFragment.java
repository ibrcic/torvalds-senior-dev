package torvalds.istinventorymanagement.items;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.bus.RxBusItem;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemDetailFragment extends Fragment {

    @BindView(R.id.item_image) ImageView imgItem;
    @BindView(R.id.item_detail_name) TextView itemName;
    @BindView(R.id.item_detail_barcode) TextView itemBarcode;
    @BindView(R.id.item_detail_serialNumber) TextView itemSerialNumber;
    @BindView(R.id.item_detail_description) TextView itemDescription;
    @BindView(R.id.item_detail_location) TextView itemLocation;
    @BindView(R.id.item_detail_department) TextView itemDepartment;

    private Item item;

    public ItemDetailFragment() {

    }

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.ITEM_KEY, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.ITEM_KEY)) {
            item = (Item) getArguments().getSerializable(Constants.ITEM_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (item != null) {
            Glide.with(this).load(this.item.getItemImage()).into(imgItem);
            this.itemName.setText(this.item.getItemName());
            this.itemBarcode.setText(this.item.getItemBarcode());
            this.itemSerialNumber.setText(String.valueOf(this.item.getItemSerialNumber()));
            this.itemDescription.setText(this.item.getItemDescription());
            this.itemLocation.setText(this.item.getItemLocation());
            this.itemDepartment.setText(this.item.getItemDepartment());
        }

        return rootView;
    }


    @OnClick(R.id.btn_checkout)
    public void checkoutClicked() {
        RxBusItem.instanceOf().addItem(item);
        Toast.makeText(getActivity(), R.string.added_to_cart, Toast.LENGTH_SHORT).show();
    }


}

package torvalds.istinventorymanagement.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.DummyContent;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment arguments
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
                    ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail,
                container, false);

        // Shows each dummy item attribute as text in a TextView.
        if (mItem != null) {

            //((TextView) rootView.findViewById(R.id.item_detail_image))
                    //.setText(mItem.image);

            ((ImageView) rootView.findViewById(R.id.item_view))
                    .setImageDrawable(getResources().getDrawable(R.drawable.img_nexus6_template));

            ((TextView) rootView.findViewById(R.id.item_detail_name))
                    .setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.item_detail_barcode))
                    .setText("Barcode: " + Long.toString(mItem.barcode));
            ((TextView) rootView.findViewById(R.id.item_detail_serialNumber))
                    .setText("S/N: " + Long.toString(mItem.serialNumber));

            ((TextView) rootView.findViewById(R.id.item_detail_location))
                    .setText("Location: " + mItem.location);

            /*
            ((TextView) rootView.findViewById(R.id.item_detail_id))
                    .setText("id: " + mItem.id);
            ((TextView) rootView.findViewById(R.id.item_detail_description))
                    .setText("description: " + mItem.description);
            ((TextView) rootView.findViewById(R.id.item_detail_type))
                    .setText("type: " + mItem.type);
            ((TextView) rootView.findViewById(R.id.item_detail_department))
                    .setText("department: " + mItem.department);
            ((TextView) rootView.findViewById(R.id.item_detail_acquireDate))
                    .setText("acquired date: " + mItem.acquireDate);
            ((TextView) rootView.findViewById(R.id.item_detail_manufacturer))
                    .setText("manufacturer: " + mItem.manufacturer);
            ((TextView) rootView.findViewById(R.id.item_detail_model))
                    .setText("model: " + mItem.model);
            ((TextView) rootView.findViewById(R.id.item_detail_yellowTag))
                    .setText("yellow tag: " + mItem.yellowTag);
            ((TextView) rootView.findViewById(R.id.item_detail_procOrder))
                    .setText("procurement order: " + mItem.procOrder);
            ((TextView) rootView.findViewById(R.id.item_detail_assetTag))
                    .setText("asset tag: " + mItem.assetTag);

            ((TextView) rootView.findViewById(R.id.item_detail_waitList))
                    .setText("waitlist: " + mItem.waitList);

             */

        }

        return rootView;
    }
}

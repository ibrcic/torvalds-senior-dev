package torvalds.istinventorymanagement.checkinout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import torvalds.istinventorymanagement.R;

/**
 * Created by: Ivan Brcic 12/04/17
 * This fragment is first tab on screen when lab assistanc logs in.
 * This fragments basically just holds three different subviews: Selected User view,
 * Checkout itemsview and Check in items view of the items that currrently selected user has.
 */
public class CheckInOutFragment extends Fragment {


    public CheckInOutFragment() {
        // Required empty public constructor
    }

    public static CheckInOutFragment newInstance() {
        CheckInOutFragment fragment = new CheckInOutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

}

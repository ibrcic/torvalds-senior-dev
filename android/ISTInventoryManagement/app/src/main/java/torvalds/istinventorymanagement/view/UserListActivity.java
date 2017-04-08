package torvalds.istinventorymanagement.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import torvalds.istinventorymanagement.MainLoggedInActivity;
import torvalds.istinventorymanagement.R;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 */

public class UserListActivity extends Fragment {
    private boolean twoPanel;

    public static MainLoggedInActivity.PlaceholderFragment newInstance(int sectionNumber) {
        MainLoggedInActivity.PlaceholderFragment fragment = new MainLoggedInActivity.PlaceholderFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_user_list,
                container, false);

        //alerts UI interface to be designed for devices with a screen width greater than 820dp
//        if (findViewById(R.id.item_detail_container) != null) {
//            twoPanel = true;
//            ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list)).setActivateOnItemClick(true);
//        }

        return rootView;
    }
}

package torvalds.istinventorymanagement.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import torvalds.istinventorymanagement.MainLoggedInActivity;
import torvalds.istinventorymanagement.R;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemListActivity extends Fragment  {

    private boolean twoPanel;

    public static MainLoggedInActivity.PlaceholderFragment newInstance(int sectionNumber) {
        MainLoggedInActivity.PlaceholderFragment fragment = new MainLoggedInActivity.PlaceholderFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_item_list,
                container, false);

        //alerts UI interface to be designed for devices with a screen width greater than 820dp
//        if (findViewById(R.id.item_detail_container) != null) {
//            twoPanel = true;
//            ((ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.item_list)).setActivateOnItemClick(true);
//        }

        return rootView;
    }
//
//    @Override
//    public void onItemSelected(String id) {
//        if (twoPanel) {
//            Bundle arguments = new Bundle();
//            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
//            ItemDetailFragment fragment = new ItemDetailFragment();
//            fragment.setArguments(arguments);
////            getSupportFragmentManager().beginTransaction()
////                    .replace(R.id.item_detail_container, fragment).commit();
//
//        } else {
//            Intent detailIntent = new Intent(getActivity(), ItemDetailActivity.class);
//            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
//            startActivity(detailIntent);
//        }
//    }

    // Move to container activity
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_items, menu);
//
//        MenuItem searchMenuItem = menu.findItem(R.id.item_search);
//        MenuItemCompat.expandActionView(searchMenuItem);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.item_search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconified(false);
//        searchView.clearFocus();
//        return true;
//    }


}

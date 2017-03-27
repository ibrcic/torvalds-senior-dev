package torvalds.istinventorymanagement.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import torvalds.istinventorymanagement.R;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemListActivity extends FragmentActivity implements ItemListFragment.Callbacks {

    private boolean twoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //alerts UI interface to be designed for devices with a screen width greater than 820dp
        if (findViewById(R.id.item_detail_container) != null) {

            twoPanel = true;

            ((ItemListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.item_list)).setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (twoPanel) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment).commit();

        } else {
            Intent detailIntent = new Intent(this, ItemDetailActivity.class);
            detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

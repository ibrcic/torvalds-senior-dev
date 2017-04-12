package torvalds.istinventorymanagement.items;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class ItemDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        if (savedInstanceState == null) {
            ItemDetailFragment fragment = ItemDetailFragment
                    .newInstance((Item) getIntent().getSerializableExtra(Constants.ITEM_KEY));
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, fragment).commit();
        }
    }

}

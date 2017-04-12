package torvalds.istinventorymanagement.items;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by Hassan Jegan Ndow on 3/27/2017.
 * Might me deteleted in the future, eaving it here for now. by: Ivan Brcic, 12/04/17
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

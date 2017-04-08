package torvalds.istinventorymanagement.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 * Tutorial reference: https://code.tutsplus.com/tutorials/android-sdk-using-fragments--mobile-13886
 */

public class UserDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        if (savedInstanceState == null) {
            UserDetailFragment fragment = UserDetailFragment
                    .newInstance((Student) getIntent().getSerializableExtra(Constants.USER_KEY));
            getSupportFragmentManager().beginTransaction().add(R.id.user_detail_container, fragment).commit();
        }
    }

}

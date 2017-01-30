package torvalds.istinventorymanagement.login;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import torvalds.istinventorymanagement.R;

public class LoginActivity extends MvpActivity<LoginView, LoginActivityPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @NonNull
    @Override
    public LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter();
    }
}

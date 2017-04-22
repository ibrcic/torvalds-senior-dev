package torvalds.istinventorymanagement.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import torvalds.istinventorymanagement.MainLoggedInActivity;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.SimpleScanningActivity;

public class LoginActivity extends MvpActivity<LoginView, LoginActivityPresenter>
        implements LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public LoginActivityPresenter createPresenter() {
        return new LoginActivityPresenter();
    }

    @OnClick(R.id.btn_login_using_credentials)
    public void loginUsingCredentialsClicked() {
        System.out.println("Login using credentials clicked");
        presenter.loginUsingCredentialsClicked();
    }

    @OnClick(R.id.btn_scan_id)
    public void scanIdClicked() {
        presenter.scanIdClicked();
    }

    @Override
    public void showLoading() {
        //TODO: Show laoding when getting API data, logging in
    }

    @Override
    public void showCredentialsLoginView() {
        DialogFragment newFragment = CredentialsLoginDialog.newInstance();
        newFragment.show(getSupportFragmentManager(), "credentials_dialog");
    }

    @Override
    public void showScanIdLoginView() {
//        DialogFragment newFragment = BarcodeLoginDialog.newInstance();
//        newFragment.show(getSupportFragmentManager(), "credentials_dialog");
        Intent i = new Intent(this, SimpleScanningActivity.class);
        startActivity(i);

    }


    @Override
    public void loginCredentialsEntered(String username, String password) {
        presenter.loginWithCredentials(username, password);
    }

    @Override
    public void showMainLoggedInView() {
        Intent i = new Intent(this, MainLoggedInActivity.class);
        startActivity(i);
    }
}

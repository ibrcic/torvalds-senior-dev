package torvalds.istinventorymanagement.login;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import torvalds.istinventorymanagement.R;

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
    public void showCredentialsLoginView() {
        System.out.println("Showing dialog");
        DialogFragment newFragment = CredentialsLoginDialog.newInstance();
        newFragment.show(getSupportFragmentManager(), "credentials_dialog");
    }
}

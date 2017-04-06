package torvalds.istinventorymanagement.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by ivan on 1/30/17.
 */

public interface LoginView extends MvpView{
    void showLoading();
    void showCredentialsLoginView();
    void showScanIdLoginView();
    void loginCredentialsEntered(String username, String password);
    void showMainLoggedInView();
}

package torvalds.istinventorymanagement.login;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by ivan on 1/30/17.
 */

public class LoginActivityPresenter extends MvpBasePresenter<LoginView> {

    public void loginUsingCredentialsClicked() {
        if(isViewAttached()) {
            getView().showCredentialsLoginView();
        }
    }

    public void scanIdClicked() {

    }
}

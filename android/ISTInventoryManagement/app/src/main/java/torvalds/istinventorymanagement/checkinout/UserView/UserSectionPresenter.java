package torvalds.istinventorymanagement.checkinout.UserView;

import android.text.Editable;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusBarcodeScan;
import torvalds.istinventorymanagement.bus.RxBusBorrower;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/12/17.
 */

class UserSectionPresenter extends MvpBasePresenter<UserSectionView> {

    @Override
    public void attachView(UserSectionView view) {
        super.attachView(view);
        observeUserSelect();
        observeIdScanned();
    }

    private void observeIdScanned() {
        RxBusBarcodeScan.instanceOf().getBarcodeScans(Constants.ScanType.STUDENT)
                .subscribe(uid -> loadUser(Long.parseLong(uid.substring(0, uid.length() - 1))));
    }

    private void observeUserSelect() {
        RxBusBorrower.instanceOf().getSelectedUser().subscribe(student -> {
            System.out.println("User selected: " + student.getUsername());
            if(isViewAttached() && student != null) {
                getView().setStudent(student);
            }
        });
    }

    public void btnGoClicked(String text) {
        long uid = Integer.parseInt(text);
        loadUser(uid);
    }

    private void loadUser(long uid) {
        ISTInventoryClient.getApi().getStudentByUid(uid).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.body() != null) {
                    RxBusBorrower.instanceOf().selectStudent(response.body());
                } else {
                    showNoUserFound();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                showNoUserFound();
            }
        });
    }

    private void showNoUserFound() {
        if(isViewAttached()) {
            getView().showNoUserError();
        }
    }

    public void btnScanIdClicked() {
        if(isViewAttached()) {
            getView().showScanBarcodeView();
        }
    }

    public void btnSelectDifferentUserClicked() {
        if(isViewAttached()) {
            getView().showSelectUserView();
        }
    }
}

package torvalds.istinventorymanagement.checkinout.UserView;

import com.hannesdorfmann.mosby.mvp.MvpView;

import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/12/17.
 */

interface UserSectionView extends MvpView{
    void setStudent(Student student);
    void showNoUserError();
    void showScanBarcodeView();
}

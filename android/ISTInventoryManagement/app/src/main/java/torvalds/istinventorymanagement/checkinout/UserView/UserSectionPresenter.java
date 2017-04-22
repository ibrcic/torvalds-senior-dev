package torvalds.istinventorymanagement.checkinout.UserView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import torvalds.istinventorymanagement.bus.RxBusBorrower;

/**
 * Created by ivan on 4/12/17.
 */

class UserSectionPresenter extends MvpBasePresenter<UserSectionView> {

    @Override
    public void attachView(UserSectionView view) {
        super.attachView(view);
        observeUserSelect();
    }

    private void observeUserSelect() {
        RxBusBorrower.instanceOf().getSelectedUser().subscribe(student -> {
            System.out.println("User selected: " + student.getUsername());
            if(isViewAttached() && student != null) {
                getView().setStudent(student);
            }
        });
    }

}

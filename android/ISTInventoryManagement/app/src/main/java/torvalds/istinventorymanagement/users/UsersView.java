package torvalds.istinventorymanagement.users;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import torvalds.istinventorymanagement.model.Student;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 */

public interface UsersView extends MvpView{
    void showUsers(List<Student> students);
    void showBorrowerSelectedConfirm();
    void goToCheckInOutTab();
}

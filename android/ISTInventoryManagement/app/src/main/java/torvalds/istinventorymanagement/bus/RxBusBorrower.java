package torvalds.istinventorymanagement.bus;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/14/17.
 */

public class RxBusBorrower {

    private static RxBusBorrower instance;

    private BehaviorRelay<Student> relay = BehaviorRelay.create();

    public static RxBusBorrower instanceOf() {

        if (instance == null) {
            instance = new RxBusBorrower();
        }

        return instance;
    }

    public void selectStudent(Student borrower) {
        relay.accept(borrower);
    }

    public Observable<Student> getSelectedUser() {
        return relay;
    }

}

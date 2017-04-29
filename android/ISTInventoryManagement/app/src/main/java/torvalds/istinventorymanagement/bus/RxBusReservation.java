package torvalds.istinventorymanagement.bus;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 4/14/17.
 */

public class RxBusReservation {

    private static RxBusReservation instance;

    private BehaviorRelay<Long> relay = BehaviorRelay.create();

    public static RxBusReservation instanceOf() {

        if (instance == null) {
            instance = new RxBusReservation();
        }

        return instance;
    }

    public void reservationMade(long borrowerId) {
        relay.accept(borrowerId);
    }

    public Observable<Long> getReservationUpdates() {
        return relay;
    }

}

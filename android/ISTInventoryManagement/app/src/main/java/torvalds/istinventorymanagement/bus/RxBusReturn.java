package torvalds.istinventorymanagement.bus;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

/**
 * Created by ivan on 4/14/17.
 */

public class RxBusReturn {

    private static RxBusReturn instance;

    private BehaviorRelay<Long> relay = BehaviorRelay.create();

    public static RxBusReturn instanceOf() {

        if (instance == null) {
            instance = new RxBusReturn();
        }

        return instance;
    }

    public void returnMade(long borrowerId) {
        relay.accept(borrowerId);
    }

    public Observable<Long> getReturnUpdate() {
        return relay;
    }

}

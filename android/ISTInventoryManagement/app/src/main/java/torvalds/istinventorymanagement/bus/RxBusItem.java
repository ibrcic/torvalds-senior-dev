package torvalds.istinventorymanagement.bus;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;
import torvalds.istinventorymanagement.model.Item;

/**
 * Created by ivan on 4/14/17.
 */

public class RxBusItem {

    private static RxBusItem instance;

    private PublishRelay<Item> relay = PublishRelay.create();

    public static RxBusItem instanceOf() {

        if (instance == null) {
            instance = new RxBusItem();
        }

        return instance;
    }

    public void addItem(Item item) {
        relay.accept(item);
    }

    public Observable<Item> getNewItems() {
        return relay;
    }

}

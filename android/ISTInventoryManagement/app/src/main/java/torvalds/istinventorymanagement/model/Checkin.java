package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 4/30/17.
 */

public class Checkin {

    private long userId;
    private List<ItemRental> idItemReturnList;

    public Checkin(long userId) {
        this.userId = userId;
        this.idItemReturnList = new ArrayList<>();
    }

    public void addItemRental(ItemRental itemRental) {
        idItemReturnList.add(itemRental);
    }

    public static class ItemRental {

        private long itemId;
        private long rentalId;

        public ItemRental(long itemId, long rentalId) {
            this.itemId = itemId;
            this.rentalId = rentalId;
        }
    }
}

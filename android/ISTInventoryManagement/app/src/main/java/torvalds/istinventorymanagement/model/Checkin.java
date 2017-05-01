package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 4/30/17.
 */

public class Checkin {

    private long borrowerId;
    private List<ItemRental> idItemReturnList;

    public Checkin(long borrowerId) {
        this.borrowerId = borrowerId;
        this.idItemReturnList = new ArrayList<>();
    }

    public void addItemRental(ItemRental itemRental) {
        idItemReturnList.add(itemRental);
    }

    public static class ItemRental {

        private long itemID;
        private long rentalId;

        public ItemRental(long itemId, long rentalId) {
            this.itemID = itemId;
            this.rentalId = rentalId;
        }
    }
}

package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 4/30/17.
 */

public class Checkout {

    private List<Long> idItemList;
    private long userId;
    private long rentalId;

    public Checkout(long userId, long rentalId) {
        this.userId = userId;
        this.rentalId = rentalId;
        idItemList = new ArrayList<>();
    }

    public List<Long> getIdItemList() {
        return idItemList;
    }

    public void setIdItemList(List<Long> idItemList) {
        this.idItemList = idItemList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRentalId() {
        return rentalId;
    }

    public void setRentalId(long rentalId) {
        this.rentalId = rentalId;
    }

    public void addItem(long itemId) {
        idItemList.add(itemId);
    }
}

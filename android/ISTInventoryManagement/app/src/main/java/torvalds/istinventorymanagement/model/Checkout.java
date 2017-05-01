package torvalds.istinventorymanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 4/30/17.
 */

public class Checkout {

    private List<Long> idItemList;
    private long borrowerId;
    private long rentalId;

    public Checkout(long borrowerId, long rentalId) {
        this.borrowerId = borrowerId;
        this.rentalId = rentalId;
        idItemList = new ArrayList<>();
    }

    public List<Long> getIdItemList() {
        return idItemList;
    }

    public void setIdItemList(List<Long> idItemList) {
        this.idItemList = idItemList;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
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

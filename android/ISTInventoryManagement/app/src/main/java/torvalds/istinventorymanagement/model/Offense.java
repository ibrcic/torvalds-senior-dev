package torvalds.istinventorymanagement.model;

/**
 * Created by ivan on 5/2/17.
 */

public class Offense {

    private long offenseId;
    private long userId;
    private long rentalId;
    private long itemId;
    private String offenseName;
    private String offenseDescription;
    private String offenseDate;

    public long getOffenseId() {
        return offenseId;
    }

    public void setOffenseId(long offenseId) {
        this.offenseId = offenseId;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getOffenseName() {
        return offenseName;
    }

    public void setOffenseName(String offenseName) {
        this.offenseName = offenseName;
    }

    public String getOffenseDescription() {
        return offenseDescription;
    }

    public void setOffenseDescription(String offenseDescription) {
        this.offenseDescription = offenseDescription;
    }

    public String getOffenseDate() {
        return offenseDate;
    }

    public void setOffenseDate(String offenseDate) {
        this.offenseDate = offenseDate;
    }
}

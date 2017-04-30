package torvalds.istinventorymanagement.model;

/**
 * Created by ivan on 4/29/17.
 */

public class ReservationResponse {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private int rentalId;

    public int getRentalId() {
        return rentalId;
    }
}

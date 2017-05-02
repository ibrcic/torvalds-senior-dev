package torvalds.istinventorymanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Hassan Jegan Ndow on 4/7/2017.
 */

public class Reservation {

    @SerializedName("reservationId")
    @Expose
    long reservationId;
    @SerializedName("userId")
    @Expose
    long userId;
    @SerializedName("rentalId")
    @Expose
    long rentalId;
    @SerializedName("itemTypeId")
    @Expose
    long itemTypeId;

    long itemId;

    @SerializedName("signature")
    @Expose
    String signature;
    @SerializedName("startDate")
    @Expose
    Date startDate;
    @SerializedName("endDate")
    @Expose
    Date endDate;


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getIdItem() {
        return itemId;
    }

    public void setIdItem(long idItem) {
        this.itemId = idItem;
    }
}

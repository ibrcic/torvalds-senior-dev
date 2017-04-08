package torvalds.istinventorymanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hassan Jegan Ndow on 4/7/2017.
 */

public class Borrower {
    @SerializedName("idItem")
    @Expose
    long borrowerId;
    @SerializedName("userName")
    @Expose
    String username;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("flagged")
    @Expose
    int flagged;

    /*Returns the username of a borrower*/
    public String getUsername() {
        return username;
    }

    /*Returns the id of a borrower*/
    public long getUserId() {
        return borrowerId;
    }

    /*Returns the email of a borrower*/
    public String getUserEmail() {
        return email;
    }

    /*Returns the flagged condition of a borrower*/
    public int getFlagged() {
        return flagged;
    }

}

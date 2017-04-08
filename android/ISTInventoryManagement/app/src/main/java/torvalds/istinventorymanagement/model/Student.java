package torvalds.istinventorymanagement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hassan Jegan Ndow on 4/7/2017.
 */

public class Student implements Serializable {

    @SerializedName("majorId")
    @Expose
    private long majorId;
    @SerializedName("majorTitle")
    @Expose
    private String majorTitle;
    @SerializedName("majorAbbr")
    @Expose
    private String majorAbbr;
    @SerializedName("classId")
    @Expose
    private long classId;
    @SerializedName("classTitle")
    @Expose
    private String classTitle;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("section")
    @Expose
    private int section;

    @SerializedName("borrowerId")
    @Expose
    private long borrowerId;
    @SerializedName("userName")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("flagged")
    @Expose
    private int flagged;

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

    /*Returns the major id of a Student*/
    public Long getMajorId() {
        return majorId;
    }

    /*Returns the major id of a Student*/
    public String getMajorTitle() {
        return majorTitle;
    }

    /*Returns the major abbreviation of a Student*/
    public String getMajorAbbr() {
        return majorAbbr;
    }

    /*Returns the class id of a Student*/
    public Long getClassId() {
        return classId;
    }

    /*Returns the class title of a Student*/
    public String getClassTitle() {
        return classTitle;
    }

    /*Returns the class name of a Student*/
    public String getClassName() {
        return className;
    }

    /*Returns the class section  of a Student*/
    public int getSection() {
        return section;
    }

}

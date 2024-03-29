package torvalds.istinventorymanagement;

/**
 * Created by ivan on 3/11/17.
 */

public class Constants {

    // Web Service base url
    public static final String BASE_URL = "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/";

    // Mock base URL, Ivan works with this.
    public static final String MOCK_URL = "http://demo6190713.mockable.io/";

    // url of Jegan's local web service - testing purposes
    public static final String JEGAN_URL = "http://10.0.2.2:8080/ISTInventoryWebService/services/";

    public static final String ITEM_KEY = "ITEM_KEY";

    public static final String USER_KEY = "USER_KEY";

    public static final String SCAN_TYPE_KEY = "SCAN_TYPE_KEY";

    public static final String SIGN_ITEMS_KEY = "SIGN_ITEMS_KEY";

    public static final String SIGN_STUDENT_KEY = "SIGN_STUDENT_KEY";

    public enum ScanType {
        STUDENT, ITEM
    }

}

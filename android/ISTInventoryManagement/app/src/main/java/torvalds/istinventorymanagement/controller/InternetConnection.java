package torvalds.istinventorymanagement.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

/**
 * @author Pratik Butani
 * referenced from tutorial: http://www.pratikbutani.com/2016/05/android-tutorial-json-parsing-using-retrofit-part-1/
 */
public class InternetConnection {

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
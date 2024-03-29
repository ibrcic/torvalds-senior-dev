package torvalds.istinventorymanagement.api;

import java.util.List;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.model.Checkin;
import torvalds.istinventorymanagement.model.Checkout;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Offense;
import torvalds.istinventorymanagement.model.Reservation;
import torvalds.istinventorymanagement.model.StatusResponse;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by ivan on 3/11/17.
 */

public class ISTInventoryClient {

    // Interface used for sending HTTP requests. Basically a singleton.
    private static InventoryApi inventoryApiInterface;

    /**
     * Generating interface with authentication if one does
     * not exists already. If it exists, it just returns that
     * one.
     */
    public static InventoryApi getApi(){

        if (inventoryApiInterface == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            inventoryApiInterface = retrofit.create(InventoryApi.class);
        }

        return inventoryApiInterface;
    }

    public interface InventoryApi {

        @GET("items/data.json")
        Call<List<Item>> getItemList();

        @GET("users/data.json")
        Call<List<Student>> getStudentList();

        @GET("items/barcode/{barcode}/data.json")
        Call<Item> getItemByBarcode(@Path("barcode") String barcode);

        @GET("reservations/rentalBorrower/{borrowerId}/data.json")
        Call<List<Item>> getBorrowedItems(@Path("borrowerId") long borrowerId);

        @GET("users/{uid}/data.json")
        Call<Student> getStudentByUid(@Path("uid") long uid);

        @POST("reservations/rental/add")
        Observable<StatusResponse> addRental(@Body Reservation reservation);

        @POST("reservations/checkout/")
        Observable<StatusResponse> checkoutItems(@Body Checkout checkout);

        @HTTP(method = "DELETE", path = "reservations/checkin", hasBody = true)
        Call<StatusResponse> checkinItems(@Body Checkin checkin);

        @POST("users/offense/add")
        Call<StatusResponse> addOffense(@Body Offense offense);

    }

}

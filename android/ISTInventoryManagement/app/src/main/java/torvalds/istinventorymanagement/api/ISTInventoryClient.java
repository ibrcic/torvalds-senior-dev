package torvalds.istinventorymanagement.api;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.model.Item;
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

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.JEGAN_URL)
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

        @GET("services/items/{serialNumber}")
        Call<Item> getItemBySerial(@Path("serialNumber") int serialNumber);

        @GET("services/items/{itemId}")
        Call<Item> getItemById(@Path("itemId") int itemId);

        @PUT("services/items/add")
        Call<Boolean> addItem(@Body Item itemToAdd);

        @POST("services/items/{itemId}")
        Call<Boolean> updateItem(@Path("itemId") int itemId, @Body Item item);

        @POST("services/items/{itemId}/break")
        Call<Boolean> breakItem(@Path("itemId") int itemId, @Query("dmgDesc") String problemDescription,
                                @Query("dmgName") String dnmgName, @Query("dmgSeverity") int severityLevel);


        @POST("services/items/{itemId}/retire")
        Call<Boolean> retireItem(@Path("itemId") int itemId);

    }

}

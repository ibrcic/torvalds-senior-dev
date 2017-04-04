package torvalds.istinventorymanagement.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import torvalds.istinventorymanagement.controller.ApiService;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 */

public class RetrofitClientLocal {

    private static final String LOCAL_URL = "http://10.0.2.2:8080/LocalISTInventoryWebService/services2/items/";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(LOCAL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}

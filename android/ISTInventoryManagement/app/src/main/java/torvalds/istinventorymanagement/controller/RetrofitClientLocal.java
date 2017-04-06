package torvalds.istinventorymanagement.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import torvalds.istinventorymanagement.controller.ApiService;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 * referenced from tutorial: http://www.pratikbutani.com/2016/05/android-tutorial-json-parsing-using-retrofit-part-1/
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

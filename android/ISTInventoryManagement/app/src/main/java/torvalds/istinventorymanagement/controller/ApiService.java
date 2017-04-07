package torvalds.istinventorymanagement.controller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import torvalds.istinventorymanagement.model.Item;


/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 * referenced from tutorial: http://www.pratikbutani.com/2016/05/android-tutorial-json-parsing-using-retrofit-part-1/
 */
public interface ApiService {

    @GET("data.json")
    Call<List<Item>> getItemList();
}

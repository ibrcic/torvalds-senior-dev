package torvalds.istinventorymanagement.controller;

import retrofit2.Call;
import retrofit2.http.GET;
import torvalds.istinventorymanagement.model.ItemListLocal;

/**
 * Created by Hassan Jegan Ndow on 4/3/2017.
 */
public interface ApiService {

    @GET("data.json")
    Call<ItemListLocal> getItemList();
}

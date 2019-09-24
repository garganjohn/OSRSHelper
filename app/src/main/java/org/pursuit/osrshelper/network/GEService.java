package org.pursuit.osrshelper.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GEService {
    //String PATH = "api/catalogue/detail.json?item={itemID}";

    @GET("api/catalogue/detail.json")
    Call<GEModel> getWhip(@Query("item") String... itemID);


}

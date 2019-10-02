package org.pursuit.osrshelper.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GEService {
    String PATH = "api/catalogue/detail.json";

    @GET(PATH)
    Call<GEModel> getWhip(@Query("item") String... itemID);


}

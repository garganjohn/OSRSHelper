package org.pursuit.osrshelper.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GEService {
    String PATH = "api/catalogue/detail.json";
    String SEARCH = "api/catalogue/items.json?category=1";

    @GET(PATH)
    Call<GEModel> getItem(@Query("item") String... itemID);

    @GET(SEARCH)
    Call<List<GEModel>> getSearch(@Query("alpha") String alpha, @Query("page") int pageNum);

}

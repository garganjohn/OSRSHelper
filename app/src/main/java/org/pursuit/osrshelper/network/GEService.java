package org.pursuit.osrshelper.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GEService {
    String PATH = "api/catalogue/detail.json?item=4151";

    @GET(PATH)
    Call<GEModel> getWhip();


}

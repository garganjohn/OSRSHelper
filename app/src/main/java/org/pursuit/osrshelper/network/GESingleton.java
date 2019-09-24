package org.pursuit.osrshelper.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GESingleton {
    private static Retrofit INSTANCE;
    private static final String BASE_URL = "http://services.runescape.com/m=itemdb_oldschool/";

    public static Retrofit getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}

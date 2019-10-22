package org.pursuit.osrshelper.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkHelper {
    public static final String TAG = "NetworkHelper.class";
    private Context context;
    private GEAdapter geAdapter;

    public NetworkHelper(Context context, GEAdapter geAdapter) {
        this.context = context;
        this.geAdapter = geAdapter;
    }

    public void makeQueryCall(List<Integer> queryResult) {
        List<GEModels.GEItems> itemsToBeDisplayed = new ArrayList<>();
        Retrofit retrofit = GESingleton.getINSTANCE();
        if (queryResult != null) {
            for (int i = 0; i < queryResult.size(); i++) {
                Call<GEModels> call = retrofit.create(GEService.class).getItem(queryResult.get(i).toString());
                Log.d(TAG, "makeQueryCall: " + call.request());

                call.enqueue(new Callback<GEModels>() {
                    @Override
                    public void onResponse(Call<GEModels> call, Response<GEModels> response) {
                        Log.d(TAG, "makeQueryCall: " + response.body().item.name);
                        itemsToBeDisplayed.add(response.body().item);
                    }

                    @Override
                    public void onFailure(Call<GEModels> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
            geAdapter.setData(itemsToBeDisplayed);
        } else {
            Toast.makeText(context, "queryResult is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void makeDynamicCall() {
        Retrofit retrofit = GESingleton.getINSTANCE();
        Call<GEModels> call = retrofit.create(GEService.class).getSearch("a", 1);
        call.enqueue(new Callback<GEModels>() {
            @Override
            public void onResponse(Call<GEModels> call, Response<GEModels> response) {
                Log.d(TAG, "onResponse: " + response.body().items);
                geAdapter.setData(response.body().items);
            }

            @Override
            public void onFailure(Call<GEModels> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

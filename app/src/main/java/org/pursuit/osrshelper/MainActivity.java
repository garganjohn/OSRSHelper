package org.pursuit.osrshelper;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;
import org.pursuit.osrshelper.item_search.SearchHelper;
import org.pursuit.osrshelper.network.GEModel;
import org.pursuit.osrshelper.network.GEModels;
import org.pursuit.osrshelper.network.GEService;
import org.pursuit.osrshelper.network.GESingleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ur it";
    private Button testBtn;
    private EditText itemInput;
    private String itemToBeSearched;
    private GEAdapter geAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        makeDynamicCall();

        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                testBtn.setOnClickListener(v -> {
                    itemToBeSearched = itemInput.getText().toString();
                    SearchHelper searchHelper = new SearchHelper(MainActivity.this, itemToBeSearched);
                    //makeCall(itemToBeSearched);
                    Log.d(TAG, "onCreate: " + searchHelper.userQuery());
                    handler.postDelayed(this, 3000);
                });
            }

        };
        handler.postDelayed(r, 3000);

    }

    public void makeCall(String itemName) {
        Retrofit retrofit = GESingleton.getINSTANCE();
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

    void initViews() {
        RecyclerView geRecyclerView = findViewById(R.id.ge_recyclerview);
        geRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        geRecyclerView.setAdapter(geAdapter = new GEAdapter(new ArrayList<>()));
        testBtn = findViewById(R.id.search_button);
        itemInput = findViewById(R.id.item_input);
    }
}

package org.pursuit.osrshelper;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;
import org.pursuit.osrshelper.item_search.SearchHelper;
import org.pursuit.osrshelper.network.GEModels;
import org.pursuit.osrshelper.network.GEService;
import org.pursuit.osrshelper.network.GESingleton;

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

        testBtn.setOnClickListener(v -> {
            itemToBeSearched = itemInput.getText().toString();
            SearchHelper searchHelper = new SearchHelper(MainActivity.this, itemToBeSearched);
            Log.d(TAG, "onCreate: " + searchHelper.userQuery());
            makeQueryCall(searchHelper.userQuery());
        });
    }

//    private void initSearchButton() {
//        Handler handler = new Handler();
//        final Runnable r = new Runnable() {
//            @Override
//            public void run() {
//
//                    handler.postDelayed(this, 3000);
//                });
//            }
//
//        };
//        handler.postDelayed(r, 3000);
//    }


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
            Toast.makeText(this, "queryResult is null", Toast.LENGTH_SHORT).show();
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

    void initViews() {
        RecyclerView geRecyclerView = findViewById(R.id.ge_recyclerview);
        geRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        geRecyclerView.setAdapter(geAdapter = new GEAdapter(new ArrayList<>()));
        testBtn = findViewById(R.id.search_button);
        itemInput = findViewById(R.id.item_input);
    }
}

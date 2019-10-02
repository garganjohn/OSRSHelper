package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;
import org.pursuit.osrshelper.network.GEModel;
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
    private List<GEModel> geModels;
    private GEAdapter geAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        makeCall();

//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                itemToBeSearched = itemInput.getText().toString();
//                makeCall();
//            }
//        });

    }

    public void makeCall() {
        Retrofit retrofit = GESingleton.getINSTANCE();
        Call<List<GEModel>> call = retrofit.create(GEService.class).getSearch("a", 1);
        Log.d(TAG, "onCreate: " + call.request());
        call.enqueue(new Callback<List<GEModel>>() {
            @Override
            public void onResponse(Call<List<GEModel>> call, Response<List<GEModel>> response) {
                geModels = response.body();
                geAdapter.setData(geModels);
                Log.d(TAG, "onResponse: " + geModels);
            }

            @Override
            public void onFailure(Call<List<GEModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    void initViews() {
        RecyclerView geRecyclerView = findViewById(R.id.ge_recyclerview);
        geRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        geRecyclerView.setAdapter(new GEAdapter(new ArrayList<GEModel>()));
        testBtn = findViewById(R.id.test_btn);
        itemInput = findViewById(R.id.item_input);
    }
}

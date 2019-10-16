package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;
import org.pursuit.osrshelper.item_search.SearchHelper;

import org.pursuit.osrshelper.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ur it";
    private NetworkHelper networkHelper;
    private SearchHelper searchHelper;
    private Button testBtn;
    private EditText itemInput;
    private String itemToBeSearched;
    private GEAdapter geAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initHelpers();
        networkHelper.makeDynamicCall();

        testBtn.setOnClickListener(v -> {
            itemToBeSearched = itemInput.getText().toString();
            Log.d(TAG, "onCreate: " + searchHelper.userQuery(itemToBeSearched));
            makeQueryCall(searchHelper.userQuery(itemToBeSearched));
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(this, "DEVw", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initHelpers() {
        searchHelper = new SearchHelper(MainActivity.this);
        networkHelper = new NetworkHelper(this, geAdapter);

    }

    private void makeQueryCall(List<Integer> queryResult) {
        networkHelper.makeQueryCall(queryResult);
    }


    void initViews() {
        RecyclerView geRecyclerView = findViewById(R.id.ge_recyclerview);
        geRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        geRecyclerView.setAdapter(geAdapter = new GEAdapter(new ArrayList<>()));
        testBtn = findViewById(R.id.search_button);
        itemInput = findViewById(R.id.item_input);
    }
}

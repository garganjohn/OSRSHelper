package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;


import org.pursuit.osrshelper.ge_recyclerview.GEAdapter;
import org.pursuit.osrshelper.item_search.SearchHelper;

import org.pursuit.osrshelper.network.GEModels;
import org.pursuit.osrshelper.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String TAG = "ur it";
    private NetworkHelper networkHelper;
    private SearchHelper searchHelper;
    private Button searchButton;
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

        searchButton.setOnClickListener(v -> {

        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        //itemToBeSearched = itemInput.getText().toString();
        Log.d(TAG, "onCreate: " + searchHelper.userQuery(newText));
        makeQueryCall(searchHelper.userQuery(newText));
        return false;
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
        SearchView searchView = findViewById(R.id.item_searchview);
        searchView.setOnQueryTextListener(this);
        searchButton = findViewById(R.id.search_button);
        itemInput = findViewById(R.id.item_input);
    }


}

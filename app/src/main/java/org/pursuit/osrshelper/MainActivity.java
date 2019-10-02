package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.pursuit.osrshelper.network.GEModel;
import org.pursuit.osrshelper.network.GEService;
import org.pursuit.osrshelper.network.GESingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ur it";
    private ImageView imageView;
    private Button testBtn;
    private EditText itemInput;
    private String itemToBeSearched;
    private TextView itemName;
    private TextView currentPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemToBeSearched = itemInput.getText().toString();
                makeCall(itemToBeSearched);
            }
        });

    }

    public void makeCall(String query) {
        Retrofit retrofit = GESingleton.getINSTANCE();
        Call<GEModel> call = retrofit.create(GEService.class).getWhip(query);
        Log.d(TAG, "onCreate: " + call.request());
        call.enqueue(new Callback<GEModel>() {
            @Override
            public void onResponse(Call<GEModel> call, Response<GEModel> response) {
                assert response.body() != null;

                try {
                    Picasso.get().load(response.body().item.icon_large).into(imageView);
                    itemName.setText(response.body().item.name);
                    currentPrice.setText(response.body().item.current.price);

                } catch (NullPointerException npe) {
                    Toast.makeText(MainActivity.this, "Enter a valid id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GEModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    void initViews() {
        imageView = findViewById(R.id.whip_pic);
        testBtn = findViewById(R.id.test_btn);
        itemInput = findViewById(R.id.item_input);
        itemName = findViewById(R.id.item_name);
        currentPrice = findViewById(R.id.current_price);
    }
}

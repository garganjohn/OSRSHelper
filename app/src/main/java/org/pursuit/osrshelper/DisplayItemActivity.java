package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.pursuit.osrshelper.ge_recyclerview.GEViewHolder;
import org.pursuit.osrshelper.network.GEModels;

public class DisplayItemActivity extends AppCompatActivity {
    private TextView itemName;
    private TextView itemCurrentPrice;
    private TextView itemDesc;
    private GEModels.GEItems itemToBeDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);
        initViews();

        itemToBeDisplayed = (GEModels.GEItems) getIntent().getSerializableExtra(GEViewHolder.ITEM);

        itemName.setText(itemToBeDisplayed.name);
        itemDesc.setText(itemToBeDisplayed.description);

        itemCurrentPrice.setText(itemToBeDisplayed.current.price);

    }

    private void initViews() {
        itemName = findViewById(R.id.item_name_tv);
        itemCurrentPrice = findViewById(R.id.current_price_tv);
        itemDesc = findViewById(R.id.item_description_tv);
    }
}

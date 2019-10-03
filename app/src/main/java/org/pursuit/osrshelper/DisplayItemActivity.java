package org.pursuit.osrshelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.pursuit.osrshelper.ge_recyclerview.GEViewHolder;

public class DisplayItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        int id = getIntent().getIntExtra(GEViewHolder.ITEM_ID, -11100);
        TextView textView = findViewById(R.id.display_activity_tv);
        textView.setText(id + "");
    }
}

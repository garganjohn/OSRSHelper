package org.pursuit.osrshelper.ge_recyclerview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.osrshelper.DisplayItemActivity;
import org.pursuit.osrshelper.R;
import org.pursuit.osrshelper.network.GEModels;

public class GEViewHolder extends RecyclerView.ViewHolder {
    public static final String ITEM_ID = "ITEM TO BE DISPLAYED";
    private CardView container;
    private TextView itemName;
    private ImageView itemImage;
    private TextView itemCurrentPrice;
    private TextView itemDesc;

    public GEViewHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.item_img);
        itemName = itemView.findViewById(R.id.item_name);
        itemCurrentPrice = itemView.findViewById(R.id.current_price);
        itemDesc = itemView.findViewById(R.id.item_description);
        container = itemView.findViewById(R.id.ge_cardview);


    }

    void onBind(GEModels.GEItems geModel) {
        Picasso.get().load(geModel.icon_large).into(itemImage);
        itemName.setText(geModel.name);
        itemCurrentPrice.setText(geModel.current.price);
        itemDesc.setText(geModel.description);
        container.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), DisplayItemActivity.class);
            intent.putExtra(ITEM_ID, geModel.id);
            itemView.getContext().startActivity(intent);
        });
    }
}

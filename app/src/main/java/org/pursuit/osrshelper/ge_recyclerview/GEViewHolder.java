package org.pursuit.osrshelper.ge_recyclerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;
import com.squareup.picasso.Picasso;

import org.pursuit.osrshelper.DisplayItemActivity;
import org.pursuit.osrshelper.R;
import org.pursuit.osrshelper.network.GEModels;

import java.util.concurrent.TimeUnit;

public class GEViewHolder extends RecyclerView.ViewHolder {
    public static final String ITEM = "ITEM TO BE DISPLAYED";
    private CardView container;
    private TextView itemName;
    private ImageView itemImage;
    private TextView itemCurrentPrice;

    public GEViewHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.item_img);
        itemName = itemView.findViewById(R.id.item_name);
        itemCurrentPrice = itemView.findViewById(R.id.current_price);
        container = itemView.findViewById(R.id.ge_cardview);
    }

    @SuppressLint("CheckResult")
    void onBind(GEModels.GEItems geModel) {
        Picasso.get().load(geModel.icon_large).into(itemImage);
        itemName.setText(geModel.name);
        itemCurrentPrice.setText(geModel.current.price);

        RxView.clicks(container).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(empty -> {
            Intent intent = new Intent(itemView.getContext(), DisplayItemActivity.class);
            intent.putExtra(ITEM, geModel);
            itemView.getContext().startActivity(intent);
        });

    }
}

package org.pursuit.osrshelper.ge_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.osrshelper.R;
import org.pursuit.osrshelper.network.GEModel;
import org.pursuit.osrshelper.network.GEModels;

public class GEViewHolder extends RecyclerView.ViewHolder {
    private TextView itemName;
    private ImageView itemImage;

    public GEViewHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.item_img);
        itemName = itemView.findViewById(R.id.item_name);
    }
    void onBind(GEModels.GEItems geModel){
//        Picasso.get().load(geModel.item.icon_large).into(itemImage);
////        itemName.setText(geModel.item.name);
        itemName.setText(geModel.name);
    }
}

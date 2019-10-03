package org.pursuit.osrshelper.ge_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.osrshelper.R;
import org.pursuit.osrshelper.network.GEModels;

import java.util.List;

public class GEAdapter extends RecyclerView.Adapter<GEViewHolder> {
    private List<GEModels.GEItems> geModels;

    public GEAdapter(List<GEModels.GEItems> geModels) {
        this.geModels = geModels;
    }

    @NonNull
    @Override
    public GEViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ge_itemview, viewGroup, false);
        return new GEViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull GEViewHolder geViewHolder, int i) {
        geViewHolder.onBind(geModels.get(i));
    }

    @Override
    public int getItemCount() {
        return geModels.size();
    }
    public void setData(List<GEModels.GEItems> geModels){
        this.geModels = geModels;
        notifyDataSetChanged();
    }
}

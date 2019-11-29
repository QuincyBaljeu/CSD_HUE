package com.example.csd_hue;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csd_hue.Database.Lamp;

import java.sql.Array;
import java.util.ArrayList;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> {


    private ArrayList<Lamp> lamps;

    public LampAdapter(ArrayList<Lamp> lamps){this.lamps = lamps;}

    @NonNull
    @Override
    public LampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LampViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LampViewHolder extends RecyclerView.ViewHolder {

        public TextView lampID;
        public Color backgroundColor;

        public LampViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}

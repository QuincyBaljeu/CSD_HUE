package com.example.csd_hue;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csd_hue.Database.Lamp;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LampAdapter extends RecyclerView.Adapter<LampAdapter.LampViewHolder> {


    private List<JSONObject> lamps;
    private JsonHandling jsonHandling;

    public LampAdapter(List<JSONObject> lamps, JsonHandling jsonHandling){
        this.lamps = lamps;
        this.jsonHandling = jsonHandling;
    }


    public class LampViewHolder extends RecyclerView.ViewHolder {
        public TextView lampID;
        public ImageView backgroundColor;

        public LampViewHolder(@NonNull View itemView) {
            super(itemView);

            lampID = itemView.findViewById(R.id.txt_lampName);
            backgroundColor = itemView.findViewById(R.id.image_lampColor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HueController.class);
                    JSONObject lamp = lamps.get(LampViewHolder.super.getAdapterPosition());
                    intent.putExtra("LAMP", lamp.toString());
                    intent.putExtra("JSON", jsonHandling);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public LampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_lamp, parent, false);
        return new LampViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LampViewHolder holder, int position) {
        JSONObject lamp = lamps.get(position);

        try {
            float[] lampHSV = {Float.valueOf(lamp.getJSONObject("state").getString("hue")),Float.valueOf(lamp.getJSONObject("state").getString("sat")), Float.valueOf(lamp.getJSONObject("state").getString("bri")) };
            holder.lampID.setText(lamp.getString("name"));
            holder.backgroundColor.setColorFilter(Color.HSVToColor(lampHSV));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        Log.d("asdasdasd", String.valueOf(lamps.size()));
        return lamps.size();
    }
}

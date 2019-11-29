package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Lamp_list extends AppCompatActivity {

    private ListView lampListView;
    private LampAdapter lampAdapter;
    private RecyclerView recyclerView;
    private List<JSONObject>lamps = new ArrayList<>();
    private String ip;
    private String port;
    private JsonHandling jsonHandling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_list);

        ip = getIntent().getExtras().getString("ip");
        port = getIntent().getExtras().getString("port");
        recyclerView = findViewById(R.id.RV_lamps);

        setup();
        Log.d("@d", "getting list...");

        /**
        Gson gson = new Gson();
        for (JSONObject objectLamp : lamps){
            Lamp convertedLamp = gson.fromJson(objectLamp.toString(), Lamp.class);
            convertedLamps.add(convertedLamp);
            Log.d("convert", "Converting a lamp");
        }

        for (Lamp conLamp : convertedLamps){
            Log.i("CONV", conLamp.toString());
        }
         */

        Button sendToDatabaseButton = findViewById(R.id.btn_sendToDb);
        Button readFromDatabaseButton = findViewById(R.id.btn_loaddb);

        sendToDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("X", String.valueOf(lamps.size()));
                for (JSONObject object : lamps){
                    try {
                        Log.d("@@@@", object.getJSONObject("state").getString("hue"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        readFromDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HueInfo = new Intent(Lamp_list.this, HueController.class);
                startActivity(HueInfo);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        Log.d("x", String.valueOf(lamps.size()));
        lampAdapter = new LampAdapter(lamps);
        recyclerView.setAdapter(lampAdapter);




        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("timer", "times ticking");
            }

            public void onFinish() {
                Log.d("timer", "finished");
                Log.d("please", String.valueOf(lamps.size()));
                lampAdapter.notifyDataSetChanged();
            }
        }.start();
        /**
        lampListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent HueInfo = new Intent(Lamp_list.this, HueController.class);
                startActivity(HueInfo);
            }
        });
         */

    }

    private void setup() {
        jsonHandling = new JsonHandling(this,ip,port, new LampFound() {
            @Override
            public void lampFound(JSONObject lamp) throws JSONException {
                JSONObject lights = lamp;

                for (int i = 0; i < lights.names().length(); i++) {
                    JSONObject lampObject = lights.getJSONObject(lights.names().getString(i));
                    Log.d("@d", "lampFound: " + lampObject.toString());
                }

                lampAdapter.notifyDataSetChanged();
                Log.d("d@",lights.toString());
            }
        });
        jsonHandling.setUp();

    }
}


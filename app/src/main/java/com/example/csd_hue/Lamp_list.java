package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Lamp_list extends AppCompatActivity {

    private ListView lampListView;
    private ArrayAdapter lampAdapter;
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

        lampListView = findViewById(R.id.lampListView);
        //TODO instantiate lamps array
        lampAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lamps);
        lampListView.setAdapter(lampAdapter);
        lampListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent HueInfo = new Intent(Lamp_list.this, HueController.class);
                startActivity(HueInfo);
            }
        });

        jsonHandling = new JsonHandling(this,ip,port, new LampFound() {
            @Override
            public void lampFound(JSONObject lamp) {
                lamps.add(lamp);
                lampAdapter.notifyDataSetChanged();
                Log.d("d@",lamp.toString());
            }
        });

        jsonHandling.setUp();
        while (!jsonHandling.isSetUp()) jsonHandling.getLampList();

        Log.d("@d", "getting list...");
    }

}

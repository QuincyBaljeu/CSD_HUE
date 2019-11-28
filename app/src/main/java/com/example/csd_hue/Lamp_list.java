package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Lamp_list extends AppCompatActivity {

    private ListView lampListView;
    private ArrayAdapter lampAdapter;
    private String[] lamps = { "1", "2", "3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_list);

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
    }


}

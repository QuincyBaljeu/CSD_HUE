package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import org.json.JSONException;

public class HueController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hue_controller);

        SeekBar bar_hue = findViewById(R.id.bar_hue);
        SeekBar bar_bri = findViewById(R.id.bar_brightness);
        SeekBar bar_sat = findViewById(R.id.bar_saturation);

        int position = (int) getIntent().getSerializableExtra("POS") + 1;

        SeekBar.OnSeekBarChangeListener listenr = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    JsonHandling.setLampColor(position, (bar_bri.getProgress()*253/100)+1, bar_hue.getProgress()*65533/100+1, (bar_sat.getProgress()*253/100+1), true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        bar_bri.setOnSeekBarChangeListener(listenr);
        bar_hue.setOnSeekBarChangeListener(listenr);
        bar_sat.setOnSeekBarChangeListener(listenr);



    }
}

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

        Button btn_sendToBridge = findViewById(R.id.btn_sendToBridge);

        SeekBar bar_hue = findViewById(R.id.bar_hue);
        SeekBar bar_bri = findViewById(R.id.bar_brightness);
        SeekBar bar_sat = findViewById(R.id.bar_saturation);

        btn_sendToBridge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("CONTROLLER", "Send to bridge");
                //method to send to bridge

            }
        });

        bar_hue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    JsonHandling.setLampColor(1, (bar_bri.getProgress()/100*255), (bar_hue.getProgress()*100/65535), (bar_sat.getProgress()/100*255), true);
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
        });

        bar_bri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    JsonHandling.setLampColor(1, (bar_bri.getProgress()/100*255), bar_hue.getProgress(), (bar_sat.getProgress()/100*255), true);
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
        });

        bar_sat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    JsonHandling.setLampColor(1, (bar_bri.getProgress()/100*255), bar_hue.getProgress(), (bar_sat.getProgress()/100*255), true);
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
        });



    }
}

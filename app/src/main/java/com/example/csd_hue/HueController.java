package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HueController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hue_controller);
        
        Button btn_sendToBridge = findViewById(R.id.btn_sendToBridge);

        btn_sendToBridge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("CONTROLLER", "Send to bridge");
                //method to send to bridge
            }
        });
    }
}

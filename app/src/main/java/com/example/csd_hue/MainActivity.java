package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_ip;
    private EditText et_userKey;
    private TextView error;
    private Switch switch_bridge;
    private boolean bridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_ip = findViewById(R.id.txt_ip);
        et_userKey = findViewById(R.id.txt_userkey);
        error = findViewById(R.id.tv_error);
        switch_bridge = findViewById(R.id.switch_Bridge);
        bridge = false;


        Button btn_connect = findViewById(R.id.btn_connect);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ip.getText().toString();
                String userkey;

                switch_bridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        bridge = isChecked;
                    }
                });

                Intent intent = new Intent(MainActivity.this, Lamp_list.class);
                intent.putExtra("ip",ip);
                intent.putExtra("bridge",bridge);
                startActivity(intent);
            }
        });
    }
}

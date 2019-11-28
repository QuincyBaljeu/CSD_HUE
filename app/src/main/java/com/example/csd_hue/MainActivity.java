package com.example.csd_hue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_ip;
    private EditText et_port;
    private EditText et_userKey;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_ip = findViewById(R.id.txt_ip);
        et_port = findViewById(R.id.txt_port);
        et_userKey = findViewById(R.id.txt_userkey);
        error = findViewById(R.id.tv_error);


        Button btn_connect = findViewById(R.id.btn_connect);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ip.getText().toString();
                String port = et_port.getText().toString();
                String userkey;

                Intent intent = new Intent(MainActivity.this, Lamp_list.class);
                intent.putExtra("ip",ip);
                intent.putExtra("port",port);
                
                startActivity(intent);
            }
        });
    }
}

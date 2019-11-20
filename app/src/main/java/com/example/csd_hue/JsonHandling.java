package com.example.csd_hue;

import android.app.Application;
import android.app.admin.DeviceAdminInfo;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandling {
    private RequestQueue requestQueue;
    private Context c;
    private String URL;
    private String username;

    public JsonHandling(Context c,String host, int port) {
       this.URL = "http://" + host + port + "/api";
        this.c = c;
    }

    public void setUp(){
        this.requestQueue = Volley.newRequestQueue(c);

        JSONObject getUserName = new JSONObject();
        try {
            getUserName.put("devicetype", c.getApplicationInfo().name + Build.MODEL);
        } catch (JSONException e) {
            Log.e("@E","JsonError: ",e);
        }

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, URL, getUserName, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    String rs = response.getJSONObject(0)
                            .getJSONObject("succes")
                            .getString("username");

                  username = rs;
                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("@E",error.toString());
            }
        });

    }
}

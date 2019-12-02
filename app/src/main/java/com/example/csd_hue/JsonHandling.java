package com.example.csd_hue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

 public class JsonHandling {
    private static RequestQueue requestQueue;
    private Context c;
    private static String URL;
    private static String username;
    private LampFound lampFound;
    List<JSONObject> lamps;

    private SharedPreferences pref;

    public JsonHandling(Context c, String host, LampFound lampFound) {
        this.URL = "http://" + host + ":" + 80 + "/api";
        this.c = c;
        this.lampFound = lampFound;
        pref = c.getSharedPreferences("HUE",Context.MODE_PRIVATE);
    }


    public void setUpBridge() {
        this.requestQueue = Volley.newRequestQueue(c);
        lamps = new ArrayList<>();
        JSONObject getUserName = new JSONObject();
        try {
            getUserName.put("devicetype", c.getApplicationInfo().name + Build.MODEL);
        } catch (JSONException e) {
            Log.e("@E", "JsonError: ", e);
        }
        String key =(pref.getString("HUEBridge",""));
        assert key != null;
        if (key.equals("")) {
            CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, URL, getUserName, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        username = response.getJSONObject(0)
                                .getJSONObject("success")
                                .getString("username");

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("HUEBridge", username);
                        editor.apply();
                        Log.d("@d", "set up completed!");
                    } catch (Exception e) {
                        Log.d("@d", "dont forget to press link!", e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("@E", error.toString());
                }
            });
            requestQueue.add(request);
        }else username = pref.getString("HUEBridge","error");
        getLampList();
    }

    public void setUpEmulator() {
        this.requestQueue = Volley.newRequestQueue(c);
        lamps = new ArrayList<>();
        JSONObject getUserName = new JSONObject();
        try {
            getUserName.put("devicetype", c.getApplicationInfo().name + Build.MODEL);
        } catch (JSONException e) {
            Log.e("@E", "JsonError: ", e);
        }
        String key =(pref.getString("HUEEmulator",""));
        assert key != null;
        if (key.equals("")) {
            CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, URL, getUserName, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                       username = response.getJSONObject(0)
                                .getJSONObject("success")
                                .getString("username");

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("HUEEmulator", username);
                        editor.apply();
                        Log.d("@d", "set up completed!");
                    } catch (Exception e) {
                        Log.d("@d", "dont forget to press link!", e);
                    }
                    getLampList();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("@E", error.toString());
                }
            });
            requestQueue.add(request);
        }else{
            username = pref.getString("HUEEmulator","error");
            getLampList();
        }
    }

    public void getLampList() {

            String putUrl = URL + "/" + username;
            JsonObjectRequest rq = new JsonObjectRequest(Request.Method.GET, putUrl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("D@zz", putUrl);
                    JSONArray array = null;
                    try {
//                        array = response.getJSONObject("lights").names();
//                        Log.d("@d", array.toString());
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject lamp = response.getJSONObject("lights").getJSONObject(array.getString(i));
//                            lamps.add(lamp);

                        JSONObject lights = response.getJSONObject("lights");
                        lampFound.lampFound(lights);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("@E", error.toString());
                }
            });
            requestQueue.add(rq);
    }

    public static void setLampColor(int id, int bri, double hueVal, int sat, boolean state) throws JSONException {
        String putUrl = URL + "/" + username + "/lights/" + id + "/state";
        JSONObject setLamp = new JSONObject();
        setLamp.put("bri", bri);
        setLamp.put("hue", hueVal);
        setLamp.put("sat", sat);
        setLamp.put("on", state);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, putUrl, setLamp, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("@D", response.toString());
            }
        }, null);
        requestQueue.add(jsonObjectRequest);
    }
}

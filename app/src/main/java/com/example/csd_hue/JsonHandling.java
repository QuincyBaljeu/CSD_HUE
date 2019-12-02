package com.example.csd_hue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
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

import java.util.ArrayList;
import java.util.List;


public class JsonHandling {
    private RequestQueue requestQueue;
    private Context c;
    private String URL;
    private String username;
    private LampFound lampFound;
    private boolean setUp = false;
    List<JSONObject> lamps;

    private SharedPreferences pref;

    public JsonHandling(Context c, String host, LampFound lampFound) {
        this.URL = "http://" + host + ":" + 80 + "/api";
        this.c = c;
        this.lampFound = lampFound;
        pref = c.getSharedPreferences("HUE",Context.MODE_PRIVATE);
    }

    public void setUp() {
        this.requestQueue = Volley.newRequestQueue(c);
        lamps = new ArrayList<>();
        JSONObject getUserName = new JSONObject();
        try {
            getUserName.put("devicetype", c.getApplicationInfo().name + Build.MODEL);
        } catch (JSONException e) {
            Log.e("@E", "JsonError: ", e);
        }

        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, URL, getUserName, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (pref.getString("HUE","").equals("") || pref.getString("HUE","") == null) {
                        String rs = response.getJSONObject(0)
                                .getJSONObject("success")
                                .getString("username");
                        username = rs;
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("HUE",username);
                        editor.commit();
                        Log.d("@d", "set up completed!");
                    }else {
                    username = pref.getString("HUE","");
                    }
                    getLampList();
                } catch (Exception e) {
                    Log.d("@d", "dont forget to press link!",e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("@E", error.toString());
            }
        });
        requestQueue.add(request);
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

    public void setLampColor(int id, int bri, int hueVal, int sat, boolean state) throws JSONException {
        String putUrl = URL + "/" + username + "/lights" + id + "/state";
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

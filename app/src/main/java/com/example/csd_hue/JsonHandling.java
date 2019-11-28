package com.example.csd_hue;

import android.content.Context;
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

    public JsonHandling(Context c, String host, int port) {
        this.URL = "http://" + host + ":" + port + "/api";
        this.c = c;
    }

    public void setUp() {
        this.requestQueue = Volley.newRequestQueue(c);

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
                    String rs = response.getJSONObject(0)
                            .getJSONObject("succes")
                            .getString("username");
                    username = rs;
                } catch (Exception e) {
                    Log.d("@d", "dont forget to press link!");
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

    public List<String> getLampList() {
        String putUrl = URL + "/" + username;
        ArrayList<String> listdata = new ArrayList<String>();
        JsonObjectRequest rq = new JsonObjectRequest(Request.Method.GET, putUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray ids = response.names();
                if (ids != null) {
                    for (int i = 0; i < ids.length(); i++) {
                        try {
                            listdata.add(ids.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("@E", error.toString());
            }
        });
        requestQueue.add(rq);
        return listdata;
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

package com.example.travelbd;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServerRequest {
    public void sendPostRequest(Context context, JSONObject jsonObject, String URL, final ServerResponseCallBack serverResponseCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("on responose", "onResponse: " + response.toString());
                        serverResponseCallback.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("on error", "onErrorResponse: ", error);
                        if (error.networkResponse!= null && error.networkResponse.statusCode == 400 )
                            serverResponseCallback.onError("invalid credential");
                        else if(error instanceof TimeoutError)
                            serverResponseCallback.onError("connection timed out");
                        else
                            serverResponseCallback.onError(error);
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public  void sendGetRequest(Context context,String url,final ServerResponseCallBack serverResponseCallBack){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                        serverResponseCallBack.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                        if(error instanceof  TimeoutError){
                            serverResponseCallBack.onError("connection timed out");
                        }
                        else if (error.networkResponse!= null && error.networkResponse.statusCode == 400 )
                            serverResponseCallBack.onError("invalid credential");
                        else
                            serverResponseCallBack.onError(error);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public  void sendGetArrayRequest(Context context,String url,final ServerResponseCallBack serverResponseCallBack){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        serverResponseCallBack.onJsonArray(response);
                    }
                },
        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                        if(error instanceof  TimeoutError){
                            serverResponseCallBack.onError("connection timed out");
                        }
                        else if (error.networkResponse!= null && error.networkResponse.statusCode == 400 )
                            serverResponseCallBack.onError("invalid credential");
                        else
                            serverResponseCallBack.onError(error);
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}

package com.example.travelbd;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ServerResponseCallBack {
    public void onResponse(JSONObject response);
    public void  onJsonArray(JSONArray arrayResponse);
    public void onError(String me);
    public  void  onError(Exception e);
}

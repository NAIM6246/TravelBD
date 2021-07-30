package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class AddPlace extends AppCompatActivity {
    final String TAG = AddPlace.class.getName();
    private Button addPlaceButton;
    private EditText name,district;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        addPlaceButton = findViewById(R.id.Add);
        name = findViewById(R.id.place_name);
        district = findViewById(R.id.place_district);

        addPlaceButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  JSONObject jsonObject = new JSONObject();
                  try {
                      jsonObject.put("name",name.getText().toString());
                      jsonObject.put("district",district.getText().toString());
                  } catch (JSONException e) {
                      Log.e(TAG,"onclidk : ",e);
                      Toast.makeText(AddPlace.this,"json exception",Toast.LENGTH_SHORT).show();
                  }
                  final String url = "http://192.168.0.105:8000/places/";
                  new ServerRequest().sendPostRequest(
                          getApplicationContext(),
                          jsonObject,
                          url, new ServerResponseCallBack(){
                              @Override
                              public void onResponse(JSONObject jsonObject) {
                                    Toast.makeText(getApplicationContext(),"place added successfully",Toast.LENGTH_LONG).show();
                                    openHome();
                              }

                              @Override
                              public void onJsonArray(JSONArray arrayResponse) {

                              }

                              @Override
                              public void onError(String me) {

                              }

                              @Override
                              public void onError(Exception e) {

                              }
                          });
//                  RequestQueue request = Volley.newRequestQueue(AddPlace.this);
//                  String url = "http://192.168.0.105:8000/places/";
//                  JSONObject jsonObject = new JSONObject();
//                  try {
//                      jsonObject.put("name",name.getText().toString());
//                      jsonObject.put("password",district.getText().toString());
//                  } catch (JSONException e) {
//                      e.printStackTrace();
//                  }
//                  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                          Request.Method.POST,
//                          url,
//                          jsonObject,
//                          new Response.Listener<JSONObject>() {
//                              @Override
//                              public void onResponse(JSONObject response) {
//                                  Toast.makeText(AddPlace.this,"Login Successful",Toast.LENGTH_SHORT).show();
//                                  openHome();
//                              }
//                          },
//                          new Response.ErrorListener() {
//                              @Override
//                              public void onErrorResponse(VolleyError error) {
//                                  Toast.makeText(AddPlace.this,error.toString(),Toast.LENGTH_SHORT).show();
//
//                              }
//                          }
//                  );
//                  request.add(jsonObjectRequest);
              }
          }
        );
    }

    public void openHome(){
        Intent intent = new Intent(this, Auth_Home.class);
        startActivity(intent);
        finish();
    }
}

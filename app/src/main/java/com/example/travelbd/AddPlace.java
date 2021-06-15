package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

public class AddPlace extends AppCompatActivity {
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
                  RequestQueue request = Volley.newRequestQueue(AddPlace.this);
                  String url = "http://192.168.0.101:8000/places/";
                  JSONObject jsonObject = new JSONObject();
                  try {
                      jsonObject.put("name",name.getText().toString());
                      jsonObject.put("district",district.getText().toString());
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
                  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                          Request.Method.POST,
                          url,
                          jsonObject,
                          new Response.Listener<JSONObject>() {
                              @Override
                              public void onResponse(JSONObject response) {
//                                  Toast.makeText(AddPlace.this,response.opt("name").toString(),Toast.LENGTH_LONG).show();
                                  Toast.makeText(AddPlace.this,response.toString(),Toast.LENGTH_SHORT).show();
                                  openHome();
                              }
                          },
                          new Response.ErrorListener() {
                              @Override
                              public void onErrorResponse(VolleyError error) {
                                  Toast.makeText(AddPlace.this,error.toString(),Toast.LENGTH_SHORT).show();

                              }
                          }
                  );
                  request.add(jsonObjectRequest);
                  //openHome();
              }
          }
        );
    }

    public void openHome(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

}

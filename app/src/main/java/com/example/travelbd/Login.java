package com.example.travelbd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private Button loginButton;
    private TextView register;
    TextView textView;
    EditText usr, pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr = findViewById(R.id.email);
        pas = findViewById(R.id.pass);
        textView = findViewById(R.id.textView);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String url = "http://192.168.0.105:8000/users/";
                  new ServerRequest().sendGetArrayRequest(
                          getApplicationContext(),
                          url,
                          new ServerResponseCallBack() {
                              @Override
                              public void onResponse(JSONObject jsonObject) {
                              }

                              @Override
                              public void onJsonArray(JSONArray jsonArray) {
                                  Toast.makeText(Login.this,jsonArray.toString(),Toast.LENGTH_SHORT).show();
                              }

                              @Override
                              public void onError(Exception e) {
                                  Toast.makeText(Login.this,e.toString(),Toast.LENGTH_SHORT).show();
                              }
                          });
//                  String user = usr.getText().toString();
//                  String pass = pas.getText().toString();
//                  ///doing here
//                  openHome();
                                          // Instantiate the RequestQueue.
//                                          RequestQueue queue = Volley.newRequestQueue(Login.this);
//                                          String url = "http://192.168.0.105:8000/users/";
//
//// Request a string response from the provided URL.
//                                          JsonArrayRequest stringRequest = new JsonArrayRequest(
//                                                  Request.Method.GET,
//                                                  url,
//                                                  null,
//                                                  new Response.Listener<JSONArray>() {
//                                                      @Override
//                                                      public void onResponse(JSONArray response) {
//                                                          //
//                                                          Toast.makeText(Login.this,response.toString(),Toast.LENGTH_SHORT).show();
//                                                      }
//                                                  }, new Response.ErrorListener() {
//                                              @Override
//                                              public void onErrorResponse(VolleyError error) {
//                                                  System.out.println(error);
//                                                  Toast.makeText(Login.this,error.toString(),Toast.LENGTH_SHORT).show();
//                                              }
//                                          });
//
//// Add the request to the RequestQueue.
//                                          queue.add(stringRequest);

                                      }
                                  }
        );
        register = (TextView) findViewById(R.id.registerHere);
        register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            openRegistration();
                                        }
                                    }
        );
    }

    public void openHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void openRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}

package com.example.travelbd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class Login extends AppCompatActivity {
    private Button button;
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
        button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override

                                      public void onClick(View v) {
//                  String user = usr.getText().toString();
//                  String pass = pas.getText().toString();
//                  ///doing here
//                  openHome();
                                          // Instantiate the RequestQueue.
                                          RequestQueue queue = Volley.newRequestQueue(Login.this);
                                          String url = "http://192.168.0.101:8000/users/";

// Request a string response from the provided URL.
                                          StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                  new Response.Listener<String>() {
                                                      @Override
                                                      public void onResponse(String response) {
                                                          //
                                                          Toast.makeText(Login.this,response,Toast.LENGTH_SHORT).show();
                                                      }
                                                  }, new Response.ErrorListener() {
                                              @Override
                                              public void onErrorResponse(VolleyError error) {
                                                  System.out.println(error);
                                                  Toast.makeText(Login.this,error.toString(),Toast.LENGTH_SHORT).show();
                                              }
                                          });

// Add the request to the RequestQueue.
                                          queue.add(stringRequest);

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

//    public class LoginUser extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... strings) {
//            String user = strings[0];
//            String pass = strings[1];
//            OkHttpClient okHttpClient = new OkHttpClient();
//            RequestBody formBody = new FormBody.Builder()
//                    .add("user_id", user)
//                    .add("pass", pass)
//                    .build();
//
//            Request request = new Request.Builder()
//                    .url("https://localhost:8000/users")
//                    .post(formBody)
//                    .build();
//
//            Response response = null;
//            try {
//                response = okHttpClient.newCall(request).execute();
//                if (response.isSuccessful()) {
//                    Toast.makeText(Login.this, response.code(), Toast.LENGTH_SHORT).show();
//                    System.out.println(response.code());
//                } else {
//                    System.out.println("connection error");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//    }
//}
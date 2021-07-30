package com.example.travelbd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private Button loginButton;
    private TextView register;
    TextView textView;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        textView = findViewById(R.id.textView);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  JSONObject jsonObject = new JSONObject();
                  try {
                      jsonObject.put("email",email.getText().toString());
                      jsonObject.put("password",password.getText().toString());
                  } catch (JSONException e) {
                      Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
                  }

                  String url = "http://192.168.0.105:8000/auth/login";
                  new ServerRequest().sendPostRequest(getApplicationContext(),
                          jsonObject,
                          url,
                          new ServerResponseCallBack() {
                              @Override
                              public void onResponse(JSONObject jsonObject) {

                                  try {
                                      if (jsonObject.getString("token").length()>0 ){
                                          JSONObject user = jsonObject.getJSONObject("user");
                                          //shared preferences to store token and user credential
                                          SharedPreferences userPref = getApplication().getApplicationContext().getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);
                                          SharedPreferences.Editor editor = userPref.edit();
                                          editor.putString("token",jsonObject.getString("token"));
                                          editor.putString("name",user.getString("name"));
                                          editor.putString("user_name",user.getString("user_name"));
                                          editor.putString("email",user.getString("email"));
                                          editor.putBoolean("isLoggedIn",true);
                                          editor.apply();
                                          //if success
                                          openHome();
                                      }
                                  }catch(JSONException e){
                                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                  }
                              }

                              @Override
                              public void onJsonArray(JSONArray jsonArray) {
                              }

                              @Override
                              public void onError(String e) {
                                  Toast.makeText(Login.this,e,Toast.LENGTH_SHORT).show();
                              }

                              @Override
                              public void onError(Exception e) {
                                  Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                              }
                          });
              }
        });
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
        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Auth_Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void openRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}

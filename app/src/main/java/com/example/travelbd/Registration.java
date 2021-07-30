package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity {
    private RelativeLayout register;
    private EditText name,username,email,pass,confirm_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (RelativeLayout) findViewById(R.id.register);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        confirm_pass = findViewById(R.id.confirm_pass);
        email = findViewById(R.id.emaill);
        register.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  if (!pass.getText().toString().equals(confirm_pass.getText().toString())) {
                      Toast.makeText(Registration.this, "password didn't match", Toast.LENGTH_SHORT).show();
                  }
                  else {
                  JSONObject jsonObject = new JSONObject();
                  try {
                      jsonObject.put("name", name.getText().toString());
                      jsonObject.put("user_name", username.getText().toString());
                      jsonObject.put("password", pass.getText().toString());
                      jsonObject.put("email", email.getText().toString());
                  } catch (JSONException e) {
                      Toast.makeText(Registration.this, e.toString(), Toast.LENGTH_SHORT).show();
                  }

                  String url = "http://192.168.0.105:8000/users/";
                  new ServerRequest().sendPostRequest(
                          getApplicationContext(),
                          jsonObject,
                          url,
                          new ServerResponseCallBack() {
                              @Override
                              public void onResponse(JSONObject response) {
                                  Toast.makeText(Registration.this,response.toString(),Toast.LENGTH_SHORT).show();
                                  openHome();
                              }

                              @Override
                              public void onJsonArray(JSONArray jsonArray) {
                              }

                              @Override
                              public void onError(String me) {
                              }

                              @Override
                              public void onError(Exception e) {
                                  Toast.makeText(Registration.this,e.toString(),Toast.LENGTH_SHORT).show();
                              }
                          }

                  );
                  Toast.makeText(Registration.this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
              }
              }
          }

        );
    }

    public void openHome(){
        Intent intent = new Intent(this, Show_Place.class);
        startActivity(intent);
        finish();
    }
}

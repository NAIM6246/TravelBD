package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static  int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences user = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                boolean isLoggedIn = user.getBoolean("isLoggedIn",false);
                if(isLoggedIn){
                    startActivity(new Intent(MainActivity.this,Auth_Home.class));
                }
                else {
                    startActivity(new Intent(MainActivity.this,Home.class));
                }
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
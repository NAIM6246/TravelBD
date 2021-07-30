package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences user = getSharedPreferences("user",Context.MODE_PRIVATE);
        boolean isLoggedin = user.getBoolean("isLoggedIn",false);
        if(!isLoggedin) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            button = findViewById(R.id.sign_in);
            button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              openLogin();
                                          }
                                      }
            );

            button = findViewById(R.id.see_places);
            button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              openShowPlace();
                                          }
                                      }
            );

            button = findViewById(R.id.register);
            button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              openRegistration();
                                          }
                                      }
            );
        }
        else {
            opneHome();
        }
    }

    public void openLogin(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void openShowPlace(){
        Intent intent = new Intent(this, Show_Place.class);
        startActivity(intent);
    }
    public void opneHome(){
        Intent intent = new Intent(this, Auth_Home.class);
        startActivity(intent);
    }

    public void openRegistration(){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

    public void openUserProfile(){
        Intent intent = new Intent(this,UserProfile.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actio_profile) {
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                    boolean isLoggedIn = user.getBoolean("isLoggedIn",false);
                    if(isLoggedIn){
                        openUserProfile();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please login first" , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Home.this,Login.class));
                    }
                    finish();
                    return true;
                }
            });
        }
        return(super.onOptionsItemSelected(item));
    }
}

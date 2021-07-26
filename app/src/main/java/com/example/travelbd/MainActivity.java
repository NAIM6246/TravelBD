package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =  findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openLogin();
              }
          }
        );

        button =  findViewById(R.id.see_places);
        button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openHome();
              }
          }
        );

        button =  findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openRegistration();
              }
          }
        );

    }

    public void openLogin(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void openHome(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

    public void openRegistration(){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

}

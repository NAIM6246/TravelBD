package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Registration extends AppCompatActivity {
    private RelativeLayout button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        button = (RelativeLayout) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openHome();
              }
          }
        );
    }

    public void openHome(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}

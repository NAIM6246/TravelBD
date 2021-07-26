package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Auth_Home extends AppCompatActivity {
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_home);

        button =  findViewById(R.id.see_places);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          openHome();
                                      }
                                  }
        );

        button =  findViewById(R.id.addPlace);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          openAddPlace();
                                      }
                                  }
        );

    }

    public void openHome(){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }


    public void openAddPlace(){
        Intent intent = new Intent(this,AddPlace.class);
        startActivity(intent);
    }

}


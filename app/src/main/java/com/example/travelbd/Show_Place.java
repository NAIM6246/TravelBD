package com.example.travelbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Show_Place extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place);

        button =  findViewById(R.id.see_according_to_divisions);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          openDivisions();
                                      }
                                  }
        );
    }
    public void openDivisions(){
        Intent intent = new Intent(this ,Divisions.class);
        startActivity(intent);
    }
}

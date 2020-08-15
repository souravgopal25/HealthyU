package com.example.healthyu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthyu.R;

public class EntrySlider extends AppCompatActivity {
    Button getStarteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_slider);


        getStarteBtn = (Button) findViewById(R.id.getStarteBtn);
        getStarteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(EntrySlider.this, DoctorUser.class);
                startActivity(i);
            }
        });
    }
}
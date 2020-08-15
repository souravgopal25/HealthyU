package com.example.healthyu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.healthyu.R;
import com.example.healthyu.model.Covid;
import com.example.healthyu.viewModel.CovidViewModel;

public class CovidData extends AppCompatActivity {
    CovidViewModel covidViewModel;
    Covid covid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_data);
        covidViewModel= ViewModelProviders.of(this).get(CovidViewModel.class);
        covidViewModel.getmData().observe(this, new Observer<Covid>() {
            @Override
            public void onChanged(Covid covid) {
                covid1=covid;

            }
        });

    }
}
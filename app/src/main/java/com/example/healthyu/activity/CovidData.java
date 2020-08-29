package com.example.healthyu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.healthyu.R;
import com.example.healthyu.model.Covid;
import com.example.healthyu.viewModel.CovidViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CovidData extends AppCompatActivity {
    CovidViewModel covidViewModel;
    Covid covid1;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.active)
    TextView active;
    @BindView(R.id.recovered)
    TextView recovered;
    @BindView(R.id.death)
    TextView death;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_data);
        ButterKnife.bind(this);
        /*covidViewModel = ViewModelProviders.of(this).get(CovidViewModel.class);
        covidViewModel.getmData().observe(this, new Observer<Covid>() {
            @Override
            public void onChanged(Covid covid) {


            }
        });*/
        active.setText("24,299,923");
        recovered.setText("1,61,07,859");
        death.setText("827,730");


    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
        String url = "https://www.worldometers.info/coronavirus/country/indonesia/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
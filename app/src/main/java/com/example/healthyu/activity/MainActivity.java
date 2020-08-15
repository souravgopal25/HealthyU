package com.example.healthyu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.patient)
    Button patient;
    @BindView(R.id.dr)
    Button dr;
    @BindView(R.id.hospitalnearby)
    Button hospitalnearby;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.signup)
    Button signup;
    @BindView(R.id.news)
    Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.patient, R.id.dr, R.id.hospitalnearby, R.id.login, R.id.signup, R.id.news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.patient:
                Intent intent=new Intent(this,PaitientAppointmentRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.dr: Intent intent1=new Intent(this,DoctorApprovalAppointment.class);
                startActivity(intent1);
                break;
            case R.id.hospitalnearby: //TODO ADD MAP
                //TODO GET GPS DATA IN ON CREATE SO THAT WE GET DATA HERE AS IT TAKES TIME TO FETCH DATA
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.login: Intent intent3=new Intent(this,LoginActivity.class);
                startActivity(intent3);
                break;
            case R.id.signup: Intent intent4=new Intent(this,SignupActivity.class);
                startActivity(intent4);
                break;
            case R.id.news: Intent intent5=new Intent(this,NewsActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
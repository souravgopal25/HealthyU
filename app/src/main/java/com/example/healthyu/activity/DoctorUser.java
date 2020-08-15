package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DATA;
import static com.example.healthyu.activity.Data.DOCTOR;
import static com.example.healthyu.activity.Data.USER;

public class DoctorUser extends AppCompatActivity {

    @BindView(R.id.doctor)
    ImageButton doctor;
    @BindView(R.id.user)
    ImageButton user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.doctor, R.id.user})
    public void onViewClicked(View view) {
        Intent intent=new Intent(DoctorUser.this,LoginActivity.class);
        switch (view.getId()) {
            case R.id.doctor:
                intent.putExtra(DATA,DOCTOR);
                startActivity(intent);
                break;
            case R.id.user:intent.putExtra(DATA,USER);
                startActivity(intent);
                break;
        }
    }
}
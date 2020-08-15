package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.model.Doctor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;

public class DoctorMain extends AppCompatActivity {

    @BindView(R.id.appointment)
    Button appointment;
    @BindView(R.id.logs)
    Button logs;
    @BindView(R.id.news)
    Button news;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        email=intent.getStringExtra(DOCTOREMAIL);

    }

    @OnClick({R.id.appointment,R.id.logs, R.id.news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.appointment:
                Intent intent=new Intent(DoctorMain.this,DoctorApprovalAppointment.class);
                intent.putExtra(DOCTOREMAIL,email);
                startActivity(intent);
                break;
            case R.id.logs:
                Intent intent1=new Intent(DoctorMain.this,DoctorLogs.class);
                intent1.putExtra(DOCTOREMAIL,email);
                startActivity(intent1);

            case R.id.news:Intent intent2 = new Intent(DoctorMain.this,NewsActivity.class);
                            startActivity(intent2);
                break;
        }
    }
}
package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.model.AppointmentRequest;
import com.example.healthyu.utils.FireBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;
import static com.example.healthyu.activity.Data.USEREMAIL;

public class PaitientAppointmentRequestActivity extends AppCompatActivity {


    String drmail, useremail;
    @BindView(R.id.disease_tile)
    EditText diseaseTile;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.name)
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitient_appointment_request);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        drmail = intent.getStringExtra(DOCTOREMAIL);
        useremail = intent.getStringExtra(USEREMAIL);
    }




    @OnClick(R.id.submit)
    public void onViewClicked() {
        String  mName, mProblem;
        mName = name.getText().toString();
        mProblem = description.getText().toString();
        AppointmentRequest appointmentRequest = new AppointmentRequest(mProblem,drmail,mName);
        if (!FireBase.putAppointmentRequest(appointmentRequest)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(PaitientAppointmentRequestActivity.this,MainActivity.class);
        } else {
            Toast.makeText(this, "DEBUG", Toast.LENGTH_SHORT).show();
        }
    }
}
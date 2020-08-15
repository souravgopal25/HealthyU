package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.model.AppointmentRequest;
import com.example.healthyu.utils.FireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;
import static com.example.healthyu.activity.Data.EMAIL;
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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        AppointmentRequest appointmentRequest = new AppointmentRequest(mProblem,drmail,mName);
        DatabaseReference myRef = database.getReference("APPOINTMENT").child(drmail).child("Pending");
        myRef.push().setValue(appointmentRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(PaitientAppointmentRequestActivity.this, "SUCCESSFULLY UPLOADED", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(PaitientAppointmentRequestActivity.this,MainActivity.class);
                intent.putExtra(EMAIL,useremail);
                startActivity(intent);
            }
        });
    }
}
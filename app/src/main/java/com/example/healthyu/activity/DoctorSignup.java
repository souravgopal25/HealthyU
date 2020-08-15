package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.model.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;

public class DoctorSignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.drid)
    EditText drid;
    @BindView(R.id.department)
    EditText department;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.gender)
    Spinner gender;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.cpass)
    EditText cpass;
    @BindView(R.id.submit)
    Button submit;
    String[] Gender = {"Male", "Female", "Rather Not to say"};
    String mName, mDrid, mAge, mSex, mPhone, mEmail, mPass, mcPass, mdept;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);
        ButterKnife.bind(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);
        gender.setOnItemSelectedListener(DoctorSignup.this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("PROFILE");
        mAuth = FirebaseAuth.getInstance();

    }


    @OnClick(R.id.submit)
    public void onViewClicked() {
        mName = name.getText().toString().trim();
        mDrid = drid.getText().toString().trim();
        mAge = age.getText().toString().trim();
        mPhone = phone.getText().toString().trim();
        mEmail = email.getText().toString().trim();
        mPass = pass.getText().toString().trim();
        mcPass = cpass.getText().toString().trim();
        mdept = department.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
            name.setError("Name REQUIRED");
            return;

        }
        if (TextUtils.isEmpty(mDrid)) {
            Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
            name.setError("Name REQUIRED");
            return;

        }
        if (TextUtils.isEmpty(mdept)) {
            Toast.makeText(this, "Please Enter Your Department", Toast.LENGTH_SHORT).show();
            department.setError("Department Required");
        }
        if (TextUtils.isEmpty(mAge)) {
            Toast.makeText(this, "Please Enter Age", Toast.LENGTH_SHORT).show();
            age.setError("Age REQUIRED");
            return;

        }
        if (TextUtils.isEmpty(mPhone)) {
            Toast.makeText(this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
            phone.setError("Phone REQUIRED");
            return;

        }
        if (TextUtils.isEmpty(mEmail)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            email.setError("Email REQUIRED");
            return;

        }


        if (TextUtils.isEmpty(mPass)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            pass.setError("Password required");
            return;

        }
        if (TextUtils.isEmpty(mcPass)) {
            Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            pass.setError("Password required");
            return;

        }
        if (!mPass.equals(mcPass)) {
            Toast.makeText(this, "Doesn't Matches Password", Toast.LENGTH_SHORT).show();
            return;
        }
        Doctor doctor = new Doctor(mName, mDrid, mAge, sex, mPhone, mEmail, mdept);
        mAuth.createUserWithEmailAndPassword(mEmail, mPass)
                .addOnCompleteListener(DoctorSignup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            doctor.setUid(currentUser.getUid());
                            myRef.child("DOCTOR").push().setValue(doctor);
                            Toast.makeText(DoctorSignup.this, "SUCCESSFULL", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DoctorSignup.this, DoctorMain.class);
                            intent.putExtra(DOCTOREMAIL,mEmail);
                            startActivity(intent);

                        } else {
                            Toast.makeText(DoctorSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner) adapterView;
        if (spin.getId() == R.id.gender) {
            sex = Gender[i];

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
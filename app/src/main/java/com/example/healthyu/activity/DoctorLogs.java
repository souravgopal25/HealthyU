package com.example.healthyu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthyu.R;
import com.example.healthyu.adapter.AppointmentAdapter;
import com.example.healthyu.model.AppointmentRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DoctorLogs extends AppCompatActivity {

    List<AppointmentRequest> mlist;
    AppointmentAdapter appointmentAdapter;
    String email;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_logs);
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("APPOINTMENT").child(email).child("APPROVED");
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                ) {
                    AppointmentRequest appointmentRequest=(AppointmentRequest) dataSnapshot.getValue(AppointmentRequest.class);
                    mlist.add(appointmentRequest);
                    System.out.println("SIZE OF MLIST"+mlist.size());

                }
                appointmentAdapter.setMlist(mlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("ERROR "+error.getMessage());

            }
        });
    }
}
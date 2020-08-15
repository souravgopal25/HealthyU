package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.adapter.AppointmentAdapter;
import com.example.healthyu.adapter.LogAdapter;
import com.example.healthyu.model.AppointmentRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;

public class DoctorLogs extends AppCompatActivity {

    List<AppointmentRequest> mlist;
    AppointmentAdapter appointmentAdapter;
    String email;
    FirebaseDatabase database;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    LogAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_logs);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        email = intent.getStringExtra(DOCTOREMAIL);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        mlist=new ArrayList<>();
        logAdapter=new LogAdapter(this);
        recycler.setAdapter(logAdapter);
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("APPOINTMENT").child(email).child("ACCEPTED");
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mlist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    AppointmentRequest appointmentRequest = (AppointmentRequest) dataSnapshot.getValue(AppointmentRequest.class);
                    mlist.add(appointmentRequest);
                    System.out.println("SIZE OF MLIST" + mlist.size());

                }

                logAdapter.setMlist(mlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("ERROR " + error.getMessage());

            }
        });
    }
}
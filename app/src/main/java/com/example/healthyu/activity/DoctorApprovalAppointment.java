package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.adapter.AppointmentAdapter;
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

public class DoctorApprovalAppointment extends AppCompatActivity implements AppointmentAdapter.ListItemClickListener{

    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<AppointmentRequest> mlist;
    AppointmentAdapter appointmentAdapter;
    String email;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_approval_appointment);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        database=FirebaseDatabase.getInstance();
        email=intent.getStringExtra(DOCTOREMAIL);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);

        mlist=new ArrayList<>();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("APPOINTMENT").child(email).child("Pending");
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
        appointmentAdapter=new AppointmentAdapter(this,mlist,this);
        recycler.setAdapter(appointmentAdapter);

        Toast.makeText(this, "SIZE OF MLIST"+mlist.size(), Toast.LENGTH_SHORT).show();
        System.out.println("SIZE OF MLIST IN MAIN ACTIVITY "+mlist.size());

    }

    @Override
    public void onListItemClick(int clickedIndex, int buttonNumber) {
        Toast.makeText(this, "Clicked on "+Integer.toString(clickedIndex) + (buttonNumber==0?"Reject":"Accept"), Toast.LENGTH_SHORT).show();
        AppointmentRequest appointmentRequest=mlist.get(clickedIndex);
        DatabaseReference myRef = database.getReference("APPOINTMENT").child(appointmentRequest.getDoctorId());
        if (buttonNumber==0){

           myRef.child("REJECTED").push().setValue(appointmentRequest).isSuccessful();
        }else {
            myRef.child("ACCEPTED").push().setValue(appointmentRequest).isSuccessful();

        }
        mlist.remove(clickedIndex);

        appointmentAdapter.setMlist(mlist);

    }
}
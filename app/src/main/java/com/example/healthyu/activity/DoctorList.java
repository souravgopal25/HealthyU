package com.example.healthyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.adapter.DoctorListAdapter;
import com.example.healthyu.adapter.ListItemClickListener;
import com.example.healthyu.model.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.healthyu.activity.Data.DOCTOREMAIL;
import static com.example.healthyu.activity.Data.EMAIL;
import static com.example.healthyu.activity.Data.USEREMAIL;

public class DoctorList extends AppCompatActivity implements ListItemClickListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<Doctor> mlist;
    DoctorListAdapter doctorListAdapter;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        email=intent.getStringExtra(EMAIL);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        doctorListAdapter=new DoctorListAdapter(this,this);
        recycler.setAdapter(doctorListAdapter);
        readDoctors();




    }

    @Override
    public void onItemClick(int clickedindex) {
        Intent intent=new Intent(DoctorList.this,PaitientAppointmentRequestActivity.class);
        Doctor doctor=mlist.get(clickedindex);
        String drmail=doctor.getEmail();
        intent.putExtra(USEREMAIL,email);
        intent.putExtra(DOCTOREMAIL,drmail);
        startActivity(intent);

    }
    private void readDoctors() {
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("PROFILE").child("DOCTOR");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mlist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor doct=snapshot.getValue(Doctor.class);
                    mlist.add(doct);


                }
                doctorListAdapter.setMlist(mlist);


            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoctorList.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}
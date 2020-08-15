package com.example.healthyu.utils;


import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.healthyu.R;
import com.example.healthyu.model.AppointmentRequest;
import com.example.healthyu.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBase {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static  List<AppointmentRequest> mlist;
    public static boolean putAppointmentRequest(AppointmentRequest appointmentRequest){

        DatabaseReference myRef = database.getReference("APPOINTMENT").child(appointmentRequest.getDoctorId()).child("Pending");
        return myRef.push().setValue(appointmentRequest).isSuccessful();

    }
    public static void getmList(String ID){
        mlist=new ArrayList<>();
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database1.getReference("APPOINTMENT").child("2").child("Pending");
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                     ) {
                    AppointmentRequest appointmentRequest=(AppointmentRequest) dataSnapshot.getValue(AppointmentRequest.class);
                    mlist.add(appointmentRequest);
                    System.out.println("SIZE OF MLIST"+mlist.size());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("ERROR "+error.getMessage());

            }
        });



    }
    public static boolean uploadProfile(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("USER");
        final int[] x = {0};
        myRef.child(user.getUid()).child("Profile").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                x[0] =1;
            }
        });
        return x[0]==1?true:false;

    }
}

package com.example.healthyu.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.model.User;
import com.example.healthyu.utils.FireBase;
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

import static com.example.healthyu.activity.Data.EMAIL;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.fullName)
    EditText fullName;
    @BindView(R.id.userEmailId)
    EditText userEmailId;
    @BindView(R.id.mobileNumber)
    EditText mobileNumber;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.signUpBtn)
    Button signUpBtn;
    String name, email, mob, pass, cpass;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private static String TAG = SignupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }

    @OnClick(R.id.signUpBtn)
    public void onViewClicked() {
        name = fullName.getText().toString();
        email = userEmailId.getText().toString();
        mob = mobileNumber.getText().toString();
        pass = password.getText().toString();
        if (TextUtils.isEmpty(name)) {
            fullName.setError("Name Required");
            return;

        }
        if (TextUtils.isEmpty(email)) {
            userEmailId.setError("Email Id required");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Password Required");
            return;

        }


        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String uid = user.getUid();
                            User user1 = new User(name, pass, mob, uid, email);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("USER");
                            myRef.child("Profile").push().setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(SignupActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    intent.putExtra(EMAIL, email);
                                    startActivity(intent);
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException().getCause());
                            Toast.makeText(SignupActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });


    }

}
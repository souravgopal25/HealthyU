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
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyu.R;
import com.example.healthyu.utils.NetworkCheck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.DATA;
import static com.example.healthyu.activity.Data.EMAIL;
import static com.example.healthyu.activity.Data.USER;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_emailid)
    EditText loginEmailid;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.createAccount)
    Button createAccount;
    private String TAG=LoginActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        data=intent.getStringExtra(DATA);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(loginEmailid.getText().toString())) {
            loginEmailid.setError("Email Required");
            return;
        }
        if (TextUtils.isEmpty(loginPassword.getText().toString())) {
            loginPassword.setError("Password Required");
            return;
        }
        String email = loginEmailid.getText().toString();
        String password = loginPassword.getText().toString();
        signin(email, password);
    }

    private void signin(String email, String password) {
        if (NetworkCheck.connected(this)) {
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "Login :success");
                                Toast.makeText(LoginActivity.this, "Login :success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (data.equals(USER)) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra(EMAIL, email);
                                    startActivity(intent);
                                }else {
                                    Intent intent = new Intent(LoginActivity.this, DoctorMain.class);
                                    intent.putExtra(EMAIL, email);
                                    startActivity(intent);
                                }


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Login Failure"+ task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        } else {
            Toast.makeText(this, "NO Internet", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.createAccount)
    public void onViewClicked1() {
        if (data.equals(USER)) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(LoginActivity.this, DoctorSignup.class);
            startActivity(intent);

        }
    }
}
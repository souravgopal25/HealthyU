package com.example.healthyu.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.example.healthyu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.EMAIL;

public class MainActivity extends AppCompatActivity implements LocationListener {


    @BindView(R.id.appointment)
    ImageButton appointment;
    @BindView(R.id.nearby)
    ImageButton nearby;
    @BindView(R.id.emergency)
    ImageButton emergency;
    @BindView(R.id.news)
    ImageButton news;
    @BindView(R.id.tabLinks)
    RelativeLayout tabLinks;
    @BindView(R.id.medi_bot)
    ImageButton mediBot;
    @BindView(R.id.specialised_doctor)
    ImageButton specialisedDoctor;
    @BindView(R.id.covidupdates)
    Button covidupdates;
    @BindView(R.id.precautions)
    Button precautions;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer)
    ConstraintLayout drawer;
    double x, y;
    private LocationManager locationManager;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Intent intent = getIntent();
        String email = intent.getStringExtra(EMAIL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        onLocationChanged(location);
    }


    @OnClick({R.id.appointment, R.id.nearby, R.id.emergency, R.id.news, R.id.medi_bot, R.id.specialised_doctor, R.id.covidupdates, R.id.precautions})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.appointment:
            case R.id.specialised_doctor:
                Intent intent = new Intent(this, DoctorList.class);
                intent.putExtra(EMAIL, email);
                startActivity(intent);

                break;
            case R.id.nearby:
                Uri gmmIntentUri = Uri.parse("geo:" + x + "," + y + "?q=hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.emergency:
                Intent intent1 = new Intent(this, EmergencyActivity.class);
                EmergencyActivity.setXY(x, y);
                startActivity(intent1);


                break;
            case R.id.news:
                Intent intent3 = new Intent(this, NewsActivity.class);
                startActivity(intent3);
                break;

            case R.id.medi_bot:
                String url = "https://t.me/YourMediBot";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.covidupdates:
                                /* Intent cIntent = new Intent(this, .class);
                                 startActivity(cIntent);*/
                break;
            case R.id.precautions:
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        x = location.getLongitude();
        y = location.getLatitude();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
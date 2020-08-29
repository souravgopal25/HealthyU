package com.example.healthyu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.healthyu.R;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.healthyu.activity.Data.EMAIL;

public class MainActivity extends AppCompatActivity implements LocationListener {


    double x, y;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.botandspecial)
    RelativeLayout botandspecial;
    @BindView(R.id.ScrollView01)
    ScrollView ScrollView01;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private LocationManager locationManager;
    String email;
    ActionBarDrawerToggle toggle;

    @SuppressLint("RestrictedApi")
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
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//       //outState.put
//    }

    public void clickMenu(View view) {
        openDrawer(drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profile:
                        Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.appointment:
                        Toast.makeText(MainActivity.this, "Reserve A bed", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.doctors:
                        Toast.makeText(MainActivity.this, "Doctors", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.insurance:
                        Intent i = new Intent(MainActivity.this, insurance.class);
                        startActivity(i);
                        break;

                    case R.id.faq:
                        Intent intent2 = new Intent(MainActivity.this, FAQ.class);
                        startActivity(intent2);
                        break;

                    case R.id.donate:
                        Intent intent = new Intent(MainActivity.this, donate_us.class);
                        startActivity(intent);
                        break;
                    case R.id.about:
                        Intent intent3 = new Intent(MainActivity.this, AboutUs.class);
                        startActivity(intent3);
                        break;
                    case R.id.logout:
                        logout(MainActivity.this);
                        break;
                    default:
                        break;
                }
                return false;
            }

            private void logout(final Activity activity) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finishAffinity();
                        System.exit(0);
                    }
                });

                builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i){
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
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
                intent1.putExtra("x",x);
                intent1.putExtra("y",y);
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
                                 Intent cIntent = new Intent(this, CovidData.class);
                                 startActivity(cIntent);
                break;
            case R.id.precautions:
                Intent intent6 = new Intent(this, FirstAid.class);
                startActivity(intent6);
                break;
        }
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
                        finishAffinity();
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

    @Override
    public void onLocationChanged(@NonNull Location location) {

            x = location.getLongitude();
            y = location.getLatitude();


        }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onclick1(View view) {
        String url=view.getTag().toString();
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Calling onNavigation
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        }

    public void clickMenu(View view) {
        openDrawer(drawerLayout);
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
                        Toast.makeText(MainActivity.this, "Insurance", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.faq:
                        Toast.makeText(MainActivity.this, "FAQs", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.contact:
                        Toast.makeText(MainActivity.this, "Donate us Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About us Selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        logout(MainActivity.this);
                        break;
                    default:
                        break;
                }
                return false;
            }

            private  void logout(final Activity activity) {
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

}


package com.example.nari_app;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nari_app.Contact.ContactModel;
import com.example.nari_app.Contact.DbHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Menu extends AppCompatActivity {
    private  FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MaterialToolbar toolbar=findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id)
                {
                    case R.id.nav_home:
                        Toast.makeText(Menu.this, "Home is clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.add_contact:
                        AddContact();
                        break;

                    case R.id.sos:
                        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Menu.this);
                        getuserlocation();
                        break;

                    case R.id.location_track:
                        locationtracking();
                        break;

                    case R.id.fake_call:
                        Intent intent=new Intent(getApplicationContext(),fakecallamain.class);
                        startActivity(intent);
                        break;

                    case R.id.safe_unsafe:
                        Intent it=new Intent(getApplicationContext(),SafeUnsafeLocation.class);
                        startActivity(it);
                           break;


                    case R.id.reached_msg:
                         destreachedmsg();
                        break;

                    case R.id.logout:
                        finish();
                        startActivity( new Intent (Menu.this , MainActivity.class));
                        Toast.makeText(Menu.this, "logout is clicked", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        return true;
                }
                return true;
            }

        });

    }

    public void AddContact()
    {
        Intent intent=new Intent(this,Viewcontact.class);
        startActivity(intent);
    }
    public void locationtracking()
    {
        Intent intent=new Intent(this,MapsActivity.class);
        startActivity(intent);
    }


    public void getuserlocation(){

          FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    SmsManager smsManager = SmsManager.getDefault();
                   DbHelper db = new DbHelper(Menu.this);
                    List<ContactModel> list = db.getAllContacts();

                    for (ContactModel c : list) {
                        String message = "Hey, " + c.getName() + " Please urgently reach me out. Here are my coordinates.\n " + "http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                        smsManager.sendTextMessage(c.getPhoneNo(), null, message, null, null);
                    }
                } else {
                    String message = "Please urgently reach me out.\n" + "GPS was turned off.Couldn't find location. Call your nearest Police Station.";
                    SmsManager smsManager = SmsManager.getDefault();
                    DbHelper db = new DbHelper(Menu.this);
                    List<ContactModel> list = db.getAllContacts();
                    for (ContactModel c : list) {
                        smsManager.sendTextMessage(c.getPhoneNo(), null, message, null, null);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Check: ", "OnFailure");
                String message = " Please urgently reach me out.\n" + "GPS was turned off.Couldn't find location. Call your nearest Police Station.";
                SmsManager smsManager = SmsManager.getDefault();
                DbHelper db = new DbHelper(Menu.this);
                List<ContactModel> list = db.getAllContacts();
                for (ContactModel c : list) {
                    smsManager.sendTextMessage(c.getPhoneNo(), null, message, null, null);
                }
            }
        });

    }

    public void destreachedmsg() {
        SmsManager smsManager = SmsManager.getDefault();
        DbHelper db = new DbHelper(Menu.this);
        List<ContactModel> list = db.getAllContacts();
        for (ContactModel c : list) {
            String message = "Hey, " + c.getName() + "I Reached My Destination Safely...." ;
            smsManager.sendTextMessage(c.getPhoneNo(), null, message, null, null);
        }

    }
}
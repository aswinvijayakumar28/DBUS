package com.example.dbus;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class sos_b1 extends AppCompatActivity {

    private final static int SEND_SMS_PERMISSION_REQUEST_CODE=111;
    private final static int REQUEST_LOCATION=1;
    Button button_sos;
    String latitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_b1);

        button_sos= findViewById(R.id.sos_btn_b1);
        button_sos.setEnabled(false);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        if(checkPermission(Manifest.permission.SEND_SMS)){
            button_sos.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        button_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference();

                myRef.child("bus").child("1001").child("location").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                         latitude= dataSnapshot.child("latitude").getValue().toString();
                         longitude= dataSnapshot.child("longitude").getValue().toString();

                        String phoneNumber = "919447890307";
                        String msg = "BUS 1 BROKEN_DOWN AT LOCATION: Lat: "+latitude+" Long: "+longitude;
                        if(checkPermission(Manifest.permission.SEND_SMS)){
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(phoneNumber, null,msg,null,null);
                            Toast.makeText(sos_b1.this,"SOS Message Sent",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(sos_b1.this,"Permission denied",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });







                String phoneNumber = "919061325766";
                String msg = "BUS_BROKEN_DOWN AT LOCATION: Lat: "+latitude+" Long: "+longitude;
                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null,msg,null,null);
                    Toast.makeText(sos_b1.this,"SOS Message Sent",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(sos_b1.this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }






    private boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case SEND_SMS_PERMISSION_REQUEST_CODE :
                if (grantResults.length>0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    button_sos.setEnabled(false);
                }
                break;
        }
    }
}

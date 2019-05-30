package com.example.dbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reset_b1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_b1);

       Button reset=(Button)findViewById(R.id.reset_btn_b1);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child("bus").child("1001").child("route").child("01_Vytilla").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("02_Chambakkara").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("03_Petta").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("04_SN Junction").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("05_Irumbanam").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("06_Manakkapadi").child("count").setValue("0");
                myRef.child("bus").child("1001").child("route").child("07_HP Pump").child("count").setValue("0");

            }
        });


    }
}

package com.example.dbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reset_b2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_b2);

        Button reset=(Button)findViewById(R.id.reset_btn_b2);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child("bus").child("1002").child("route").child("01_Aluva").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("02_Pulinchodu").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("03_Companypadi").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("04_Ambattukavu").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("05_Muttom").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("06_Kalamassery").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("07_HMT Junction").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("08_Vallatol Nagar").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("09_BMC").child("count").setValue("0");
                myRef.child("bus").child("1002").child("route").child("10_Kakkanad").child("count").setValue("0");

            }
        });


    }
}

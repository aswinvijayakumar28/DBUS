package com.example.dbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addpassenger_b2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String busstop;
    Animation downtoup;
    long fee,oldfee,cap;
    String c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpassenger_b2);

        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        ImageView img=findViewById(R.id.addpimg2);
        final EditText user_id_txt=(EditText)findViewById(R.id.addpid2);

        Button add =(Button)findViewById(R.id.add_psg_b2);

        Spinner spinner =findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bus_2,R.layout.color_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        add.setAnimation(downtoup);
        spinner.setAnimation(downtoup);
        img.setAnimation(downtoup);
        user_id_txt.setAnimation(downtoup);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cap=(long)dataSnapshot.child("bus").child("1002").child("total passengers").getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cap < 35) {


                    final String TypedUsername = user_id_txt.getText().toString();

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (TypedUsername.length() != 0) {

                                if (dataSnapshot.child("users").child(TypedUsername).exists()) {

                                    fee = (long) dataSnapshot.child("bus").child("1002").child("route").child(busstop).child("fee").getValue();
                                    oldfee = (long) dataSnapshot.child("users").child(TypedUsername).child("fee").getValue();
                                    oldfee = oldfee + fee;
                                    myRef.child("users").child(TypedUsername).child("fee").setValue(oldfee);

                                    c = dataSnapshot.child("bus").child("1002").child("route").child(busstop).child("count").getValue().toString();
                                    int val = Integer.parseInt(c);
                                    val = val + 1;

                                    c = String.valueOf(val);
                                    myRef.child("bus").child("1002").child("route").child(busstop).child("count").setValue(c);

                                    Toast.makeText(addpassenger_b2.this, " Passenger Added! ", Toast.LENGTH_SHORT).show();
                                    Intent stop = new Intent(addpassenger_b2.this, bus1.class);
                                    startActivity(stop);

                                } else {
                                    Toast.makeText(addpassenger_b2.this, " Invalid Passenger ID", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(addpassenger_b2.this, "Enter Your Credentials", Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(addpassenger_b2.this, "BUS Capacity Full", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        busstop=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),busstop,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

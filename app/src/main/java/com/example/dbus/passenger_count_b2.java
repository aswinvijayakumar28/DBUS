package com.example.dbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class passenger_count_b2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    List list;
    RecyclerView recyclerview;
    Animation downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_count_b2);

        recyclerview = (RecyclerView) findViewById(R.id.rview2);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("bus").child("1002").child("route");

        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        recyclerview.setAnimation(downtoup);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Stopdetails stopdetails = dataSnapshot1.getValue(Stopdetails.class);
                    Listdata listdata = new Listdata();
                    String name=stopdetails.getName();
                    String count=stopdetails.getCount();
                    listdata.setName(name);
                    listdata.setCount(count);

                    list.add(listdata);


                }

                RecyclerviewAdapter recycler = new RecyclerviewAdapter(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(passenger_count_b2.this);
                recyclerview.setLayoutManager(layoutmanager);
                recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerview.setAdapter(recycler);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }
}

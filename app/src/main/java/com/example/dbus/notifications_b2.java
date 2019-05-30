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
public class notifications_b2 extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    List list;
    RecyclerView recyclerview;
    Animation downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_b2);

        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);

        recyclerview = (RecyclerView) findViewById(R.id.rview3);
        recyclerview.setAnimation(downtoup);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("notifications").child("driver_notifications");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Notificationdetails notificationdetails = dataSnapshot1.getValue(Notificationdetails.class);
                    Listdata listdata = new Listdata();
                    String content=notificationdetails.getContent();

                    listdata.setContent(content);


                    list.add(listdata);


                }

                RecyclerviewAdapter2 recycler = new RecyclerviewAdapter2(list);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(notifications_b2.this);
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

package com.example.dbus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class total_passengers_b2 extends AppCompatActivity {
    Animation lefttoright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_passengers_b2);
        lefttoright= AnimationUtils.loadAnimation(this,R.anim.lefttoright);

        final TextView tot_cap=(TextView)findViewById(R.id.tot_capb2);

        final TextView tot_psg=(TextView)findViewById(R.id.tot_psg_b2);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        tot_psg.setAnimation(lefttoright);
        tot_cap.setAnimation(lefttoright);


        myRef.child("bus").child("1002").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String t = dataSnapshot.child("total passengers").getValue().toString();
                tot_psg.setText(t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

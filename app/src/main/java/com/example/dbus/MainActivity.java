package com.example.dbus;

import android.content.Intent;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private Button fpswd;
    private ImageView drvimg;
    Animation uptodown,downtoup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);


        login= findViewById(R.id.login);
        fpswd= findViewById(R.id.fpswd);
        drvimg=findViewById(R.id.drvimg);


        final EditText username=(EditText)findViewById(R.id.username);
        final EditText password=(EditText)findViewById(R.id.password);
        final Intent intent= new Intent(MainActivity.this,activity2.class);

        drvimg.setAnimation(uptodown);
        login.setAnimation(downtoup);
        fpswd.setAnimation(downtoup);
        username.setAnimation(downtoup);
        password.setAnimation(downtoup);




        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String TypedUsername=username.getText().toString();
                final String TypedPassword=password.getText().toString();

                myRef.child("driver").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(TypedUsername.length()!=0) {
                            if (dataSnapshot.child(TypedUsername).exists()) {
                                if (TypedPassword.equals(dataSnapshot.child(TypedUsername).child("password").getValue().toString())) {
                                    final Intent intent= new Intent(MainActivity.this,activity2.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Wrong Driver ID or password ", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(MainActivity.this, " Invalid Driver ID or password ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Enter Your Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        fpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,change_password.class);
                startActivity(i);
            }
        });


    }
}

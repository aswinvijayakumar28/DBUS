package com.example.dbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class sq extends AppCompatActivity {
    private Button resetsq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq);


        resetsq=findViewById(R.id.resetsq);
        final EditText username=(EditText)findViewById(R.id.usernametv);
        final EditText password=(EditText)findViewById(R.id.passwordtv);
        final EditText securityquestion=(EditText)findViewById(R.id.securityquestiontv);
        final EditText securitytanswer=(EditText)findViewById(R.id.securityanswertv);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        resetsq.setOnClickListener(new View.OnClickListener() {
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

                                    final String TypedSecurityQuestion=securityquestion.getText().toString();
                                    final String TypedSecurityAnswer=securitytanswer.getText().toString();

                                    myRef.child("driver").child(TypedUsername).child("security question").setValue(TypedSecurityQuestion);
                                    myRef.child("driver").child(TypedUsername).child("security answer").setValue(TypedSecurityAnswer);

                                    Toast.makeText(sq.this, "Security Question reset successfully ", Toast.LENGTH_SHORT).show();
                                    final Intent intent= new Intent(sq.this,activity2.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(sq.this, "Wrong Driver ID or password ", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(sq.this, " Invalid Driver ID or password ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(sq.this, "Enter Your Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}

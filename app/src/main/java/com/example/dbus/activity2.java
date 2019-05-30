package com.example.dbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class activity2 extends AppCompatActivity implements View.OnClickListener{


    private CardView bus1,bus2,sq;
    Animation uptodown,downtoup,lefttoright,righttoleft;
    private TextView tv0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);


        bus1 = findViewById(R.id.bus1);
        bus2 = findViewById(R.id.bus2);
        sq = findViewById(R.id.sq);
        tv0=findViewById(R.id.tv0);

        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright=AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft=AnimationUtils.loadAnimation(this,R.anim.righttoleft);

        tv0.setAnimation(uptodown);
        bus1.setAnimation(lefttoright);
        bus2.setAnimation(righttoleft);
        sq.setAnimation(downtoup);


        bus1.setOnClickListener(this);
        bus2.setOnClickListener(this);
        sq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;

        switch (v.getId()){

            case R.id.bus1 : i= new Intent(this,bus1.class);startActivity(i); break;
            case R.id.bus2 : i= new Intent(this,bus2.class); startActivity(i);break;
            case R.id.sq : i= new Intent(this,sq.class);startActivity(i);break;
            default:break;

        }

    }
}

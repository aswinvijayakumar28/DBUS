package com.example.dbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


public class bus1 extends AppCompatActivity implements View.OnClickListener  {

    private TextView tv1;
    LinearLayout l1,l2,l3,l4,l5;


    private CardView psgcount_b1,notif_b1,totpsg_b1,link_b1,sos_b1,addpsg_b1;
    Animation uptodown,downtoup,lefttoright,righttoleft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus1);


        tv1=findViewById(R.id.tv1);


        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright=AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft=AnimationUtils.loadAnimation(this,R.anim.righttoleft);


        psgcount_b1 = findViewById(R.id.psgcount_b1);
        notif_b1 = findViewById(R.id.notif_b1);
        totpsg_b1 = findViewById(R.id.totpsg_b1);
        link_b1 = findViewById(R.id.link_b1);
        sos_b1 = findViewById(R.id.sos_b1);
        addpsg_b1=findViewById(R.id.addpsg_b1);


        tv1.setAnimation(uptodown);
        link_b1.setAnimation(righttoleft);
        sos_b1.setAnimation(lefttoright);
        psgcount_b1.setAnimation(lefttoright);
        notif_b1.setAnimation(lefttoright);
        totpsg_b1.setAnimation(righttoleft);
        addpsg_b1.setAnimation(lefttoright);





        psgcount_b1.setOnClickListener(this);
        notif_b1.setOnClickListener(this);
        totpsg_b1.setOnClickListener(this);
        link_b1.setOnClickListener(this);
        sos_b1.setOnClickListener(this);
        addpsg_b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()) {

            case R.id.psgcount_b1:
                i = new Intent(this, passenger_count_b1.class);
                startActivity(i);
                break;
            case R.id.notif_b1:
                i = new Intent(this, notifications_b1.class);
                startActivity(i);
                break;
            case R.id.totpsg_b1:
                i = new Intent(this, total_passengers_b1.class);
                startActivity(i);
                break;
            case R.id.link_b1:
                i = new Intent(this, reset_b1.class);
                startActivity(i);
                break;
            case R.id.sos_b1:
                i = new Intent(this, sos_b1.class);
                startActivity(i);
                break;
            case R.id.addpsg_b1:
                i = new Intent(this, addpassenger_b1.class);
                startActivity(i);
                break;
            default:
                break;


        }

    }
}

package com.example.dbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class bus2 extends AppCompatActivity implements View.OnClickListener {

    private TextView tv2;
    private CardView psgcount_b2,notif_b2,totpsg_b2,link_b2,sos_b2,addpsg_b2;
    Animation uptodown,downtoup,lefttoright,righttoleft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);

        tv2=findViewById(R.id.tv2);


        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lefttoright=AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        righttoleft=AnimationUtils.loadAnimation(this,R.anim.righttoleft);

        psgcount_b2 = findViewById(R.id.psgcount_b2);
        notif_b2 = findViewById(R.id.notif_b2);
        totpsg_b2 = findViewById(R.id.totpsg_b2);
        link_b2 = findViewById(R.id.link_b2);
        sos_b2 = findViewById(R.id.sos_b2);
        addpsg_b2=findViewById(R.id.addpsg_b2);



        tv2.setAnimation(uptodown);
        link_b2.setAnimation(righttoleft);
        sos_b2.setAnimation(lefttoright);
        addpsg_b2.setAnimation(lefttoright);
        psgcount_b2.setAnimation(lefttoright);
        notif_b2.setAnimation(lefttoright);
        totpsg_b2.setAnimation(righttoleft);


        psgcount_b2.setOnClickListener(this);
        notif_b2.setOnClickListener(this);
        totpsg_b2.setOnClickListener(this);
        link_b2.setOnClickListener(this);
        sos_b2.setOnClickListener(this);
        addpsg_b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.psgcount_b2:
                i = new Intent(this, passenger_count_b2.class);
                startActivity(i);
                break;
            case R.id.notif_b2:
                i = new Intent(this, notifications_b2.class);
                startActivity(i);
                break;
            case R.id.totpsg_b2:
                i = new Intent(this, total_passengers_b2.class);
                startActivity(i);
                break;
            case R.id.link_b2:
                i = new Intent(this, reset_b2.class);
                startActivity(i);
                break;
            case R.id.sos_b2:
                i = new Intent(this, sos_b2.class);
                startActivity(i);
                break;
            case R.id.addpsg_b2:
                i = new Intent(this, addpassenger_b2.class);
                startActivity(i);
                break;
            default:
                break;


        }
    }
}

package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int[] images;
    Timer timer = new Timer();
    TimerTask timertask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View wallview = findViewById(R.id.wallView2);
        Button wpButton = findViewById(R.id.button1);

        images = new  int[]{R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};


        wpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        View wallview = findViewById(R.id.wallView2);
                          int imglength= images.length;
                          Random random = new Random();
                          int rNum = random.nextInt(imglength);
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  wallview.setBackground(ContextCompat.getDrawable(getApplicationContext(),images[rNum]));
                              }
                          });



                    }
                },0,3000);


            }
        });



    }
}

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/a"
    android:id="@+id/wallView2"
    tools:context=".MainActivity">


    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="CHANGING WALLPAPER APPLICATION"
        android:layout_marginTop="200sp"
        android:textSize="19sp"
        android:textStyle="bold"
        android:textColor="@color/teal_700"
        android:layout_marginLeft="1sp"
        android:id="@+id/text1"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="350sp"
        android:text="CLICK HERE TO CHANGE WALLPAPER"
        android:textSize="15sp"
        android:background="@drawable/custom_button"
        android:layout_marginLeft="50sp"
        android:layout_marginRight="50sp"
        android:id="@+id/button1"/>
</RelativeLayout>

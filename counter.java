package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textCounter=findViewById(R.id.text_count);
        Button startButton = findViewById(R.id.b1);
        Button stopButton = findViewById(R.id.b2);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!stopButton.isPressed()){
                            try {
                                Thread.sleep(500);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            textCounter.post(new Runnable() {
                                @Override
                                public void run() {
                                    counter++;
                                    textCounter.setText(""+counter);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(500);
                    textCounter.setText(""+counter);
                }catch (Exception e){
                    e.printStackTrace();
                }
                onStop();
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
    tools:context=".MainActivity">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="COUNTER APPLICATION"
        android:textSize="30sp"
        android:layout_marginTop="100sp"
        android:gravity="center_horizontal"
        android:textAlignment="center"
        android:textStyle="bold"/>

    <TextView
        android:id="@+id/text_count"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="200sp"
        android:gravity="center_horizontal"
        android:text="Counter Value"
        android:textAlignment="center"
        android:textSize="30sp" />
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="350sp"
        android:text="START"
        android:textSize="20sp"
        android:padding="10sp"
        android:layout_centerHorizontal="true"
        android:layout_marginLeft="200sp"
        android:id="@+id/b1"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="450sp"
        android:text="STOP"
        android:textSize="20sp"
        android:padding="10sp"
        android:layout_centerHorizontal="true"
        android:layout_marginLeft="200sp"
        android:id="@+id/b2"/>
</RelativeLayout>

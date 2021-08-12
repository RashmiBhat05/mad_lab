package com.example.texttospeech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static android.media.AudioManager.STREAM_MUSIC;
public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.edit);
        b1=(Button)findViewById(R.id.speech);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    int result = t1.setLanguage(Locale.UK);
                    if(result == TextToSpeech.LANG_NOT_SUPPORTED||result == TextToSpeech.LANG_MISSING_DATA){
                        Toast.makeText(getApplicationContext(),"Language is not supported", Toast.LENGTH_SHORT).show();
                    }else{
                        b1.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View v) {
                                String tospeak = ed1.getText().toString();
                                speak_text(tospeak);
                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"INTI is failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void speak_text(String str){
        Bundle bundle = new Bundle();
        bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            t1.speak(str,TextToSpeech.QUEUE_FLUSH, bundle, null);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(t1!=null){
            t1.stop();
            t1.shutdown();
        }
    }

}


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:background="@drawable/b">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:text="TEXT TO SPEECH APPLICATION"
        android:layout_marginTop="130sp"
        android:textSize="30dp"
        android:textAlignment="center"
        android:gravity="center_horizontal"
        android:textStyle="bold"/>
    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter text here"
        android:layout_marginTop="280sp"
        android:layout_centerHorizontal="true"
        android:padding="15dp"
        android:textAlignment="center"
        android:gravity="center"
        android:textSize="20dp"
        android:id="@+id/edit"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="390sp"
        android:text="Convert text to speech"
        android:textSize="20dp"
        android:layout_marginLeft="30dp"
        android:layout_marginRight="30dp"
        android:background="@drawable/custom_button"
        android:id="@+id/speech"/>


</RelativeLayout>

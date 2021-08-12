package com.example.phonedialer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.phonedialer.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final int REQUEST_CALL=1;
    private EditText mEditPhoneNumber;
    private Button btnzero;
    private Button btnone;
    private Button btntwo;
    private Button btnthree;
    private Button btnfour;
    private Button btnfive;
    private Button btnsix;
    private Button btnseven;
    private Button btneight;
    private Button btnnine;
    private Button btnstar;
    private Button btnhas;
    private Button call;
    private Button btnclr;
    private Button save;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=(ActivityMainBinding) DataBindingUtil.setContentView(this,R.layout.activity_main);

        Button btnzero=findViewById(R.id.btn0);
        Button btnone=findViewById(R.id.btn1);
        Button btntwo=findViewById(R.id.btn2);
        Button btnthree=findViewById(R.id.btn3);
        Button btnfour=findViewById(R.id.btn4);
        Button btnfive=findViewById(R.id.btn5);
        Button btnsix=findViewById(R.id.btn6);
        Button btnseven=findViewById(R.id.btn7);
        Button btneight=findViewById(R.id.btn8);
        Button btnnine=findViewById(R.id.btn9);
        Button btnclr =findViewById(R.id.btndel);
        Button btnstar=findViewById(R.id.btnstar);
        Button btnhas=findViewById(R.id.btnhash);
        Button call=findViewById(R.id.btncall);
        Button save=findViewById(R.id.btnsave);

        EditText text=(EditText) findViewById(R.id.numtext);
        PhoneNumberUtils.formatNumber(text.getText().toString());

        binding.numtext.setShowSoftInputOnFocus(false);

        binding.btn0.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"0"));
        binding.btn1.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"1"));
        binding.btn2.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"2"));
        binding.btn3.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"3"));
        binding.btn4.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"4"));
        binding.btn5.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"5"));
        binding.btn6.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"6"));
        binding.btn7.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"7"));
        binding.btn8.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"8"));
        binding.btn9.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"9"));
        binding.btnstar.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"*"));
        binding.btnhash.setOnClickListener(v-> binding.numtext.setText(binding.numtext.getText()+"#"));

        binding.btncall.setOnClickListener(v->{
            if(binding.numtext.getText().length()>0){
                Toast.makeText(MainActivity.this,"Phone number is:"+binding.numtext.getText().toString(),Toast.LENGTH_SHORT).show();
                String phStr="tel:"+binding.numtext.getText().toString();
                makePhoneCall(phStr);
            }
            else{
                Toast.makeText(MainActivity.this,"Unable to call",Toast.LENGTH_SHORT).show();
            }
        });

        binding.btndel.setOnClickListener(v->{
            if(binding.numtext.getText().toString().length()>0){
                CharSequence currentText=binding.numtext.getText();
                binding.numtext.setText(currentText.subSequence(0,currentText.length()-1));
            }
            else{
                binding.numtext.setText(" ");
            }
        });

        binding.btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.numtext.getText().toString().isEmpty()){
                    String phnnum=binding.numtext.getText().toString();
                    Intent intent=new Intent(Intent.ACTION_INSERT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,phnnum);

                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No App to support", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void makePhoneCall(String phStr){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(phStr));
            startActivity(callIntent);
        }
    }
}


<?xml version="1.0" encoding="utf-8"?>
<layout>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/b"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="CALL AND SAVE APPLICATION"
        android:layout_marginTop="30sp"
        android:textSize="28dp"
        android:textAlignment="center"
        android:gravity="center_horizontal"
        android:textStyle="bold"/>
    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="100sp"
        android:layout_marginRight="160sp"
        android:layout_marginLeft="10sp"
        android:id="@+id/numtext"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="100sp"
        android:layout_marginLeft="250dp"
        android:layout_marginRight="50sp"
        android:text="DEL"
        android:textSize="25dp"
        android:background="@color/teal_700"
        android:id="@+id/btndel"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="180sp"
        android:layout_marginRight="100sp"
        android:layout_marginLeft="20sp"
        android:text="1"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btn1"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="180sp"
        android:layout_marginRight="130sp"
        android:layout_marginLeft="120sp"
        android:background="@color/teal_700"
        android:text="2"
        android:textSize="15sp"
        android:id="@+id/btn2"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="180sp"
        android:background="@color/teal_700"
        android:layout_marginRight="50sp"
        android:layout_marginLeft="220sp"
        android:text="3"
        android:textSize="15sp"
        android:id="@+id/btn3"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="250sp"
        android:layout_marginRight="100sp"
        android:layout_marginLeft="20sp"
        android:background="@color/teal_700"
        android:text="4"
        android:textSize="15sp"
        android:id="@+id/btn4"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="250sp"
        android:layout_marginRight="130sp"
        android:layout_marginLeft="120sp"
        android:text="5"
        android:background="@color/teal_700"
        android:textSize="15sp"
        android:id="@+id/btn5"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="250sp"
        android:layout_marginRight="50sp"
        android:layout_marginLeft="220sp"
        android:text="6"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btn6"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="320sp"
        android:layout_marginRight="100sp"
        android:layout_marginLeft="20sp"
        android:background="@color/teal_700"
        android:text="7"
        android:textSize="15sp"
        android:id="@+id/btn7"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="320sp"
        android:layout_marginRight="130sp"
        android:layout_marginLeft="120sp"
        android:text="8"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btn8"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="320sp"
        android:layout_marginRight="50sp"
        android:layout_marginLeft="220sp"
        android:text="9"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btn9"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="390sp"
        android:layout_marginRight="100sp"
        android:layout_marginLeft="20sp"
        android:text="*"
        android:background="@color/teal_700"
        android:textSize="15sp"
        android:id="@+id/btnstar"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="390sp"
        android:layout_marginRight="130sp"
        android:layout_marginLeft="120sp"
        android:text="0"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btn0"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="390sp"
        android:layout_marginRight="50sp"
        android:layout_marginLeft="220sp"
        android:text="#"
        android:textSize="15sp"
        android:background="@color/teal_700"
        android:id="@+id/btnhash"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="480sp"
        android:layout_marginLeft="20sp"
        android:text="CALL"
        android:width="120sp"
        android:layout_marginRight="90sp"
        android:background="@color/teal_700"
        android:id="@+id/btncall"/>
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="480sp"
        android:layout_marginLeft="160sp"
        android:text="SAVE"
        android:width="130sp"
        android:layout_marginRight="50sp"
        android:background="@color/teal_700"
        android:id="@+id/btnsave"/>








</RelativeLayout>
</layout>

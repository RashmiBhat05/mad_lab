Main activity.java

package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button SignUpButton = findViewById(R.id.signup);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText SignUpUserText = findViewById(R.id.user);
                EditText SignUpPasswordText = findViewById(R.id.pwd);
                String SignupUserName = SignUpUserText.getText().toString();
                String SignupPassword = SignUpPasswordText.getText().toString();
                if (ValidatePassword(SignupPassword)) {
                    Toast.makeText(MainActivity.this, "SIGNUP SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    intent.putExtra("username",SignupUserName);
                    intent.putExtra("password",SignupPassword);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean ValidatePassword(String passwd) {
        if (passwd.length() < 8) {
            Toast.makeText(this, "password must be more than 8 charecters in length.", Toast.LENGTH_SHORT).show();
            return false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!passwd.matches(upperCaseChars)) {
            Toast.makeText(this, "password must have one uppercase charecter", Toast.LENGTH_SHORT).show();
            return false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!passwd.matches(lowerCaseChars)) {
            Toast.makeText(this, "password must have one lowercase charecter", Toast.LENGTH_SHORT).show();
            return false;
        }
        String numbers = "(.*[0-9].*)";
        if (!passwd.matches(numbers)) {
            Toast.makeText(this, "password must have one number", Toast.LENGTH_SHORT).show();
            return false;
        }
        String specialChars = "(.*[@,$,#,%].*$)";
        if (!passwd.matches(specialChars)) {
            Toast.makeText(this, "password must have one special charecter", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

java   new.javaclass
login.java

package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText  signedUSER,signedpassword;
    private Button submit;
    private String user,pwd;
    private static int counter=0;
    private String loginusername,loginpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signedUSER=findViewById(R.id.signeduser);
        signedpassword=findViewById(R.id.signedpassword);
        submit=findViewById(R.id.login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getIntent().getExtras();
                user=bundle.getString("username");
                pwd=bundle.getString("password");
                loginusername=signedUSER.getText().toString().trim();
                loginpwd=signedpassword.getText().toString().trim();
                if(loginusername.equals(user)&&loginpwd.equals(pwd)){
                    Toast.makeText(login.this, "Login is Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    counter++;
                    if(counter==1){
                        Toast.makeText(login.this, "Login Failed!You have 1 attempt left!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(login.this, "Login Attempts exceeded", Toast.LENGTH_SHORT).show();
                        submit.setEnabled(false);
                    }
                }
            }
        });
    }
}

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:layout_editor_absoluteX="43dp"
        tools:layout_editor_absoluteY="163dp">

        <TextView
            android:id="@+id/textView2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#03A9F4"
            android:gravity="center"
            android:text="SIGNUP ACTIVITY"
            android:textColor="#B52323"
            android:textSize="35sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal"
            android:paddingLeft="10dp"
            android:paddingRight="10dp">

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:layout_weight="1"
                android:background="#F3958D"
                android:gravity="center"
                android:text="USERNAME"
                android:textSize="20sp" />

            <EditText
                android:id="@+id/user"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#A377FA"
                android:ems="10"
                android:inputType="textPersonName"
                android:textSize="18sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal"
            android:paddingLeft="10dp"
            android:paddingRight="10dp">

            <TextView
                android:id="@+id/textView4"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:layout_weight="1"
                android:background="#F3958D"
                android:gravity="center"
                android:text="PASSWORD"
                android:textSize="20sp" />

            <EditText
                android:id="@+id/pwd"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#AD91DD"
                android:ems="10"
                android:inputType="textPassword"
                android:textSize="18sp" />
        </LinearLayout>

        <Button
            android:id="@+id/signup"
            android:layout_width="116dp"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="20dp"
            android:text="SIGNUP" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>


Res.layout activity_login.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".login">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:layout_editor_absoluteX="43dp"
        tools:layout_editor_absoluteY="163dp">

        <TextView
            android:id="@+id/textView2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#03A9F4"
            android:gravity="center"
            android:text="LOGIN ACTIVITY"
            android:textColor="#B52323"
            android:textSize="45sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal"
            android:paddingLeft="10dp"
            android:paddingRight="10dp">

            <TextView
                android:id="@+id/textView3"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:layout_weight="1"
                android:background="#F3958D"
                android:gravity="center"
                android:text="USERNAME"
                android:textSize="25sp" />

            <EditText
                android:id="@+id/signeduser"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#AD91DD"
                android:ems="10"
                android:inputType="textPersonName"
                android:textSize="18sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal"
            android:paddingLeft="10dp"
            android:paddingRight="10dp">

            <TextView
                android:id="@+id/textView4"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginEnd="20dp"
                android:layout_marginRight="20dp"
                android:layout_weight="1"
                android:background="#F3958D"
                android:gravity="center"
                android:text="PASSWORD"
                android:textSize="25sp" />

            <EditText
                android:id="@+id/signedpassword"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#AD91DD"
                android:ems="10"
                android:inputType="textPassword"
                android:textSize="18sp" />
        </LinearLayout>

        <Button
            android:id="@+id/login"
            android:layout_width="116dp"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="20dp"
            android:text="Login" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>


androidManifest.xml
<application>
<activity android:name=".login"
    android:parentActivityName=".MainActivity"
    ></activity>
</application>







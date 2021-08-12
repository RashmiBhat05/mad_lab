
package com.example.simplecalculator;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.support.v7.app.AppCompatActivity;
import android.view.View;
import java.text.DecimalFormat;

//import com.sample.calc.dataBinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	//check
    private com.example.simplecalculator.databinding.ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

	//a+b = 0.0
    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;
	//format check
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");
	//check
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION; // 1+
                binding.result.setText(decimalFormat.format(valueOne) + "+");
                binding.editText.setText(null);
            }
        });



        binding.buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                binding.result.setText(decimalFormat.format(valueOne) + "-");
                binding.editText.setText(null);
            }
        });

        binding.buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                binding.result.setText(decimalFormat.format(valueOne) + "*");
                binding.editText.setText(null);
            }
        });

        binding.buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                binding.result.setText(decimalFormat.format(valueOne) + "/");
                binding.editText.setText(null);
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();	//1+2 = 3.0
                binding.result.setText(binding.result.getText().toString() +
                        decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
                }
                else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.editText.setText("");
                    binding.result.setText("");
                }
            }
        });
    }

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
        }
        else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e){}
        }
    }
}


<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">


<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#BED49800"
        android:orientation="vertical"
        android:paddingLeft="20dp"
        android:paddingRight="20sp"
        tools:layout_editor_absoluteX="0dp"
        tools:layout_editor_absoluteY="-16dp">

        <TextView
            android:id="@+id/result"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="50dp"
            android:background="#8C0A0A"
            android:ems="10"
            android:inputType="textPersonName"
            android:textColor="#E8F80B"
            android:textSize="30sp" />

        <EditText
            android:id="@+id/editText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="30dp"
            android:background="#950C0C"

            android:enabled="false"
            android:inputType="numberDecimal"
            android:textColor="#E6D10D"
            android:textSize="30sp" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal">

            <Button
                android:id="@+id/buttonSeven"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="7"
                android:textSize="24sp"
                app:backgroundTint="#F44336" />

            <Button
                android:id="@+id/buttonEight"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="8"
                android:textSize="24sp"
                app:backgroundTint="#5C13DF" />

            <Button
                android:id="@+id/buttonNine"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="9"
                android:textSize="24sp"
                app:backgroundTint="#CDDC39" />

            <Button
                android:id="@+id/buttonDiv"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="/"
                android:textSize="24sp"
                app:backgroundTint="#11248E" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal">

            <Button
                android:id="@+id/buttonFour"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="4"
                android:textSize="24sp"
                app:backgroundTint="#10E119" />

            <Button
                android:id="@+id/buttonFive"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="5"
                android:textSize="24sp"
                app:backgroundTint="#D316D3" />

            <Button
                android:id="@+id/buttonSix"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="6"
                android:textSize="24sp"
                app:backgroundTint="#313435" />

            <Button
                android:id="@+id/buttonMul"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="*"
                android:textSize="24sp"
                app:backgroundTint="#6A0505" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal">

            <Button
                android:id="@+id/buttonOne"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="1"
                android:textSize="24sp"
                app:backgroundTint="#FF5722" />

            <Button
                android:id="@+id/buttonTwo"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="@string/_2"
                android:textColorLink="#FBF5F5"
                android:textSize="24sp"
                app:backgroundTint="#C62A2A" />

            <Button
                android:id="@+id/buttonThree"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:background="#78BC19"
                android:text="3"
                android:textSize="24sp"
                app:backgroundTint="#21C8B9" />

            <Button
                android:id="@+id/buttonSub"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:background="#17DCC9"
                android:text="-"
                android:textSize="24sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginTop="20dp"
            android:orientation="horizontal">

            <Button
                android:id="@+id/buttonDot"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="."
                android:textColor="#E6DEDE"
                android:textSize="24sp"
                app:backgroundTint="#1B1C1A" />

            <Button
                android:id="@+id/buttonZero"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="0"
                android:textSize="24sp"
                app:backgroundTint="#46D120" />

            <Button
                android:id="@+id/buttonEqual"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="="
                android:textSize="24sp"
                app:backgroundTint="#C38B79" />

            <Button
                android:id="@+id/buttonAdd"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:layout_marginLeft="10dp"
                android:layout_marginEnd="10dp"
                android:layout_marginRight="10dp"
                android:layout_weight="1"
                android:text="+"
                android:textSize="24sp"
                app:backgroundTint="#DAC404" />
        </LinearLayout>

        <Button
            android:id="@+id/buttonClear"
            android:layout_width="74dp"
            android:layout_height="60dp"
            android:layout_gravity="right"
            android:layout_marginTop="20dp"
            android:layout_marginEnd="10dp"
            android:layout_marginRight="10dp"
            android:text="C"
            android:textColor="#E1D7D7"
            android:textSize="24sp"
            app:backgroundTint="#251F1F" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>

</layout>



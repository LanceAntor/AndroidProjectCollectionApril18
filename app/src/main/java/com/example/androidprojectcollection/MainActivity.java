package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;

    Button btn3;

    Button btn4;

    Button btn5;

    Button btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btnLayoutExercise);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, LayoutExercise.class
                );
                startActivity(intent1);
            }
        });
        btn2 = findViewById(R.id.btnButtonExercise);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, ButtonExercise.class
                );
                startActivity(intent1);
            }
        });
        btn3 = findViewById(R.id.btnCalculator);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, CalculatorExercise.class
                );
                startActivity(intent1);
            }
        });
        btn4 = findViewById(R.id.btnConnectExercise);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, ConnectExercise.class
                );
                startActivity(intent1);
            }
        });
        btn5 = findViewById(R.id.btnPassingIntents);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, PassingIntentsExercise.class
                );
                startActivity(intent1);
            }
        });

        btn5 = findViewById(R.id.btnMenu);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this, MenuExercise.class
                );
                startActivity(intent1);
            }
        });


    }
}
package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {

    Button closeBtn;
    Button toastBtn;
    Button changeBgBtn;
    int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    int currentColorIndex = 0;
    Button changeBtnCol;
    Button disappearBtn;
    ConstraintLayout rootView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        closeBtn = (Button) findViewById(R.id.btnClose);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        ButtonExercise.this, ReturnExcercise.class
                );
                startActivity(intent1);

            }
        });
        toastBtn = (Button) findViewById(R.id.btnToast);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonExercise.this, "This is Toast", Toast.LENGTH_SHORT).show();

            }
        });
        changeBgBtn = (Button) findViewById(R.id.btnChangeBg);
        rootView = findViewById(R.id.rootView);
        changeBgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootView.setBackgroundColor(colors[currentColorIndex]);
                currentColorIndex = (currentColorIndex + 1) % colors.length;

            }
        });
        changeBtnCol= (Button) findViewById(R.id.btnChangeCol);
        changeBtnCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnCol.setBackgroundColor(colors[currentColorIndex]);
                currentColorIndex = (currentColorIndex + 1) % colors.length;

            }
        });
        disappearBtn= (Button) findViewById(R.id.btnDisappear);
        disappearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disappearBtn.setVisibility(View.GONE);
            }
        });

    }

}
package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MapExercise extends AppCompatActivity {
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;

    ImageButton btn4;

    ImageButton btn5;

    TextView location;
    ImageView bg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_exercise);
        bg = (ImageView) findViewById(R.id.bg);
        location = (TextView) findViewById(R.id.location);

        btn1 = (ImageButton) findViewById(R.id.btnColesseum);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setImageResource(R.drawable.colesseum_view);
                location.setText("The Flavian Amphitheater");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.89042175039396, 12.492295973013146"));
                startActivity(intent);
            }
        });
        btn2 = (ImageButton) findViewById(R.id.btnGreateWall);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setImageResource(R.drawable.greawall_view);
                location.setText("The Great Wall of China");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:40.43216076024989, 116.57032123712976"));
                startActivity(intent);
            }
        });
        btn3 = (ImageButton) findViewById(R.id.btnChrist);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setImageResource(R.drawable.christ_view);
                location.setText("Christ Redeemer");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-22.951708510396085, -43.2104764744439"));
                startActivity(intent);
            }
        });

        btn4 = (ImageButton) findViewById(R.id.btnRice);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setImageResource(R.drawable.rice_view);
                location.setText("Banaue Rice Terraces");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:16.932209536036996, 121.05763642097958"));
                startActivity(intent);
            }
        });
        btn5 = (ImageButton) findViewById(R.id.btnMahal);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setImageResource(R.drawable.mahal_view);
                location.setText("Taj Mahal");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:27.175364300444436, 78.04218511196866"));
                startActivity(intent);
            }
        });

    }
}
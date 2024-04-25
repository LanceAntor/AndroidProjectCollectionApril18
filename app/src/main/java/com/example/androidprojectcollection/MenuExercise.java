package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MenuExercise extends AppCompatActivity {
    Button btnChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btnChanger = findViewById(R.id.btnTransformingButton);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menuexercise,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.mItemChange){
            Toast.makeText(this, "Edit Object Item is Clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.mItemReset){
            Toast.makeText(this, "Reset Object Item is Clicked", Toast.LENGTH_SHORT).show();
            int dimen = (int) getResources().getDimension(R.dimen.squaure_size_orig);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) btnChanger.getLayoutParams();
            layoutParams.width = dimen;
            layoutParams.height = dimen;
            btnChanger.setLayoutParams(layoutParams);
            btnChanger.setTextSize(24);
            btnChanger.setTextColor(Color.WHITE);
            btnChanger.setTypeface(null,Typeface.NORMAL);
            btnChanger.setBackgroundColor(Color.parseColor("#7F00FF"));
        } else if(item.getItemId() == R.id.mChangeBGColorToRed) {
            btnChanger.setBackgroundColor(Color.RED);
        } else if (item.getItemId() == R.id.mChangeTextSize) {
            btnChanger.setTextSize(50);
        } else if (item.getItemId() == R.id.mChangeTxtToGreen){
            btnChanger.setTextColor(Color.GREEN);
        } else if(item.getItemId() == R.id.mChangeFontWeightToItalic){
            btnChanger.setTypeface(null, Typeface.BOLD_ITALIC);
        } else if(item.getItemId() == R.id.mChangeSizeToSqure) {
            int dimen = (int) getResources().getDimension(R.dimen.squaure_size);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) btnChanger.getLayoutParams();
            layoutParams.width = dimen;
            layoutParams.height = dimen;
            btnChanger.setLayoutParams(layoutParams);


        }

        else if (item.getItemId() == R.id.mItemExit){
            finish();
        }
        return true;
    }
}
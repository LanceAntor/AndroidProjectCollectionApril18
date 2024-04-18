package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {

    TextView tFname, tLName, tGender, tPnum, temail, tBdate, tHS, tInterest, tPaddress, tFathersName, tMothersName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);

        tFname = findViewById(R.id.txtPI2FName);
        tLName = findViewById(R.id.txtPI2LName);
        tGender = findViewById(R.id.txtPI2Gender);
        tPnum = findViewById(R.id.txtPI2PhoneNumber);
        temail = findViewById(R.id.txtPI2EmailAddress);
        tBdate = findViewById(R.id.txtPI2Birthdate);
        tHS = findViewById(R.id.txtPI2HighSchool);
        tInterest = findViewById(R.id.txtPI2Interest);
        tPaddress = findViewById(R.id.txtPI2PresentAddress);
        tFathersName = findViewById(R.id.txtPI2FathersName);
        tMothersName = findViewById(R.id.txtPI2MothersName);

        Intent intent = getIntent();

        String fname = intent.getStringExtra("fname_key");
        String lname = intent.getStringExtra("lname_key");
        String gender = intent.getStringExtra("gender_key");
        String pnum = intent.getStringExtra("pnum_key");
        String email = intent.getStringExtra("eadd_key");
        String bdate = intent.getStringExtra("bdate_key");
        String HS = intent.getStringExtra("highschool_key");
        String interest = intent.getStringExtra("interest_key");
        String paddress = intent.getStringExtra("padd_key");
        String father = intent.getStringExtra("fathers_key");
        String mother = intent.getStringExtra("mothers_key");


        tFname.setText(fname);
        tLName.setText(lname);
        tGender.setText(gender);
        tPnum.setText(pnum);
        temail.setText(email);
        tBdate.setText(bdate);
        tHS.setText(HS);
        tInterest.setText(interest);
        tPaddress.setText(paddress);
        tFathersName.setText(father);
        tMothersName.setText(mother);
    }

}
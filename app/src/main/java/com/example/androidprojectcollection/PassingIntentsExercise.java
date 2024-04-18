package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class PassingIntentsExercise extends AppCompatActivity {

    EditText eFName, eLName , eBDate, eNum, eMail, ePAddress, eHS, eInterest, eFather, eMother;

    Button eSubmit, eClear;

    RadioButton rMale, rFem, rOth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        eSubmit = (Button) findViewById(R.id.btnSubmit);
        eSubmit.setOnClickListener(this::onClick);
        eClear = (Button) findViewById(R.id.btnClear);

        eFName = (EditText) findViewById(R.id.etxtFName);
        eLName = (EditText) findViewById(R.id.etxtLName);
        eBDate = (EditText) findViewById(R.id.etxtBDate);
        eNum = (EditText) findViewById(R.id.etxtPNumber);
        eMail = (EditText) findViewById(R.id.etxtEmailAdd);
        ePAddress = (EditText) findViewById(R.id.etxtPAddress);
        eHS = (EditText) findViewById(R.id.etxtHSchool);
        eInterest = (EditText) findViewById(R.id.etxtInterest);
        eFather = (EditText) findViewById(R.id. etxtFathersName);
        eMother = (EditText) findViewById(R.id.etxtMothersName);


        rMale = (RadioButton) findViewById(R.id.radMale);
        rFem = (RadioButton) findViewById(R.id.radFemale);
        rOth = (RadioButton) findViewById(R.id.radOthers);
        eClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }
    public void onClick(View v){
        String fName = eFName.getText().toString();
        String lName = eLName.getText().toString();

        String gender;
        if(rMale.isChecked())
        {
            gender = "Male";
        } else if(rFem.isChecked()){
            gender = "Female";
        } else if(rOth.isChecked()){
            gender = "Others";
        } else {
            gender = "Uknown";
        }

        String bDate = eBDate.getText().toString();
        String pNumber = eNum.getText().toString();
        String emailAdd = eMail.getText().toString();
        String presentAdd = ePAddress.getText().toString();
        String highSchool = eHS.getText().toString();
        String interest = eInterest.getText().toString();
        String fathersName = eFather.getText().toString();
        String mothersName = eMother.getText().toString();


        Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
        intent.putExtra("fname_key", fName);
        intent.putExtra("gender_key", gender);
        intent.putExtra("pnum_key", pNumber);
        intent.putExtra("lname_key", lName);
        intent.putExtra("bdate_key", bDate);
        intent.putExtra("eadd_key", emailAdd);
        intent.putExtra("padd_key",presentAdd);
        intent.putExtra("highschool_key", highSchool);
        intent.putExtra("interest_key", interest);
        intent.putExtra("fathers_key", fathersName);
        intent.putExtra("mothers_key", mothersName);
        startActivity(intent);
    }
    public void reset(){
        eFName.setText("");
        eLName.setText("");
        eBDate.setText("");
        eMail.setText("");
        eInterest.setText("");
        ePAddress.setText("");
        eNum.setText("");
        eHS.setText("");
        eMother.setText("");
        eFather.setText("");
        rMale.setChecked(false);
        rFem.setChecked(false);
        rOth.setChecked(false);
    }
}
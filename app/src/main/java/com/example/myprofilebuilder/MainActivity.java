package com.example.myprofilebuilder;

/*
*
* MainActivity.class
* Group 19
* In class 03
* Mayuri Jain, Narendra Pahuja
*
* */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String TAG_IMAGE = "photoProf";

    RadioGroup rg_gender;
    RadioButton rd_male;
    RadioButton rd_female;
    ImageView iv_gender;
    EditText et_firstName;
    EditText et_lastName;
    Button bt_save;
    String flag_image = "";
    boolean radioChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_gender = (RadioGroup) findViewById(R.id.gender_rdgrp);
        rd_male = (RadioButton) findViewById(R.id.rd_male);
        rd_female = (RadioButton) findViewById(R.id.rd_female);
        iv_gender = (ImageView) findViewById(R.id.im_gender);
        bt_save = (Button) findViewById(R.id.saveButton);
        et_firstName = (EditText) findViewById(R.id.firstName);
        et_lastName = (EditText) findViewById(R.id.lastName);


        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(radioGroup.getCheckedRadioButtonId()){

                    case R.id.rd_female:
                        iv_gender.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image = "Female";
                        radioChecked = true;
                        break;
                    case R.id.rd_male:
                        iv_gender.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image = "Male";
                        radioChecked = true;
                        break;
                    default:
                        break;
                }

            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                boolean isError = false;

                if(firstName.isEmpty()){
                    isError=true;
                    et_firstName.setError("Invalid Input");
                }
                if(lastName.isEmpty()){
                    isError=true;
                    et_lastName.setError("Invalid Input");
                }
                if(!radioChecked){
                    isError=true;
                    Toast.makeText(MainActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
                }


                if(!isError){
                    Intent intent = new Intent(MainActivity.this, displayActivity.class);
                    User user = new User(flag_image,firstName,lastName);

                    Bundle sentData = new Bundle();
                    sentData.putSerializable("user", user);
                    intent.putExtra(TAG_IMAGE, sentData);
                    startActivity(intent);
                    finish();
                }

            }
        });




    }
}

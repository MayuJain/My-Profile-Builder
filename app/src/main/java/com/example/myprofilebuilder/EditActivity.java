package com.example.myprofilebuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    public static final String TAG_IMAGE = "sendData";

    RadioGroup rg_gender_edit;
    RadioButton rd_male_edit;
    RadioButton rd_female_edit;
    ImageView iv_gender_edit;
    EditText et_firstName_edit;
    EditText et_lastName_edit;
    Button bt_save_edit;
    String flag_image="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        rg_gender_edit = (RadioGroup) findViewById(R.id.gender_rdgrp_edit);
        rd_male_edit = (RadioButton) findViewById(R.id.rd_male_edit);
        rd_female_edit = (RadioButton) findViewById(R.id.rd_female_edit);
        iv_gender_edit = (ImageView) findViewById(R.id.im_gender_edit);
        bt_save_edit = (Button) findViewById(R.id.saveButton_edit);
        et_firstName_edit = (EditText) findViewById(R.id.firstName_edit);
        et_lastName_edit = (EditText) findViewById(R.id.lastName_edit);

        final Bundle extrasFromMain = getIntent().getExtras().getBundle("toEdit");
        User user = (User) extrasFromMain.getSerializable("bundleEdit");
        flag_image = user.getGender();
        if(user != null){
            if(user.getGender().equals("Male")){
                rd_male_edit.setChecked(true);
                iv_gender_edit.setImageDrawable(getDrawable(R.drawable.male));
            }else{
                rd_female_edit.setChecked(true);
                iv_gender_edit.setImageDrawable(getDrawable(R.drawable.female));
            }

            et_firstName_edit.setText(user.getFirstName());
            et_lastName_edit.setText(user.getLastName());
        }

        rg_gender_edit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(radioGroup.getCheckedRadioButtonId()){

                    case R.id.rd_female_edit:
                        iv_gender_edit.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image = "Female";
                        break;
                    case R.id.rd_male_edit:
                        iv_gender_edit.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image = "Male";
                        break;
                    default:
                        break;
                }
            }
        });

        bt_save_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = et_firstName_edit.getText().toString();
                String lastName = et_lastName_edit.getText().toString();
                boolean isError = false;

                if(firstName.isEmpty()){
                    isError=true;
                    et_firstName_edit.setError("Invalid Input");
                }
                if(lastName.isEmpty()){
                    isError=true;
                    et_lastName_edit.setError("Invalid Input");
                }

                if(!isError){
                    User user = new User(flag_image,firstName,lastName);

                    Bundle sentData = new Bundle();
                    sentData.putSerializable("usertoDisplay", user);
                    Intent intent = new Intent(EditActivity.this, displayActivity.class);
                    intent.putExtra(EditActivity.TAG_IMAGE, sentData);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}

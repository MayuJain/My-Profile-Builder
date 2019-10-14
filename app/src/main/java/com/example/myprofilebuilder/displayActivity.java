package com.example.myprofilebuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class displayActivity extends AppCompatActivity {

    private static final int REQ_CODE = 5;
    private ImageView iv_profile;
    private Button bt_edit;
    TextView tv_displayName;
    TextView tv_displayGender;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        iv_profile = findViewById(R.id.im_displayImage);
        bt_edit = findViewById(R.id.bt_Edit);
        tv_displayName = findViewById(R.id.tv_name);
        tv_displayGender = findViewById(R.id.tv_gender);

        final Bundle extrasFromMain = getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
        user = (User) extrasFromMain.getSerializable("user");

        //String flagGender = extrasFromMain.getString(MainActivity.TAG_IMAGE);

        if(user != null){
        Log.d("demo",user.toString());
            if(user.getGender().equals("Male")){
                iv_profile.setImageDrawable(getDrawable(R.drawable.male));
            }else{
                iv_profile.setImageDrawable(getDrawable(R.drawable.female));
            }

            tv_displayName.setText("Name : " + user.getFirstName() + " " + user.getLastName());
            tv_displayGender.setText(user.getGender());
        }

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(displayActivity.this,EditActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("bundleEdit", user);

                toEdit.putExtra("toEdit", bundle);
                startActivityForResult(toEdit,REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){

                final Bundle extrasFromMain = data.getExtras().getBundle(EditActivity.TAG_IMAGE);
                User user1 = (User) extrasFromMain.getSerializable("usertoDisplay");

                Log.d("demo in result", user1.toString());
                tv_displayName.setText("Name : " + user1.getFirstName() + " " + user1.getLastName());
                tv_displayGender.setText(user1.getGender());
                if(user1.getGender().equals("Male")){
                    iv_profile.setImageDrawable(getDrawable(R.drawable.male));
                }else{
                    iv_profile.setImageDrawable(getDrawable(R.drawable.female));
                }
                user.setFirstName(user1.getFirstName());
                user.setLastName(user1.getLastName());
                user.setGender(user1.getGender());
            }
        }
    }
}

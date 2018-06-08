package com.vmhin.bookingapp.ui.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Toast;

import com.vmhin.bookingapp.R;
import com.vmhin.bookingapp.ui.db.DBHelper;
import com.vmhin.bookingapp.ui.models.UserDetails;

public class SettingsActivity extends AppCompatActivity {

    AppCompatButton cancelBtn, saveBtn;

    AppCompatEditText nameText, idText, emailText, commentsText;

    AppCompatRadioButton maleGender, femaleGender;

    DBHelper dbHelper;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dbHelper = new DBHelper(this);

        saveBtn = (AppCompatButton) findViewById(R.id.saveBtn);
        cancelBtn = (AppCompatButton) findViewById(R.id.cancelBtn);

        nameText = (AppCompatEditText) findViewById(R.id.nameText);
        idText = (AppCompatEditText) findViewById(R.id.idText);
        emailText = (AppCompatEditText) findViewById(R.id.emailText);
        commentsText = (AppCompatEditText) findViewById(R.id.commentsText);

        maleGender = (AppCompatRadioButton) findViewById(R.id.maleGender);
        femaleGender = (AppCompatRadioButton) findViewById(R.id.femaleGender);

        if(dbHelper.getAllUsers().size() > 0){
            userDetails  = dbHelper.getAllUsers().get(0);
            if(userDetails != null){
                nameText.setText(userDetails.getName());
                idText.setText(userDetails.getUserID());
                emailText.setText(userDetails.getEmail());
                commentsText.setText(userDetails.getComment());

                if(userDetails.getGender().equals("Male")){
                    maleGender.setChecked(true);
                }else {
                    femaleGender.setChecked(true);
                }
            }
        }else {
            nameText.setText("John");
            idText.setText("12345");
            emailText.setText("abc@gmail.com");
            commentsText.setText("Comments");
            maleGender.setChecked(true);
        }


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String id = idText.getText().toString().trim();
                String email = emailText.getText().toString().trim();
                String comments = commentsText.getText().toString().trim();

                String gender = (maleGender.isChecked()) ? "Male" : "";

                if(gender.equals("")){
                    gender = (femaleGender.isChecked()) ? "Female" : "";
                }

                if(name.length() <= 0 || id.length() <= 0 || email.length() <= 0 || comments.length() <= 0 || gender.length() <= 0){
                    Toast.makeText(SettingsActivity.this, "Please enter the all fields", Toast.LENGTH_LONG).show();
                }else {
                    if(userDetails != null){
                        dbHelper.updateUser(Integer.parseInt(userDetails.getId()), name, email, gender, comments, id);
                        Toast.makeText(SettingsActivity.this, "User field updated successfully.", Toast.LENGTH_LONG).show();
                    }else {
                        dbHelper.insertUser(name, email, gender, comments, id);
                        Toast.makeText(SettingsActivity.this, "User field inserted successfully.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SettingsActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

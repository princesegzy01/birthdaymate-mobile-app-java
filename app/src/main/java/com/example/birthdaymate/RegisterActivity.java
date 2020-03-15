package com.example.birthdaymate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);
    }

    public void gotoLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void  Register(View view){

        EditText nameText = findViewById(R.id.name);
        EditText dobText = findViewById(R.id.dob);
        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);

        if( nameText.getText().toString().isEmpty()){
            @SuppressLint("WrongConstant")
            Toast t = Toast.makeText(this, "Name field cannot be empty", 3);
            t.show();
            return;
        }

        if( dobText.getText().toString().isEmpty()){
            @SuppressLint("WrongConstant")
            Toast t = Toast.makeText(this, "Date of birth field cannot be empty", 3);
            t.show();
            return;
        }

        if( emailText.getText().toString().isEmpty()){
            @SuppressLint("WrongConstant")
            Toast t = Toast.makeText(this, "Email field cannot be empty", 3);
            t.show();
            return;
        }

        if( passwordText.getText().toString().isEmpty()){
            @SuppressLint("WrongConstant")
            Toast t = Toast.makeText(this, "Password field cannot be empty", 3);
            t.show();
            return;
        }

    }
}

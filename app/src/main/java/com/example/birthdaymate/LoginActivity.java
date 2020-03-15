package com.example.birthdaymate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
    }

    public void Login (View view){
        EditText username = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        if(usernameString.isEmpty()){
            Toast t = Toast.makeText(this, "Email field cannot be empty", 3);
            t.show();
            return;
        }

        if(passwordString.isEmpty()){
            Toast t = Toast.makeText(this, "Password field cannot be empty", 3);
            t.show();
            return;
        }
    }
}

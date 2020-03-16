package com.example.birthdaymate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidNetworking.initialize(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);

        simpleProgressBar = findViewById(R.id.progressBar);
        simpleProgressBar.setVisibility(View.INVISIBLE);
    }

    public void gotoLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

//    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void  Register(View view){



        EditText nameText = findViewById(R.id.name);
        EditText dobText = findViewById(R.id.dob);
        EditText emailText = findViewById(R.id.email);
        EditText passwordText = findViewById(R.id.password);

        if( nameText.getText().toString().isEmpty()){
            @SuppressLint("WrongConstant")
            Toast t = Toast.makeText(this, "userName field cannot be empty", 3);
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

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", nameText.getText().toString().trim());
            jsonObject.put("email", emailText.getText().toString().trim());
            jsonObject.put("dob", dobText.getText().toString().trim());
            jsonObject.put("password", passwordText.getText().toString().trim());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        simpleProgressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post("http://192.168.43.103:9000/api/account/register")
        .addJSONObjectBody(jsonObject) // posting json
        .setTag("test")
        .setPriority(Priority.IMMEDIATE)
        .build()
        .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                // do anything with response
                simpleProgressBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
                alertDialogBuilder.setMessage("Account registration was successful");
                alertDialogBuilder.setNeutralButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(RegisterActivity.this,"Registration successful",3).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

            @Override
            public void onError(ANError anError) {

                Gson g = new Gson();
                final ErrorClass err = g.fromJson(anError.getErrorBody().toString(), ErrorClass.class);
                Log.e("errorRest", err.msg);

                simpleProgressBar.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterActivity.this);
                alertDialogBuilder.setMessage(err.msg);
                alertDialogBuilder.setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(RegisterActivity.this,err.msg,3).show();
                                return;
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();




            }
        });



    }
}

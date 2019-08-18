package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LandingActivity extends AppCompatActivity {
    public static final String LOGIN_MESSAGE = "com.budgetapp.LOGIN";
    public static final String FIRST_LOGIN_USER = "com.budgetapp.FIRST_LOGIN_USER";
    public static final String FIRST_LOGIN_PWD = "com.budgetapp.FIRST_LOGIN_PASSWORD";
    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        usernameField = findViewById(R.id.appUsernameInput);
        passwordField = findViewById(R.id.appPasswordInput);
    }

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, FirstLoginActivity.class);
        intent.putExtra(FIRST_LOGIN_USER, usernameField.getText().toString());
        intent.putExtra(FIRST_LOGIN_PWD, passwordField.getText().toString());
        startActivity(intent);
    }
}

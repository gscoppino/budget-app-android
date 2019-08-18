package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstLoginActivity extends AppCompatActivity {
    private EditText salaryInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        salaryInput = findViewById(R.id.appMonthlySalaryInput);

        Intent intent = getIntent();
        // TODO: The data from the intent will be used in registering a user.
    }

    public void onClickBegin(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}

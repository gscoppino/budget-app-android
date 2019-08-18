package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.budgetapp.dummy.DummyContent;

public class HomeActivity extends AppCompatActivity implements ExpenseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void onClickFAB(View view) {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        startActivity(intent);
    }
}

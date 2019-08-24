package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.budgetapp.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements
        LandingFragment.OnFragmentInteractionListener,
        FirstLoginFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        AddExpenseFragment.OnFragmentInteractionListener,
        ExpenseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_landingFragment_to_homeFragment);
    }

    public void onRegister(View view) {
        Navigation.findNavController(view).navigate(R.id.action_landingFragment_to_firstLoginFragment);
    }

    public void onBegin(View view) {
        Navigation.findNavController(view).navigate(R.id.action_firstLoginFragment_to_homeFragment);
    }

    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void onAddExpense(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addExpenseFragment2);
    }

    public void onSubmitExpense(View view) {
        Navigation.findNavController(view).navigate(R.id.action_addExpenseFragment_to_homeFragment2);
    }
}

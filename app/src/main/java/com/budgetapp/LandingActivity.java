package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener {

    public static final String LOGIN_USER_ID_KEY = "com.budgetapp.LOGIN_USER_ID";
    public static final String LOGIN_USER_USERNAME_KEY = "com.budgetapp.LOGIN_USER_USERNAME_KEY";
    public static final String LOGIN_USER_SALARY_KEY = "com.budgetapp.LOGIN_USER_SALARY_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);
    }

    public void onLogin(View view, final int userId, final String userUsername, final int userMonthlySalary) {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(LOGIN_USER_ID_KEY, userId);
        intent.putExtra(LOGIN_USER_SALARY_KEY, userMonthlySalary);
        intent.putExtra(LOGIN_USER_USERNAME_KEY, userUsername);
        startActivity(intent);
    }

    public void onRegister(View view, final String username, final String password) {
        Bundle bundle = new Bundle();
        bundle.putString(RegistrationFragment.USER_USERNAME, username);
        bundle.putString(RegistrationFragment.USER_PASSWORD, password);

        Navigation.findNavController(view).navigate(
                R.id.action_loginFragment_to_registrationFragment,
                bundle);
    }
}

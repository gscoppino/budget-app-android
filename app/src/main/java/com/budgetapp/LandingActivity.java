package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener {

    public static final String LOGIN_USER_ID_KEY = "com.budgetapp.LOGIN_USER_ID";
    private String usernameForRegistration;
    private String passwordForRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);
    }

    public void onLogin(View view, final String username, final String password) {
        final Intent intent = new Intent(this, MainActivity.class);
        ApiServiceSingleton.getInstance().userService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                boolean foundUser = false;
                int userId = -1;
                for (User user : response.body()) {
                    if (user.getUsername().equals(username)) {
                        foundUser = true;
                        userId = user.getId();
                        break;
                    }
                }

                if (foundUser) {
                    intent.putExtra(LOGIN_USER_ID_KEY, userId);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    public void onRegister(View view, final String username, final String password) {
        this.usernameForRegistration = username;
        this.passwordForRegistration = password;
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
    }

    public void onBegin(View view, final int monthlySalary) {
        final Intent intent = new Intent(this, MainActivity.class);
        NewUserPayload newUserPayload = new NewUserPayload();
        newUserPayload.setUsername(this.usernameForRegistration);
        newUserPayload.setSalary(monthlySalary);
        ApiServiceSingleton.getInstance().userService.createUser(newUserPayload).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                intent.putExtra(LOGIN_USER_ID_KEY, response.body().getId());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}

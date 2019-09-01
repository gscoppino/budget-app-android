package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LandingActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);
    }

    public void onLogin(View view, final String username, final String password) {
        final Intent intent = new Intent(this, MainActivity.class);
        APIServiceSingleton.getInstance(this).addToRequestQueue(new JsonArrayRequest(
                Request.Method.GET,
                "https://10.0.2.2:8080/api/users",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do things
                        boolean foundUser = false;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                if (user.getString("username").equals(username)) {
                                    foundUser = true;
                                    break;
                                }
                            } catch (JSONException je) {
                                // meh
                            }
                        }

                        if (foundUser) {
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        // do things
                    }
                }));
    }

    public void onRegister(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
    }

    public void onBegin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

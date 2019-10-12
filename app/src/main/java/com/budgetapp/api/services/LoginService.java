package com.budgetapp.api.services;


import com.budgetapp.api.models.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    @Headers({
            "Content-Type: application/json"
    })
    Call<Void> authenticateUser(@Body UserCredentials userCredentials);
}

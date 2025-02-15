package com.budgetapp.api.services;

import com.budgetapp.api.models.NewUserPayload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("users")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Void> createUser(@Body NewUserPayload newUserPayload);
}

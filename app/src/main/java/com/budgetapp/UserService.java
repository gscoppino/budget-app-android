package com.budgetapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @GET("users")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<List<User>> getUsers();

    @POST("users")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<User> createUser(@Body NewUserPayload newUserPayload);
}

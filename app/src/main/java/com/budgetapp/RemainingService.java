package com.budgetapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RemainingService {
    @GET("remaining/{userId}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Remaining> getRemaining(@Path("userId") int userId);
}

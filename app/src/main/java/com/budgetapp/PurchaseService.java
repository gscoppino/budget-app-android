package com.budgetapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseService {
    @POST("purchases/{userId}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Purchase> createPurchase(@Path ("userId") int userId,
                                  @Body NewPurchasePayload newPurchasePayload);
}

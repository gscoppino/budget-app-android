package com.budgetapp.api.services;

import com.budgetapp.api.models.NewPurchasePayload;
import com.budgetapp.api.models.Purchase;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PurchaseService {
    @POST("purchases")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Purchase> createPurchase(@Body NewPurchasePayload newPurchasePayload);
}

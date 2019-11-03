package com.budgetapp.api.services;

import com.budgetapp.api.models.BudgetMonth;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BudgetService {
    @GET("budget")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<BudgetMonth> getOverviewForMonth(@Query("year") String year,
                                          @Query("month") String month);
}

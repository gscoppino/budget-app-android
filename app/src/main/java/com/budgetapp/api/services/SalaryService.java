package com.budgetapp.api.services;

import com.budgetapp.api.models.UpdateSalaryPayload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

public interface SalaryService {
    @PUT("salary")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Void> updateSalary(@Body UpdateSalaryPayload updateSalaryPayload);
}

package com.budgetapp.api;

import com.budgetapp.api.services.BudgetService;
import com.budgetapp.api.services.LoginService;
import com.budgetapp.api.services.PurchaseService;
import com.budgetapp.api.services.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiServiceSingleton {
    private static ApiServiceSingleton instance;
    private Retrofit retrofitInstance;
    public LoginService loginService;
    public UserService userService;
    public BudgetService budgetService;
    public PurchaseService purchaseService;

    private ApiServiceSingleton() {
        retrofitInstance = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:8080/api/")
                .client(ApiOkHttpClient.getApiOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        loginService = retrofitInstance.create(LoginService.class);
        userService = retrofitInstance.create(UserService.class);
        budgetService = retrofitInstance.create(BudgetService.class);
        purchaseService = retrofitInstance.create(PurchaseService.class);
    }

    public static synchronized ApiServiceSingleton getInstance() {
        if (instance == null) {
            instance = new ApiServiceSingleton();
        }

        return instance;
    }
}

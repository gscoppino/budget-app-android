package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BudgetMonth {
    @JsonProperty("purchases")
    private List<Purchase> purchaseList;

    @JsonProperty("sum")
    private int sum;

    @JsonProperty("purchases")
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    @JsonProperty("sum")
    public int getSum() {
        return sum;
    }
}

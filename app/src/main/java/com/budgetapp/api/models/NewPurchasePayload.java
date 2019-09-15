package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPurchasePayload {
    @JsonProperty("cost")
    private int cost;

    @JsonProperty("cost")
    public int getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(int cost) {
        this.cost = cost;
    }
}

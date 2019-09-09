package com.budgetapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Purchase {
    @JsonProperty("id")
    private int id;

    @JsonProperty("userid")
    private int userId;

    @JsonProperty("purchasedate")
    private String purchaseDate;

    @JsonProperty("cost")
    private int cost;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("userid")
    public int getUserId() {
        return userId;
    }

    @JsonProperty("purchasedate")
    public String getPurchaseDate() {
        return purchaseDate;
    }

    @JsonProperty("cost")
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "" + purchaseDate + ": $" + cost;
    }
}

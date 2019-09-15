package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("id")
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("monthlysalary")
    private int monthlySalary;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("monthlysalary")
    public int getMonthlySalary() {
        return monthlySalary;
    }
}

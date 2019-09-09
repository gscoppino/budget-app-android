package com.budgetapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUserPayload {
    @JsonProperty("username")
    private String username;

    @JsonProperty("salary")
    private int salary;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("salary")
    public int getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(int salary) {
        this.salary = salary;
    }
}

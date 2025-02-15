package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewUserPayload {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String pw;

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

    @JsonProperty("pw")
    public String getPw() { return pw; }

    @JsonProperty("pw")
    public void setPw(String pw) { this.pw = pw; }

    @JsonProperty("salary")
    public int getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(int salary) {
        this.salary = salary;
    }
}

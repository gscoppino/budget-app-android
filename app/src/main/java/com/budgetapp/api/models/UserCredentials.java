package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentials {
    @JsonProperty("username")
    private String username;

    @JsonProperty("pw")
    private String pw;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("pw")
    public String getPw() {
        return pw;
    }

    @JsonProperty("pw")
    public void setPw(String pw) {
        this.pw = pw;
    }
}

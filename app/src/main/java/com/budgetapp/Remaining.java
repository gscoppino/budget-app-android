package com.budgetapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Remaining {
    @JsonProperty("remaining")
    private int remaining;

    @JsonProperty("remaining")
    public int getRemaining() {
        return remaining;
    }
}

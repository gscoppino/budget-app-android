package com.budgetapp.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateSalaryPayload {
    @JsonProperty("salary")
    private int salary;

    @JsonProperty("salary")
    public int getSalary() { return salary; }

    @JsonProperty("salary")
    public void setSalary(int salary) { this.salary = salary; }
}

package com.example.rqchallenge.employees;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseID {

    private String status;

    public String getStatus() {
        return status;
    }

    public Employee getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    private Employee data;
    private String message;

    // Getters and setters
}
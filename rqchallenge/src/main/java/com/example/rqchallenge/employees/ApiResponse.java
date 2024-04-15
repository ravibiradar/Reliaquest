package com.example.rqchallenge.employees;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    private String status;

    private List<Employee> data;
    private String message;

    public String getStatus() {
        return status;
    }

    public List<Employee> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
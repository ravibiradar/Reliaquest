package com.example.rqchallenge.service;

import com.example.rqchallenge.employees.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    ResponseEntity<List<Employee>> getAllEmployees();
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id);

    ResponseEntity<Integer> getHighestSalaryOfEmployees();

    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames();

    ResponseEntity<Employee> createEmployee(@RequestBody Map<String, Object> employeeInput);

    ResponseEntity<String> deleteEmployeeById(String id);

    ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString);
}

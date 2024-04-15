package com.example.rqchallenge.service;

import com.example.rqchallenge.employees.ApiResponse;
import com.example.rqchallenge.employees.ApiResponseID;
import com.example.rqchallenge.employees.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private RestTemplate restTemplate;
    @Value("${spring.application.url}")
    private String url;
    @Value("${spring.application.employeebyidurl}")
    private String employeebyidurl;

    @Value("${spring.application.crateemployeeurl}")
    private String crateemployeeurl;


    @Value("${spring.application.deleteeemployeeurl}")
    private String deleteeemployeeurl;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Start of service method getAllEmployees");
        restTemplate=new RestTemplate();
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(url, ApiResponse.class);
        ApiResponse apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {
            for (Employee employee : apiResponse.getData()) {
                System.out.println(employee);
            }
            return ResponseEntity.ok(apiResponse.getData());
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }
    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        logger.info("Start of service method getEmployeeById");
        restTemplate=new RestTemplate();
        ResponseEntity<ApiResponseID> responseEntity = restTemplate.getForEntity(employeebyidurl.concat("/"+id), ApiResponseID.class);
        ApiResponseID apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {

            return ResponseEntity.ok(apiResponse.getData());
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        logger.info("Start of service method getHighestSalaryOfEmployees");
        restTemplate=new RestTemplate();
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(url, ApiResponse.class);
        System.out.println("response"+responseEntity);
        ApiResponse apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {
            for (Employee employee : apiResponse.getData()) {
                System.out.println(employee);
            }
            Integer highestsal=apiResponse.getData().stream().mapToInt(Employee::getEmployee_salary).max().orElse(0);

            return ResponseEntity.ok(highestsal);
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        logger.info("Start of service method getTopTenHighestEarningEmployeeNames");
        restTemplate=new RestTemplate();
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(url, ApiResponse.class);
        System.out.println("response"+responseEntity);
        ApiResponse apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {
            for (Employee employee : apiResponse.getData()) {
                System.out.println(employee);
            }
            List<String> top10EmployeeNames=apiResponse.getData().stream().sorted(Comparator.comparingInt(Employee::getEmployee_salary).reversed()).limit(3).map(Employee::getEmployee_name).collect(Collectors.toList());
            return ResponseEntity.ok(top10EmployeeNames);
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
        logger.info("Start of service method createEmployee");
        restTemplate=new RestTemplate();
        ResponseEntity<ApiResponseID> responseEntity = restTemplate.postForEntity(crateemployeeurl,employeeInput, ApiResponseID.class);
        System.out.println("response"+responseEntity);
        ApiResponseID apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {

            return ResponseEntity.ok(apiResponse.getData());
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        logger.info("Start of service method deleteEmployeeById");
        restTemplate=new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                deleteeemployeeurl.concat(id),
                HttpMethod.DELETE,
                null,
                String.class
        );
        return response;

    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        logger.info("Start of service method getEmployeesByNameSearch");
        restTemplate=new RestTemplate();
        System.out.println("url is:"+url);
        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(url, ApiResponse.class);
        ApiResponse apiResponse = responseEntity.getBody();
        if ("success".equals(apiResponse.getStatus())) {
            for (Employee employee : apiResponse.getData()) {
                System.out.println(employee);
            }
            List<Employee> employeesbyname=apiResponse.getData().stream().filter(employee -> employee.getEmployee_name().equals(searchString)).collect(Collectors.toList());
            return ResponseEntity.ok(employeesbyname);
        } else {
            System.out.println("Failed to fetch records: " + apiResponse.getMessage());
        }
        return null;
    }
}

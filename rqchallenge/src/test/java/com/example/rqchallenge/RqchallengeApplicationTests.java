package com.example.rqchallenge;

import com.example.rqchallenge.employees.ApiResponse;
import com.example.rqchallenge.employees.Employee;
import com.example.rqchallenge.employees.IEmployeeControllerImpl;
import com.example.rqchallenge.service.EmployeeService;
import com.example.rqchallenge.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class RqchallengeApplicationTests {

	@Mock
	private EmployeeService employeeService;
    @MockBean
	private RestTemplate restTemplate;
	@InjectMocks
	private IEmployeeControllerImpl employeeController;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllEmployees() throws IOException {

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1,"Ravi",122,31,""));
		when(employeeService.getAllEmployees()).thenReturn(ResponseEntity.ok(employees));
		ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employees, response.getBody());
	}


}

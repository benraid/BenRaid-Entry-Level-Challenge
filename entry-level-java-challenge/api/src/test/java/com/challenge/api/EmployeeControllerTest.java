package com.challenge.api;

import com.challenge.api.dto.EmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for the EmployeeController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    // Simplifies integration testing a lot vs RestTemplate. Will configure the client for us when testing.
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test GET /api/v1/employee to fetch all employees
     */
    @Test
    public void testGetAllEmployees() {
        ResponseEntity<EmployeeModel[]> response = restTemplate.getForEntity("/api/v1/employee", EmployeeModel[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Employee[] employees = response.getBody();
        assertThat(employees).isNotNull();
        assertThat(employees.length).isGreaterThanOrEqualTo(2); // At least Jane and John should be there
    }

    /**
     * Test GET /api/v1/employee/{uuid} to fetch a single employee
     */
    @Test
    public void testGetEmployeeByUuid() {
        // Get the list of employees to get a valid UUID to test with
        ResponseEntity<EmployeeModel[]> allEmployeesResponse = restTemplate.getForEntity("/api/v1/employee", EmployeeModel[].class);
        Employee[] employees = allEmployeesResponse.getBody();
        assertThat(employees).isNotNull();
        assertThat(employees.length).isGreaterThan(0);

        // use UUID of the first employee
        String uuid = employees[0].getUuid().toString();
        ResponseEntity<EmployeeModel> response = restTemplate.getForEntity("/api/v1/employee/" + uuid, EmployeeModel.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Employee employee = response.getBody();
        assertThat(employee).isNotNull();
        assertThat(employee.getUuid().toString()).isEqualTo(uuid);
    }

    /**
     * Test POST /api/v1/employee to create a new employee
     */
    @Test
    public void testCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Integration");
        request.setLastName("Test");
        request.setEmail("integration.test@example.com");
        request.setSalary(90000);
        request.setAge(40);
        request.setJobTitle("Tester");
        request.setContractHireDate(java.time.Instant.now());
        // Just left termination date as null for this test. They get to keep their job


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmployeeRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<EmployeeModel> response = restTemplate.postForEntity("/api/v1/employee", entity, EmployeeModel.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Employee createdEmployee = response.getBody();
        assertThat(createdEmployee).isNotNull();
        assertThat(createdEmployee.getFirstName()).isEqualTo("Integration");
        assertThat(createdEmployee.getLastName()).isEqualTo("Test");
        assertThat(createdEmployee.getFullName()).isEqualTo("Integration Test");
        assertThat(createdEmployee.getEmail()).isEqualTo("integration.test@example.com");
        assertThat(createdEmployee.getContractTerminationDate()).isNull(); // should just be null for this new employee
    }
}
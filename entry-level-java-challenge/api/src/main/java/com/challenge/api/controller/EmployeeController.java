package com.challenge.api.controller;

import com.challenge.api.dto.EmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST API controller exposing endpoints to manage Employee information
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * GET /api/v1/employee
     * @return unfiltered list of all employees
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * GET /api/v1/employee/{uuid}
     * @param uuid employee UUID
     * @return the employee matching the UUID
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        return employeeService.getEmployeeByUuid(uuid);
    }

    /**
     * POST /api/v1/employee
     * @param request the details required to create an employee
     * @return the newly created employee
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody EmployeeRequest request) {
        // Validate required fields (this can be further enhanced)
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required employee fields.");
        }

        EmployeeModel employee = new EmployeeModel();
        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(request.getFirstName() + " " + request.getLastName());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());
        employee.setContractTerminationDate(request.getContractTerminationDate());

        return employeeService.createEmployee(employee);
    }
}
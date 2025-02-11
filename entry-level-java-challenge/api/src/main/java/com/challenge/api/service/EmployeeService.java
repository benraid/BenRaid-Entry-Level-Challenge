package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    // Will store the mock data of employees
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    public EmployeeService() {
        // Add some mock employees
        Employee emp1 = new EmployeeModel("John", "Doe", 50000, 30, "Developer", "john.doe@example.com", java.time.Instant.now(), null);
        employees.put(emp1.getUuid(), emp1);

        Employee emp2 = new EmployeeModel("Jane", "Smith", 60000, 28, "Designer", "jane.smith@example.com", java.time.Instant.now(), null);
        employees.put(emp2.getUuid(), emp2);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employees.get(uuid);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for UUID: " + uuid);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        // Set the full name if not provided
        if (employee.getFirstName() != null && employee.getLastName() != null && (employee.getFullName() == null || employee.getFullName().isEmpty())) {
            employee.setFullName(employee.getFirstName() + " " + employee.getLastName());
        }
        employees.put(employee.getUuid(), employee);
        return employee;
    }
}
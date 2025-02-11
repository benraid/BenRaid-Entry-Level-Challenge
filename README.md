# ReliaQuest Employee API

ReliaQuest Entry-Level Java Challenge. It provides a simple REST API to manage employee data.

## Overview

- **GET /api/v1/employee**: Returns a list of all employees.
- **GET /api/v1/employee/{uuid}**: Returns details for a single employee by UUID.
- **POST /api/v1/employee**: Creates a new employee.

When the application starts it will automatically add two sample employees (John and Jane) to help test.

## What Was Added

- **EmployeeService.java**:  
  A service class that stores employees in memory. The service automatically creates two sample employees when the application starts.

- **EmployeeModel.java**:  
  The model class that implements the Employee interface.  

- **EmployeeRequest.java**:  
  A DTO used for creating new employees via the POST endpoint.

- **EmployeeController.java**:  
  A REST controller that exposes the three endpoints and uses the service to handle employee data.

- **Integration Tests**:  
  Tests using Spring Boot’s TestRestTemplate to check that the endpoints work correctly.

## How to Test the Application

### Integration Testing
- Just run EmployeeControllerTest.java in the test folder.

### Manual Testing
Use curl:
- **GET All Employees:** curl -X GET http://localhost:8080/api/v1/employee
- **GET Employee by UUID:** curl -X GET http://localhost:8080/api/v1/employee/{uuid}
- **POST Create Employee:**
```
curl -X POST http://localhost:8080/api/v1/employee \
-H "Content-Type: application/json" \
-d '{
"firstName": "Test",
"lastName": "User",
"salary": 70000,
"age": 29,
"jobTitle": "Software Engineer",
"email": "test@example.com",
"contractHireDate": "2025-02-11T00:00:00Z"
}'
```

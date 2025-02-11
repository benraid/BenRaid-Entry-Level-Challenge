package com.challenge.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * DTO that represents the data necessary for an Employee
 * Used lombok for getters and setters
 */
@Getter
@Setter
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate; // Nullable
}
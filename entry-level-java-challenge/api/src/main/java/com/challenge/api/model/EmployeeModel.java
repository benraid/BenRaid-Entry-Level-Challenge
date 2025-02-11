package com.challenge.api.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of the Employee interface
 * Just used lombok for getters and setters
 */
@Getter
@Setter
public class EmployeeModel implements Employee {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    public EmployeeModel() {}

    public EmployeeModel(String firstName, String lastName, Integer salary, Integer age, String jobTitle, String email, Instant contractHireDate, Instant contractTerminationDate) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
        this.contractTerminationDate = contractTerminationDate;
    }
}
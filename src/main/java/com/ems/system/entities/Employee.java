package com.ems.system.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must be less than or equal to 60")
    private int age;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "State is required")
//    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name must not contain special characters")
    private String state;

    @Positive(message = "Salary must be greater than zero")
    private double salary;

    @NotNull(message = "Join date is required")
    private LocalDate joinAt;


}

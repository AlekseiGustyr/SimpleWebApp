package com.mastery.java.task.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank
    @Size(min = 2, max = 50, message = "firstName's length should be from 2 to 50 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "lastName's length should be from 2 to 50 characters")
    private String lastName;

    @Min(value = 1, message = "departmentId should be positive( >0)")
    @Max(value = 20, message = "department with that id is doesn't exist")
    private int departmentId;

    @NotBlank
    @Size(min = 2, max = 50, message = "jobTitle length should be from 2 to 30 characters")
    private String jobTittle;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PastOrPresent
    private Date dateOfBirth;

}



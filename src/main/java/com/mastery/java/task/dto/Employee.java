package com.mastery.java.task.dto;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;


    @NotNull(message = "firstName shoudn't be empty")
    @Size(min = 2, max = 50, message = "firstName's lenght shoud be from 2 to 50 characters")
    private String firstName;

    @NotNull(message = "lastName shoudn't be empty")
    @Size(min = 2, max = 50, message = "lastName's lenght shoud be from 2 to 50 characters")
    private String lastName;


    @NotNull(message = "departmentId shoudn't be empty")
    @Min(value = 1, message = "departmentId should be positive( >0)")
    @Max(value = 20, message = "department with that id is doesn't exist")
    private int departmentId;


    @NotNull(message = "jobTittle shoudn't be empty")
    @Size(min = 2, max = 50, message = "jobTitle lenght shoud be from 2 to 30 characters")
    private String jobTittle;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PastOrPresent
    private Date dateOfBirth;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, int departmentId, String jobTittle, Gender gender, Date dateOfBirth) {

        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTittle = jobTittle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setJobTittle(String jobTittle) {
        this.jobTittle = jobTittle;
    }

    public String getJobTittle() {
        return jobTittle;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

}



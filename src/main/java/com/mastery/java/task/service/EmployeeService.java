package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeService extends JpaRepository<Employee, Long> {

}

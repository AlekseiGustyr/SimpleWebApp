package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public  Employee updateEmployee(Employee updatedEmployee, long id) {
        return employeeDao.findById(id).map(employee ->
        {   employee.setEmployeeId(updatedEmployee.getEmployeeId());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setDepartmentId(updatedEmployee.getDepartmentId());
            employee.setJobTittle(updatedEmployee.getJobTittle());
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());

            return employeeDao.save(employee);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public  String deleteEmployee(Long id)
    {
        log.info("delete employee");
        log.error("delete employee");
        employeeDao.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        employeeDao.deleteById(id);
        return "Employee deleted";
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee findEmployeeById(long id) {
        return employeeDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}

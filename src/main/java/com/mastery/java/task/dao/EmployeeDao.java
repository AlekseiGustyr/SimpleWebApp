package com.mastery.java.task.dao;


import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.userNotFoundException.userNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class EmployeeDao  {


    private static EmployeeService EmployeeService;


    @Autowired
    public  EmployeeDao(EmployeeService EmployeeService) {
        EmployeeDao.EmployeeService =EmployeeService;
   }

    public static Employee replaceEmployee(Employee updatedEmployee, long id)
    {
        return EmployeeService.findById(id).map(employee ->
        { employee.setEmployeeId(updatedEmployee.getEmployeeId());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setDepartmentId(updatedEmployee.getDepartmentId());
            employee.setJobTittle(updatedEmployee.getJobTittle());
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());

            return EmployeeService.save(employee);
        }).orElseThrow(() -> new userNotFoundException(id));
    }

}

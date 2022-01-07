package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.userNotFoundException.userNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService  {

    private static EmployeeDao EmployeeDao;


    @Autowired
    public EmployeeService(EmployeeDao EmployeeDao) {
        EmployeeService.EmployeeDao = EmployeeDao;
    }

    public static Employee replaceEmployee(Employee updatedEmployee, long id)
    {
        return EmployeeDao.findById(id).map(employee ->
        {   employee.setEmployeeId(updatedEmployee.getEmployeeId());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setDepartmentId(updatedEmployee.getDepartmentId());
            employee.setJobTittle(updatedEmployee.getJobTittle());
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());

            return EmployeeDao.save(employee);
        }).orElseThrow(() -> new userNotFoundException(id));
    }
}

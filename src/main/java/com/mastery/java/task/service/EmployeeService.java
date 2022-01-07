package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.userNotFoundException.userNotFoundException;
import com.mastery.java.task.rest.EmployeeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService  {

    public static   final Logger logger = LogManager.getLogger(EmployeeController.class.getSimpleName());

    @Autowired
    private static EmployeeDao EmployeeDao;

    public static Employee replaceEmployee(Employee updatedEmployee, long id)
    {
        if (!EmployeeDao.existsById(id)) {
            throw new userNotFoundException(id);
        }
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

    public  String deleteEmployee(Long id)
    {
        if(EmployeeDao.existsById(id))
        EmployeeDao.deleteById(id);
        else throw new userNotFoundException(id);
        return "Employee deleted";
    }

    public Employee saveEmployee(Employee employee) {

        return EmployeeDao.save(employee);
    }


    public Employee findEmployeeById(long id) {
        return EmployeeDao.findById(id).orElseThrow(() -> new userNotFoundException(id));
    }

    public List<Employee> findAll() {
        return EmployeeDao.findAll();
    }
}

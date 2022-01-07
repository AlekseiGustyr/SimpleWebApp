package com.mastery.java.task.rest;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.userNotFoundException.userNotFoundException;
import com.mastery.java.task.service.EmployeeService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "/employees")
public class EmployeeController {

    public static final Logger logger = LogManager.getLogger(EmployeeController.class.getSimpleName());

    private final EmployeeDao EmployeeDao;

    @Autowired
    private EmployeeDao EmployeeDa0;

    public EmployeeController(com.mastery.java.task.dao.EmployeeDao employeeDao) {
        EmployeeDao = employeeDao;
    }

    @DeleteMapping("/{id}")
    String deleteEmployee(@PathVariable long id) {

        logger.info("deleting employee with id= {}", id);
        if (EmployeeDao.existsById(id)) {
            EmployeeDao.deleteById(id);
            logger.info("user with id = {} successfully deleted ", id);
            return new String("user deleted");
        }
        logger.warn("user was not delete");
        return new String("user not deleted");
    }

    @PostMapping()
    Employee registration (@RequestBody @Valid Employee Employee){
            logger.info("Try to save new user");
            return EmployeeDao.save(Employee);
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable(name ="id") long id)  {
            return EmployeeDao.findById(id).orElseThrow(() -> new userNotFoundException(id));
        }


    @GetMapping()
    List<Employee> getEmployees(String message){
        return EmployeeDao.findAll();
    }


    @PutMapping("/{id}")
    Employee updateEmployee(@RequestBody  Employee updatedEmployee, @PathVariable long id )
    {
                logger.info("change employee info with id={}", id);
            if (!EmployeeDao.existsById(id)) {
                throw new userNotFoundException(id);
            }

               return EmployeeService.replaceEmployee(updatedEmployee,id);
        }


}

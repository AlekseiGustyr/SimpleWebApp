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

@RestController
public class EmployeeController {

    public static final Logger logger = LogManager.getLogger(EmployeeController.class.getSimpleName());

    private final EmployeeService EmployeeService;
    @Autowired
    private EmployeeDao EmployeeDa0;

    @Autowired
    public EmployeeController(EmployeeService EmployeeService) {
        this.EmployeeService=EmployeeService;
    }



    @DeleteMapping("/del/{id}")
    String deleteEmployee(@PathVariable long id) {

        logger.info("deleting employee with id= {}", id);
        if (EmployeeService.existsById(id)) {
            EmployeeService.deleteById(id);
            logger.info("user with id = {} successfully deleted ", id);
            return new String("user deleted");
        }
        logger.warn("user was not delete");
        return new String("user not deleted");
    }



    @PostMapping("/post")
    Employee registration (@RequestBody @Valid Employee Employee){
            logger.info("Try to save new user");
            return EmployeeService.save(Employee);
    }


    @GetMapping("/employer/{id}")
    Employee getEmployee(@PathVariable(name ="id") long id)  {
            return EmployeeService.findById(id).orElseThrow(() -> new userNotFoundException(id));
        }


    @GetMapping("/employes")
    List<Employee> getEmployees(String message){
        return EmployeeService.findAll();
    }


    @PutMapping("/put/{id}")
    Employee updateEmployee(@RequestBody  Employee updatedEmployee, @PathVariable long id )
    {
                logger.info("change employee info with id={}", id);
            if (!EmployeeService.existsById(id)) {
                throw new userNotFoundException(id);
            }

               return EmployeeDao.replaceEmployee(updatedEmployee,id);
        }


}

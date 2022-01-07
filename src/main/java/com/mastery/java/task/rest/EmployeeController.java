package com.mastery.java.task.rest;

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
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService EmployeeService;



    @DeleteMapping("/{id}")
    String deleteEmployee(@PathVariable long id)
    {
            return EmployeeService.deleteEmployee(id);
        }


    @PostMapping()
    Employee registration (@RequestBody @Valid Employee Employee)
    {
            return EmployeeService.saveEmployee(Employee);
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable(name ="id") long id)
    {
            return EmployeeService.findEmployeeById(id);
        }


    @GetMapping()
    List<Employee> getEmployees(String message){
        return EmployeeService.findAll();
    }


    @PutMapping("/{id}")
    Employee updateEmployee(@RequestBody  Employee updatedEmployee, @PathVariable long id )
    {
               return EmployeeService.replaceEmployee(updatedEmployee,id);
        }


}

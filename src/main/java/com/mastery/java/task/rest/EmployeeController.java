package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @DeleteMapping("/{id}")
    String deleteEmployee(@PathVariable long id)
    {
            return employeeService.deleteEmployee(id);
        }

    @PostMapping()
    Employee registration (@RequestBody @Valid Employee employee)
    {
            return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable(name ="id") long id)
    {
            return employeeService.findEmployeeById(id);
        }

    @GetMapping()
    List<Employee> getEmployees(String message){
        return employeeService.findAll();
    }

    @PutMapping("/{id}")
    Employee updateEmployee(@RequestBody  Employee updatedEmployee, @PathVariable long id )
    {
               return employeeService.replaceEmployee(updatedEmployee,id);
        }


}

package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Test
    public void getEmployeeByIdTest() {
        when(employeeService.findEmployeeById((long)1)).thenReturn(
                new Employee((long)1,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10)));

        Employee employee = employeeService.findEmployeeById((long)1);

        assertEquals("Lokesh", employee.getFirstName());
        assertEquals("Gupta", employee.getLastName());
        assertEquals("developer", employee.getJobTittle());
    }

    @Test
    public void getAllEmployeesTest() {
        List<Employee> list= new ArrayList<Employee>();
        Employee empOne= new Employee((long)1,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));
        Employee empTwo= new Employee((long)2,"Valerii","Gupta",4,"developer",(Gender)MALE,new Date( 2001-12-10));
        Employee empThree= new Employee((long)3,"Ludmila","Gupta",4,"developer",(Gender)FEMALE,new Date( 1999-10-20));

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeService.findAll()).thenReturn(list);

        List<Employee> employeesList=employeeService.findAll();

        assertEquals(3,employeesList.size());
        assertEquals("Ludmila", employeesList.get(2).getFirstName());
    }

    @Test
    public void createEmployeeTest() {
        Employee emp = new Employee((long)1,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));

        employeeService.saveEmployee(emp);

        verify(employeeService, times(1)).saveEmployee(emp);
    }

    @Test
    public void deleteEmployeeTest() {
        Employee emp = new Employee((long)1,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));

        employeeService.deleteEmployee((long)1);

        verify(employeeService,times(1)).deleteEmployee((long)1);
    }

    @Test
    public void updateEmployeeTest() {
        Employee emp = new Employee((long)1,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));
        Employee updateEmp = new Employee((long)1,"Lokesh","lastname",5,"developer",(Gender)MALE,new Date( 2000-10-10));

        assertEquals("Gupta",emp.getLastName());

        when(employeeService.updateEmployee(updateEmp,(long) 1)).thenReturn(updateEmp);

        Employee finalEmployee = employeeService.updateEmployee(updateEmp,(long) 1);

        assertEquals("lastname",finalEmployee.getLastName());
        assertEquals(5,finalEmployee.getDepartmentId());
    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFoundExceptionTest(){
        when(employeeService.findEmployeeById(2)).thenThrow(new UserNotFoundException((long) 2));

        employeeService.findEmployeeById(2);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteNotExistEmployeeTest(){

        when(employeeService.deleteEmployee((long)1)).thenThrow(new UserNotFoundException(1));

        employeeService.deleteEmployee((long)1);
    }
}

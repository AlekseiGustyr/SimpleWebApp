package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Log4j2
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao mockEmployeeDao;

    @After
    public void clean() {
        Mockito.reset(mockEmployeeDao);
    }

    @Test
    public void getEmployeeByIdTest() {
        log.debug("Service : getEmployeeByIdTest");

        Employee emp =new Employee(1L,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));
        Mockito.when(mockEmployeeDao.findById(1L)).thenReturn(Optional.of(emp));

        Employee employee = employeeService.findEmployeeById(1L);

        assertEquals("Lokesh", employee.getFirstName());
        assertEquals("Gupta", employee.getLastName());
        assertEquals("developer", employee.getJobTittle());
    }

    @Test
    public void getAllEmployeesTest() {
        log.debug("Service : getAllEmployeesTest");

        List<Employee> list= new ArrayList<Employee>();
        Employee empOne= new Employee(1L,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));
        Employee empTwo= new Employee(2L,"Valerii","Gupta",4,"developer",(Gender)MALE,new Date( 2001-12-10));
        Employee empThree= new Employee(3L,"Ludmila","Gupta",4,"developer",(Gender)FEMALE,new Date( 1999-10-20));

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        Mockito.when(mockEmployeeDao.findAll()).thenReturn(list);

        List<Employee> employeesList=employeeService.findAll();

        assertEquals(3,employeesList.size());
        assertEquals("Ludmila", employeesList.get(2).getFirstName());
    }

    @Test
    public void saveEmployeeTest() {
        log.debug("Service : saveEmployeeTest");

        Employee emp = new Employee(1l,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));

        Mockito.when(mockEmployeeDao.save(emp)).thenReturn(emp);

        Employee savedEmp = employeeService.saveEmployee(emp);

        assertEquals(1,savedEmp.getEmployeeId());
        assertEquals("Lokesh",savedEmp.getFirstName());
    }

    @Test
    public void deleteEmployeeTest() {
        log.debug("Service : deleteEmployeeTest");

         Mockito.doAnswer(delete -> {
            String deleted = new String("deleted");
            assertEquals("deleted",deleted);
            return null;
                }
        ).when(mockEmployeeDao).deleteById(1L);

        Employee emp = new Employee(1l,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));

        Mockito.when(mockEmployeeDao.findById(1L)).thenReturn(Optional.of(emp));

        employeeService.deleteEmployee(1L);

       verify(mockEmployeeDao).deleteById(1L);
    }

    @Test
    public void updateEmployeeTest() {
        log.debug("Service : updateEmployeeTest");

        Employee emp = new Employee(1L,"Lokesh","Gupta",4,"developer",(Gender)MALE,new Date( 2000-10-10));
        Employee updateEmp = new Employee(1L,"Lokesh","lastname",5,"developer",(Gender)MALE,new Date( 2000-10-10));

        Mockito.when(mockEmployeeDao.findById(1L)).thenReturn(Optional.of(emp));
        Mockito.when(mockEmployeeDao.save(emp)).thenReturn(updateEmp);

        Employee finalEmployee = employeeService.updateEmployee(updateEmp,1L);

        assertEquals("lastname",finalEmployee.getLastName());

    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFoundExceptionTest(){
        log.debug("Service : userNotFoundExceptionTest");

        when(mockEmployeeDao.findById(2L)).thenThrow(new UserNotFoundException(2L));

        employeeService.findEmployeeById(2L);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteNotExistEmployeeTest(){
        log.debug("Service : deleteNotExistEmployeeTest");

        Mockito.doThrow(UserNotFoundException.class)
        .when(mockEmployeeDao).deleteById(1L);

        employeeService.deleteEmployee(1L);
    }
}

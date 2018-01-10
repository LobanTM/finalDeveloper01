package com.loban.finalProject.service;

import com.loban.finalProject.config.MyCustomJpaConfig;
import com.loban.finalProject.model.Department;
import com.loban.finalProject.model.Employee;
import com.loban.finalProject.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { MyCustomJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void saveTest(){
        Employee saveEmployee = employeeService.save(new Employee("Jhon", "Smith", "jhon.smith@gmail.com",
                new Department("dep1"), new Position("director", 7000f), 1f,1f,1f,0f));
        assertNotNull(saveEmployee);
    }

    @Test
    public void findAllEmployeesTest(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Jhon", "Smith", "jhon.smith@gmail.com",
                new Department("dep1"), new Position("director", 7000f), 1f,1f,1f,0f));
        employeeList.add(new Employee("Eva", "Smith", "eva.smith@gmail.com",
                new Department("dep1"), new Position("counter", 3000f), 1f,1f,1f,0f));

        List<Employee> saveEmployeeList = new ArrayList<>();

        for (Employee e: employeeList){
            saveEmployeeList.add(employeeService.save(e));
        }

//        List<Employee> employeeListTest = employeeService.findAllEmployees();
//        assertNotNull(employeeListTest);
//        assertEquals(employeeList.size(), employeeListTest.size());
    }
}

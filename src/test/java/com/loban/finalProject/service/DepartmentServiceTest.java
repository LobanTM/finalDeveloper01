package com.loban.finalProject.service;

import com.loban.finalProject.config.MyCustomJpaConfig;
import com.loban.finalProject.model.Department;
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

public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void saveTest(){
        Department saveDepartment = departmentService.save(new Department("dep1"));
        assertNotNull(saveDepartment);
    }

    @Test
    public void findAllDepartmentsTest(){
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department("dep1"));
        departmentList.add(new Department("dep2"));

        for (Department d: departmentList){
            departmentService.save(d);
        }

        List<Department> departmentListTest = departmentService.findAllDepartment();
        assertEquals(departmentList.size(), departmentListTest.size());
    }
}

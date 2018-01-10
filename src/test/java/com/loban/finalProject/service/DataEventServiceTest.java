package com.loban.finalProject.service;

import com.loban.finalProject.config.MyCustomJpaConfig;
import com.loban.finalProject.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { MyCustomJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional

public class DataEventServiceTest {
    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Autowired
    private EventService eventService;

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    private StatusWork statusWorkTest;
    private Event eventTest;
    private Department departmentTest;
    private Position positionTest;
    private Employee employeeTest;
    private DateStatus dateStatusTest;
    private DateEvent dateEventTest;

    @Before
    public void beforeTest(){
//        Event
        eventTest = eventService.save(new Event("newEvent"));
//        StatusWork
        statusWorkTest = statusWorkService.save(new StatusWork("work"));
//        Department
        departmentTest = departmentService.save(new Department("dep1"));
//        Position
        positionTest = positionService.save(new Position( "sheff", 1000f));
//        Employee
        employeeTest = employeeService.save(new Employee("Firstname", "Lastname", "email@gmail.com",
                departmentTest, positionTest, 1f, 1f, 1f, 0f));
    }

    @Test
    public void saveTest(){
        DateEvent saveDateEvent = dateEventService.save(new DateEvent("08 01 2018", new Event("newEvent")));
        assertNotNull(saveDateEvent);
    }

    @Test
    public void createEventNewDayTest(){
        DateEvent dateEvent = dateEventService.createEventNewDay("08 01 2018");
        assertEquals("08 01 2018", dateEvent.getDate());
    }

}

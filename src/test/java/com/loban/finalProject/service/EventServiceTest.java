package com.loban.finalProject.service;

import com.loban.finalProject.config.MyCustomJpaConfig;
import com.loban.finalProject.model.Event;
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
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void saveTest(){
        Event saveDepartment = eventService.save(new Event("workDay"));
        assertNotNull(saveDepartment);
    }

    @Test
    public void findAllDepartmentsTest(){
        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("workDay"));
        eventList.add(new Event("softSkill"));

        for (Event e: eventList){
            eventService.save(e);
        }

        List<Event> eventListtTest = eventService.findAllEvent();
        assertEquals(eventList.size(), eventListtTest.size());
    }
}

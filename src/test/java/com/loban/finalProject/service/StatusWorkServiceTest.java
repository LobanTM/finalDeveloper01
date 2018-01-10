package com.loban.finalProject.service;


import com.loban.finalProject.config.MyCustomJpaConfig;
import com.loban.finalProject.model.StatusWork;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { MyCustomJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class StatusWorkServiceTest {

    @Autowired
    private StatusWorkService statusWorkService;

    @Test
    public void saveTest() {
        assertNotNull(statusWorkService.save(new StatusWork("work")));
    }

    @Test
    public void saveGetNameTest(){
        StatusWork saveStatusWork = statusWorkService.save(new StatusWork("work"));
        StatusWork statusWork = statusWorkService.findById(saveStatusWork.getId());
        assertEquals("work", statusWork.getName());
    }

    @Test
    public void getFindAllTest(){
        List<StatusWork> list = new ArrayList<>();
        list.add(new StatusWork("1"));
        list.add(new StatusWork("2"));

        List<StatusWork> saveList = new ArrayList<>();

        for(StatusWork s: list){
            statusWorkService.save(s);
        }
        saveList = statusWorkService.findAllStatusWork();
        assertEquals(list, saveList);

//        assertEquals("1", saveList.get(0).getName() );
    }


}

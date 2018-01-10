package com.loban.finalProject.service;

import com.loban.finalProject.config.MyCustomJpaConfig;
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
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Test
    public void saveTest(){
        Position savePosition = positionService.save(new Position("director", 7000f));
        assertNotNull(savePosition);
    }

    @Test
    public void findAllPositionsTest(){
        List<Position> positionList = new ArrayList<>();
        positionList.add(new Position("sheff", 5000f));
        positionList.add(new Position("susheff", 4000f));

        for (Position p: positionList){
            positionService.save(p);
        }

        List<Position> positionListTest = positionService.findAllPosition();
        assertEquals(positionList.size(), positionListTest.size());
    }
}

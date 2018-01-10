package com.loban.finalProject.service;

import com.loban.finalProject.model.Position;
import com.loban.finalProject.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position findById(Long id) {
        Position position1 = positionRepository.findOne(id);
        if(position1!=null){
            log.info("Find Position by ID {}, position: {}", id, position1.getName());
           return position1;
        }
        return null;
    }

    @Override
    public List<Position> findAllPosition() {
        List<Position> list = positionRepository.findAll();
        if (list!=null){
            log.info("Find all Positions ");
            return list;
        }
        return null;
    }

    @Override
    public List<Position> findByName(String name) {
        List<Position> list = positionRepository.findByName(name);
        if (list!=null){
            log.info("Find all Positions by name {}", name);
            return list;
        }
        return null;
    }

    @Override
    public Position save(Position position) {
        log.info("Save Position by ID {}", position.getId());
        Position savePosition = positionRepository.save(position);
        return savePosition;
    }

    @Override
    public void update(Position position) {

        Position position1 = positionRepository.findOne(position.getId());
        if(position1!=null){
            log.info("Update Position by ID {}", position.getId());
            positionRepository.delete(position1);
            positionRepository.save(position);
        }

    }

    @Override
    public void delete(Long id) {
        Position position1 = positionRepository.findOne(id);
        if(position1!=null){
            log.info("Delete Position by ID {}, position: {}", id, position1.getName());
            positionRepository.delete(position1);
        }

    }
}

package com.loban.finalProject.service;

import com.loban.finalProject.model.Position;

import java.util.List;

public interface PositionService {

    Position findById(Long id);
    List<Position> findAllPosition();
    List<Position> findByName(String name);

    Position save(Position position);
    void update(Position position);

    void delete(Long id);

}

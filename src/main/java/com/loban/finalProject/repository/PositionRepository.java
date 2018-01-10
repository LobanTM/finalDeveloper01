package com.loban.finalProject.repository;

import com.loban.finalProject.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByName (String name);
}

package com.loban.finalProject.repository;

import com.loban.finalProject.model.StatusWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusWorkRepository extends JpaRepository<StatusWork, Long> {

    StatusWork findByName(String name);
}

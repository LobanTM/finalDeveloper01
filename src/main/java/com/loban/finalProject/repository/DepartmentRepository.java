package com.loban.finalProject.repository;

import com.loban.finalProject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByName(String name);
}

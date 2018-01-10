package com.loban.finalProject.service;

import com.loban.finalProject.model.Department;

import java.util.List;

public interface DepartmentService {


    Department findById(Long id);
    List<Department> findAllDepartment();
    List<Department> findByName(String name);

    Department save(Department department);
    void update(Department department);
    void delete(Long id);
}

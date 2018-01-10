package com.loban.finalProject.service;

import com.loban.finalProject.model.Department;
import com.loban.finalProject.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        Department department = departmentRepository.findOne(id);
        if(department!=null){
            log.info("Find Department ID{}", id);
            return department;
        }
        return null;
    }

    @Override
    public List<Department> findAllDepartment() {
        List<Department> list = departmentRepository.findAll();
        if (list!=null){
            log.info("Find all Department");
            return list;
        }
        return null;
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> list = departmentRepository.findByName(name);

        if (list.size()>0){
            log.info("Find Depatrment by name {}", name);
            return list;
        }

        return null;
    }

    @Override
    public Department save(Department department) {

        log.info("Save Department ID {}", department.getId());
        Department saveDepartment = departmentRepository.save(department);
        return saveDepartment;
    }

    @Override
    public void update(Department department) {
        Department department1 = departmentRepository.findOne(department.getId());
        if (department1!=null){
            departmentRepository.delete(department.getId());
        }

        log.info("Update Department ID {}", department.getId());
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        Department department1 = departmentRepository.findOne(id);
        if (department1!=null){
            log.info("Delete Department ID {}", id);
            departmentRepository.delete(id);
        }
    }
}

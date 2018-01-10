package com.loban.finalProject.service;

import com.loban.finalProject.model.DateEvent;
import com.loban.finalProject.model.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee findById(Long id);
    List<Employee> findAllEmployees();

    Employee save(Employee employee);
    void update(Employee employee);
    void delete(Long id);

    //additional
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByEmail(String email);



}

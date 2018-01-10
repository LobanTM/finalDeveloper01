package com.loban.finalProject.repository;

import com.loban.finalProject.model.DateEvent;
import com.loban.finalProject.model.Department;
import com.loban.finalProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DateEventRepository extends JpaRepository<DateEvent, Long> {

    List<DateEvent> findByEmployees(List<Employee> employees);
    List<DateEvent> findByEmployeesAndDate(List<Employee> employees, String date);
    List<DateEvent> findByDepartments(List<Department> departments);

    DateEvent findByDate(String date);
}

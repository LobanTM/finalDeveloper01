package com.loban.finalProject.service;

import com.loban.finalProject.model.DateStatus;
import com.loban.finalProject.model.Employee;

import java.util.Date;
import java.util.List;

public interface DateStatusService {

    DateStatus findById(Long id);
    List<DateStatus> findAllSatuses();

    DateStatus save(DateStatus dateStatus);
    void update(DateStatus dateStatus);
    void delete(Long id);

    //additional
    List<DateStatus> findByEmployee(Employee employee);
    List<DateStatus> findByEmployeeAndDate(Employee employee, String date);
    List<DateStatus> findByDate(String date);

    //automatic create new Day (default all Employees have status "work")
    List<DateStatus> createDay(String date);
}

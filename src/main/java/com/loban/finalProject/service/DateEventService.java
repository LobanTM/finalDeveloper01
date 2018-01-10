package com.loban.finalProject.service;

import com.loban.finalProject.model.DateEvent;
import com.loban.finalProject.model.Department;
import com.loban.finalProject.model.Employee;

import java.util.Date;
import java.util.List;

public interface DateEventService {

    DateEvent findById(Long id);
    DateEvent findByDate(String date);
    List<DateEvent> findAllEvents();

    DateEvent save(DateEvent dateEvent);
    void update(DateEvent dateEvent);
    void delete(Long id);

    //additional
    List<DateEvent> findByEmployee(Employee employee);
    List<DateEvent> findByEmployeeAndDate(Employee employee, String date);
    List<DateEvent> findByDepartment(Department department);

//    automatic create new event of day (event = workDay, set = all employees with status = work);
    DateEvent createEventNewDay(String date);
//    void createEventToday();

//    report  for employee of period
    void reportEmployeeOfPeriod(Employee employee, String dateBeginPeriod, String dateEndPeriod);

}

package com.loban.finalProject.repository;

import com.loban.finalProject.model.DateStatus;
import com.loban.finalProject.model.Employee;
import com.loban.finalProject.model.StatusWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateStatusRepository extends JpaRepository<DateStatus, Long> {

    List<DateStatus> findByEmployee(Employee employee);
    List<DateStatus> findByDateAndEmployee(String date, Employee employee);
    List<DateStatus> findByDate(String date);
}

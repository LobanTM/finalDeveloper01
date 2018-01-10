package com.loban.finalProject.service;

import com.loban.finalProject.model.Employee;
import com.loban.finalProject.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee findById(Long id) {
        log.info("Find Employee ID {}", id);
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> findAllEmployees() {

        log.info("Find all Employees");
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        log.info("Save Employee ID{}", employee.getId());
        Employee saveEmployee = employeeRepository.save(employee);
        return saveEmployee;
    }

    @Override
    public void update(Employee employee) {
        log.info("Update Employee ID{}", employee.getId());
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findOne(id);
        if (employee!=null){
            log.info("Delete Employee ID {}", id);
        }else{
            log.info("Employee ID {} not exist", id);
        }
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        log.info("Find all Employees by first name {}", firstName);
        List<Employee> list = employeeRepository.findByFirstName(firstName);
        if (list.size()>0){
            return list;
        }
        log.info("Employees by first name {} not exist", firstName);
        return null;
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        log.info("Find all Employees by last name {}", lastName);
        List<Employee> list = employeeRepository.findByLastName(lastName);
        if (list.size()>0){
            return list;
        }
        log.info("Employees by last name {} not exist",lastName);
        return null;
    }

    @Override
    public List<Employee> findByEmail(String email) {
        log.info("Find all Employees by email {}", email);
        List<Employee> list = employeeRepository.findByEmail(email);
        if (list.size()>0){
            return list;
        }
        log.info("Employees by last name {} not exist",email);
        return null;
    }
}

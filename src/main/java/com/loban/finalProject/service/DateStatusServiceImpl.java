package com.loban.finalProject.service;

import com.loban.finalProject.model.DateStatus;
import com.loban.finalProject.model.Employee;
import com.loban.finalProject.model.StatusWork;
import com.loban.finalProject.repository.DateStatusRepository;
import com.loban.finalProject.repository.EmployeeRepository;
import com.loban.finalProject.repository.StatusWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DateStatusServiceImpl implements DateStatusService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private DateStatusRepository dateStatusRepository;


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    public static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MM yyyy");

    @Override
    public DateStatus findById(Long id) {
        log.info("Find DateStatus by ID {}", id);
        return dateStatusRepository.findOne(id);
    }

    @Override
    public List<DateStatus> findAllSatuses() {
        log.info("Getting list of all statuses");
        return dateStatusRepository.findAll();
    }

    @Override
    public DateStatus save(DateStatus dateStatus) {
        log.info("Save DateStatus by ID {}", dateStatus.getId());
        DateStatus saveDateStatus =  dateStatusRepository.save(dateStatus);
        return saveDateStatus;
    }

    @Override
    public void update(DateStatus dateStatus) {
        log.info("Update DateStatus by ID {}", dateStatus.getId());
        dateStatusRepository.save(dateStatus);
    }

    @Override
    public void delete(Long id) {
        if (dateStatusRepository.findOne(id)!=null){
            log.info("Delete DateStatus by ID {}", id);
            dateStatusRepository.delete(id);
        }
    }

    @Override
    public List<DateStatus> findByEmployee(Employee employee) {
        log.info("Find all DateStatus  for employee by ID {}",  employee.getId());
        return dateStatusRepository.findByEmployee(employee);
    }

    @Override
    public List<DateStatus> findByEmployeeAndDate(Employee employee, String date) {
        log.info("Find all DateStatus  for employee by ID {} and date DATE {}",  employee.getId(), date);
        return dateStatusRepository.findByDateAndEmployee(date, employee);
    }

    @Override
    public List<DateStatus> findByDate(String date) {
        return dateStatusRepository.findByDate(date);
    }

    @Override
    //automatic
    // create new Day (default all Employees have status "work")
    public List<DateStatus> createDay(String date) {
        List<DateStatus> saveDataStatusList = new ArrayList<>();

        if ("".equals(date)){
            date = formatForDateNow.format(new Date());
        }

        log.info("Create new Day (default all Employees have status \"work\")");
        //Set<Employee> employees - all employees in Company
        List<Employee> employeesList = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();
        for(Employee e: employeesList){
            employeeSet.add(e);
        }

        String[] dateDayList = date.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(dateDayList[2]),
                Integer.parseInt(dateDayList[1])-1,
                Integer.parseInt(dateDayList[0]));

        if ((calendar.get(Calendar.DAY_OF_WEEK)!=1)&&(calendar.get(Calendar.DAY_OF_WEEK)!=7)) {
            //String dateDay = formatForDateNow.format(new Date());
            log.info("!!!!!!!!!!!!!!!!!!!!!!! day {}", date);
            StatusWork statusWork = statusWorkRepository.findByName("work");

            for (Employee e : employeeSet) {

                List<DateStatus> list = dateStatusRepository.findByDateAndEmployee(date, e);
                if ((list == null) || (list.size() == 0)) {
                    log.info("create new DateStatus DATE {}, employee {}, status {}",
                            date, e.getLastName(), statusWork.getName());
                    saveDataStatusList.add(dateStatusRepository.save(new DateStatus(e,//employeeRepository.findOne(e.getId()),
                                            date, statusWork))
                                            );
                }
            }
        }

        log.info("End creating new Day DATE {}", date);
        List<DateStatus> dateStatusListReturn = dateStatusRepository.findByDate(date);

        return dateStatusListReturn;
//        return saveDataStatusList;
    }
}

package com.loban.finalProject.service;

import com.loban.finalProject.model.DateEvent;
import com.loban.finalProject.model.Department;
import com.loban.finalProject.model.Employee;
import com.loban.finalProject.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Slf4j
public class DateEventServiceImpl implements  DateEventService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private DateEventRepository dateEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MM yyyy");


    @Override
    public DateEvent findById(Long id) {
        log.info("Getting dateEvent by ID {}", id);
        return dateEventRepository.findOne(id);
    }

    @Override
    public DateEvent findByDate(String date) {
        DateEvent dateEvent = dateEventRepository.findByDate(date);
        if (dateEvent!=null){
            log.info("Find dateEvent by date {}", date);
            return dateEvent;
        }
        log.info("Find dateEvent by date {}", date);
        return null;
    }

    @Override
    public List<DateEvent> findAllEvents() {
        log.info("Find all DateEvent ");
        return dateEventRepository.findAll();
    }

    @Override
    public DateEvent save(DateEvent dateEvent) {
        log.info("Save DateEvent {}", dateEvent.getDate());
        DateEvent saveDateEvent =  dateEventRepository.save(dateEvent);
        return saveDateEvent;
    }

    @Override
    public void update(DateEvent dateEvent) {
        log.info("Update DateEvent {}", dateEvent.getDate());
        dateEventRepository.save(dateEvent);
    }

    @Override
    public void delete(Long id) {
        if (dateEventRepository.findOne(id)!=null){
            log.info("Delete DateEvent {}", dateEventRepository.findOne(id).getDate());
            dateEventRepository.delete(id);
        }
    }

    @Override
    public List<DateEvent> findByEmployee(Employee employee) {

        return null;
    }

    @Override
    public List<DateEvent> findByEmployeeAndDate(Employee employee, String date) {
        return null;
    }

    @Override
    public List<DateEvent> findByDepartment(Department department) {
        return null;
    }

    @Override
    //    automatic
    // create new event of day(date = NOW) (event = workDay, set = all employees with status = work);
    public DateEvent createEventNewDay(String date){

        DateEvent dateEventReturn = new DateEvent();

        if ("".equals(date)){
            date = formatForDateNow.format(new Date());
        }

        List<Employee> employeesList = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();
        for(Employee e: employeesList){
            employeeSet.add(e);
        }

        List<Department> departmentList = departmentRepository.findAll();
        Set<Department> departmentSet = new HashSet<>();
        for(Department d: departmentList){
            departmentSet.add(d);
        }

//        String dateDay = formatForDateNow.format(new Date());
        String[] dateDayList = date.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(dateDayList[2]),
                Integer.parseInt(dateDayList[1])-1,
                Integer.parseInt(dateDayList[0]));

//        check Day is WORK?
        if ((calendar.get(Calendar.DAY_OF_WEEK)!=1)&&(calendar.get(Calendar.DAY_OF_WEEK)!=7)){
            //day is not weekend
            if(dateEventRepository.findByDate(date) == null){
                //this date not exist
                log.info("create new DateEvent {}", date);
                dateEventReturn = dateEventRepository.save(new DateEvent(date, eventRepository.findOne(1L),
                        departmentSet, employeeSet));
            }
        }
        return dateEventReturn;

    }

    @Override
    public void reportEmployeeOfPeriod(Employee employee, String dateBeginPeriod, String dateEndPeriod) {

        log.info("Start make of report for employee: {}", employee);
        //out data
        Employee employeeReport = employee;
        Float reportSalaryOfPeriod = 0f;
        List<String> workDays = new ArrayList<>();


        String[] beginPeriod = dateBeginPeriod.split(" ");
        String[] endPeriod = dateEndPeriod.split(" ");
        GregorianCalendar calendarEndPeriod = new GregorianCalendar();

        calendarEndPeriod.set(Integer.parseInt(endPeriod[2]),
                Integer.parseInt(endPeriod[1])-1,
                Integer.parseInt(endPeriod[0]));
        GregorianCalendar calendarBeginPeriod = new GregorianCalendar();
        calendarBeginPeriod.set(Integer.parseInt(beginPeriod[2]),
                Integer.parseInt(beginPeriod[1])-1,
                Integer.parseInt(beginPeriod[0]));
//        count of period
        long s = (calendarEndPeriod.getTime().getTime() - calendarBeginPeriod.getTime().getTime())/86400000;

        for (int i = 0; i < s + 1; i++) {               //список по дням периода
            if (i != 0) {
                calendarBeginPeriod.add(Calendar.DATE, 1);
            }

            String dateDay = ((calendarBeginPeriod.get(Calendar.DATE)<10)?("0"):(""))
                    + calendarBeginPeriod.get(Calendar.DATE)
                    + (((calendarBeginPeriod.get(Calendar.MONTH) + 1)<10)?(" 0"):(" "))
                    + (calendarBeginPeriod.get(Calendar.MONTH) + 1)
                    + " "
                    + calendarBeginPeriod.get(Calendar.YEAR);
            //System.out.print(dateDay+" / ");

            Set<Employee> employeeSet = new HashSet<>();
            DateEvent dateEvent = dateEventRepository.findByDate(dateDay);

            if (dateEvent!=null) {                            //событие на заданую дату вообще есть
                employeeSet = dateEvent.getEmployees();
                for (Employee e : employeeSet) {
                    if (employee.getId().equals(e.getId())) {
                        //System.out.println("true");            //в списке события на эту дату есть такой сотрудник

                        //прибавляем ему ставку*коеффициент
                        Float koef = 1f;
                        switch (dateEvent.getEvent().getType()){
                            case "work": koef = e.getKoefWork();
                            case "ill": koef = e.getKoefIll();
                            case "holiday": koef = e.getKoefHoliday();
                            case "not work": koef = e.getKoefNotWork();
                        }
                        //добавляем дату в список отработанных дней
                        workDays.add(dateDay +" "+ dateEvent.getEvent().getType()+" "+e.getPosition().getSalary()
                                +" "+koef.toString());
                        reportSalaryOfPeriod += e.getPosition().getSalary()*koef;
                    }
                }
            }else System.out.println("set of this date "+dateDay+" not exist");
        }
        log.info("Start print of report for employee: {}", employee);
        System.out.println("=======  REPORT  =======");
        System.out.print(employeeReport.getId());
        System.out.print(" ");
        System.out.print(employeeReport.getFirstName());
        System.out.print(" ");
        System.out.print(employeeReport.getLastName());
        System.out.println(" ");
        System.out.print(employeeReport.getEmail());
        System.out.print(" ");
        System.out.print(employeeReport.getDepartment().getName());
        System.out.print(" ");
        System.out.print(employeeReport.getPosition().getName());
        System.out.println(" ");
        System.out.print("daily salary: ");
        System.out.println(employeeReport.getPosition().getSalary());

        System.out.println("WORK DAYS : COUNT = "+ workDays.size());
        for(String s1: workDays){
            System.out.println(s1);
        }
        System.out.println("REZULT = " + reportSalaryOfPeriod);

    }
}

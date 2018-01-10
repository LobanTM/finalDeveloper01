package com.loban.finalProject.view;

import com.loban.finalProject.config.DataConfig;
import com.loban.finalProject.model.*;
import com.loban.finalProject.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateDB {

    public static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MM yyyy");

    private StatusWorkService statusWorkService;
    private PositionService positionService;
    private DepartmentService departmentService;
    private EventService eventService;
    private DateStatusService dateStatusService;
    private DateEventService dateEventService;
    private EmployeeService employeeService;

    public void connectDB(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);

        statusWorkService = applicationContext.getBean(StatusWorkServiceImpl.class);
        positionService = applicationContext.getBean(PositionServiceImpl.class);
        departmentService = applicationContext.getBean(DepartmentServiceImpl.class);
        eventService = applicationContext.getBean(EventServiceImpl.class);

        dateStatusService = applicationContext.getBean(DateStatusServiceImpl.class);
        dateEventService = applicationContext.getBean(DateEventServiceImpl.class);
        employeeService = applicationContext.getBean(EmployeeServiceImpl.class);
    }

    public void editDB() {
        this.connectDB();

        System.out.println("~~~~EMPLOYEE~~~~~");

        List<Employee> employees = employeeService.findAllEmployees();
        if (employees!=null){
            for(Employee e: employees){
                System.out.println(e);
            }
        }



        String date = "";
//        dateEventService.createEventNewDay(date);
//        System.out.println("++++============++++++++");
//        System.out.println(dateStatusService.createDay(date));
//        dateEventService.reportEmployeeOfPeriod(employeeService.findById(5L), "02 01 2018",  "10 01 2018");

////       ==========  MAIL   ===============================================================
//        String to = "loban2010@meta.ua";         // sender email
//        String from = "tetyana.loban@gmail.com";       // receiver email
//        String host = "127.0.0.1";            // mail server host
//
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
////        properties.setProperty("mail.smtp.socketFactory.port", "25");
////        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
////        properties.setProperty("mail.smtp.port", "25");
//
//        Session session = Session.getDefaultInstance(properties); // default session
//
//        try {
//            MimeMessage message = new MimeMessage(session); // email message
//            message.setFrom(new InternetAddress(from)); // setting header fields
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("Test Mail from Java Program"); // subject line
//
//            // actual mail body
//            message.setText("You can send mail from Java program by using mail API, but you need" +
//                    "couple of more JAR files e.g. smtp.jar and activation.jar");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Email Sent successfully....");
//        } catch (MessagingException mex){
//            System.out.println("!!!!!!!!!!! " + mex);
//        }

////        ===================================================================================
    }

    public void showDB(){
        this.connectDB();
        System.out.println("~~~~EVENT~~~~~");
        List<Event> eventList = eventService.findAllEvent();
        if (eventList != null){
            for(Event e: eventList){
                System.out.println(e);
            }
        }
        System.out.println("~~~~STATUS~~~~~");
        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();
        if (statusWorkList!=null) {
            for (StatusWork s : statusWorkList) {
                System.out.println(s);
            }
        }else {
            System.out.println("null");
        }
        System.out.println("~~~~POSITION~~~~~");

        List<Position> positionList = positionService.findAllPosition();
        if (positionList!=null){
            for(Position p: positionList){
                System.out.println(p);
            }
        }
        System.out.println("~~~~DEPARTMENT~~~~~");

        List<Department> departmentList = departmentService.findAllDepartment();
        if (departmentList!= null){
            for(Department d: departmentList){
                System.out.println(d);
            }
        }
        System.out.println("~~~~EMPLOYEE~~~~~");

        List<Employee> employees = employeeService.findAllEmployees();
        if (employees!=null){
            for(Employee e: employees){
                System.out.println(e);
            }
        }
        System.out.println("~~~~DATE STATUS~~~~~");
        List<DateStatus> dateStatustList = dateStatusService.findAllSatuses();
        if (dateStatustList!=null){
            for (DateStatus dS: dateStatustList){
                System.out.println(dS);
            }
        }
        System.out.println("~~~~DATE EVENT~~~~~");
        List<DateEvent> dateEventList = dateEventService.findAllEvents();
        if (dateEventList!=null){
            for (DateEvent dE: dateEventList){
                System.out.println(dE);
            }
        }




    }

    public void fullDB(){
        this.connectDB();
        positionService.save(new Position("director", 10000f));
        positionService.save(new Position("sheff", 7000f));
        positionService.save(new Position("sub-sheff", 7000f));
        positionService.save(new Position("master sheff", 5000f));
        positionService.save(new Position("developer Java", 2000f));
        positionService.save(new Position("developer C#", 1000f));
        positionService.save(new Position("developer frontEnd", 1000f));

        departmentService.save(new Department("department01"));
        departmentService.save(new Department("department02"));
        departmentService.save(new Department("department03"));
        departmentService.save(new Department("department04"));
        departmentService.save(new Department("department05"));
        departmentService.save(new Department("department06"));

        eventService.save(new Event("workDay"));
        eventService.save(new Event("techskill"));

        statusWorkService.save(new StatusWork("work"));
        statusWorkService.save(new StatusWork("ill"));
        statusWorkService.save(new StatusWork("holiday"));
        statusWorkService.save(new StatusWork("not work"));


        employeeService.save(new Employee("Hugo","Boss","hugo.boss@gmail.com",
                departmentService.findById(1L), positionService.findById(1L),1f,1f,1f,0f));
        employeeService.save(new Employee("Tim","Taller","tim.taller@gmail.com",
                departmentService.findById(1L), positionService.findById(2L),1f,1f,1f,0f));
        employeeService.save(new Employee("Tom","Cruse","tom.cruse@gmail.com",
                departmentService.findById(1L), positionService.findById(3L),1f,1f,1f,0f));
        employeeService.save(new Employee("Will","Smith","will.smith@gmail.com",
                departmentService.findById(1L), positionService.findById(4L),1f,1f,1f,0f));
        employeeService.save(new Employee("Billy","Bons","billy.bons@gmail.com",
                departmentService.findById(1L), positionService.findById(5L),1f,1f,1f,0f));
        employeeService.save(new Employee("Tilly","Taller","tilly.taller@gmail.com",
                departmentService.findById(1L), positionService.findById(5L),1f,1f,1f,0f));
        employeeService.save(new Employee("Willy","Cruse","willy.cruse@gmail.com",
                departmentService.findById(1L), positionService.findById(5L),1f,1f,1f,0f));
        employeeService.save(new Employee("Polly","Smith","polly.smith@gmail.com",
                departmentService.findById(1L), positionService.findById(5L),1f,1f,1f,0f));

        dateStatusService.save(new DateStatus(employeeService.findById(1L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "20 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(1L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "21 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(1L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "22 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(1L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "23 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(1L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "24 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(1L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(2L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(3L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(4L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(5L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(6L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(7L), "25 12 2017", statusWorkService.findById(1L)));
        dateStatusService.save(new DateStatus(employeeService.findById(8L), "25 12 2017", statusWorkService.findById(1L)));



        Set<Department> departments = new HashSet<>();
        departments.add(departmentService.findById(1L));

        Set<Employee> employees = new HashSet<>();
        employees.add(employeeService.findById(1L));
        employees.add(employeeService.findById(2L));
        employees.add(employeeService.findById(3L));
        employees.add(employeeService.findById(4L));
        employees.add(employeeService.findById(5L));
        employees.add(employeeService.findById(6L));
        employees.add(employeeService.findById(7L));
        employees.add(employeeService.findById(8L));

        dateEventService.save(new DateEvent("20 12 2017", eventService.findById(1L), departments, employees));
        dateEventService.save(new DateEvent("21 12 2017", eventService.findById(1L), departments, employees));
        dateEventService.save(new DateEvent("22 12 2017", eventService.findById(1L), departments, employees));
        dateEventService.save(new DateEvent("23 12 2017", eventService.findById(1L), departments, employees));
        dateEventService.save(new DateEvent("24 12 2017", eventService.findById(1L), departments, employees));
        dateEventService.save(new DateEvent("25 12 2017", eventService.findById(1L), departments, employees));
    }
}

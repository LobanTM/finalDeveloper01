package com.loban.finalProject.model;

import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="dateevents")
public class DateEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="date")
//    private Date date;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Event event;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Set<Department> departments;

    //1
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Set<Employee> employees;

    //2
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "dateevents_employees",
//    joinColumns = { @JoinColumn(name = "events_id") },
//    inverseJoinColumns = { @JoinColumn(name = "employees_id") })
//    private Set<Employee> employees;

    //3
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
//    private Set<Employee> employees;

    public DateEvent() {
    }

    public DateEvent(String date, Event event) {
        this.date = date;
        this.event = event;
    }

    public DateEvent(String date, Event event, Set<Department> departments, Set<Employee> employees) {
        this.date = date;
        this.event = event;
        this.departments = departments;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DateEvent{" +
                "date=" + date +
                ", event=" + event +
                ", departments=" + departments +
//                ", employees=" + employees +
                '}';
    }
}

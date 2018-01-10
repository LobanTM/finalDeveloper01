package com.loban.finalProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @Column(name="koefWork")
    private Float koefWork;

    @Column(name="koefIll")
    private Float koefIll;

    @Column(name="koefHoliday")
    private Float koefHoliday;

    @Column(name="koefNotWork")
    private Float koefNotWork;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    List<DateStatus> statuses;

    //1
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "employees")
    List<DateEvent> events;

    //2
//    @ManyToMany(mappedBy = "employees")
//    List<DateEvent> events;

    //3
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "event_id", referencedColumnName = "id")
//    List<DateEvent> events;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(String firstName, String lastName, String email, Department department, Position position,
                    Float koefWork, Float koefIll, Float koefHoliday, Float koefNotWork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.position = position;
        this.koefWork = koefWork;
        this.koefIll = koefIll;
        this.koefHoliday = koefHoliday;
        this.koefNotWork = koefNotWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getKoefWork() {
        return koefWork;
    }

    public void setKoefWork(Float koefWork) {
        this.koefWork = koefWork;
    }

    public Float getKoefIll() {
        return koefIll;
    }

    public void setKoefIll(Float koefIll) {
        this.koefIll = koefIll;
    }

    public Float getKoefHoliday() {
        return koefHoliday;
    }

    public void setKoefHoliday(Float koefHoliday) {
        this.koefHoliday = koefHoliday;
    }

    public Float getKoefNotWork() {
        return koefNotWork;
    }

    public void setKoefNotWork(Float koefNotWork) {
        this.koefNotWork = koefNotWork;
    }

    public List<DateStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<DateStatus> statuses) {
        this.statuses = statuses;
    }

    public List<DateEvent> getEvents() {
        return events;
    }

    public void setEvents(List<DateEvent> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", koefWork=" + koefWork +
                ", koefIll=" + koefIll +
                ", koefHoliday=" + koefHoliday +
                ", koefNotWork=" + koefNotWork +
//                ", statuses=" + statuses +
//                ", events=" + events +
                '}';
    }
}

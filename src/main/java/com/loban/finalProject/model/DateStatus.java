package com.loban.finalProject.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="datestatuses")
public class DateStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name="date")
//    private Date date;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusWork statusWork;

    public DateStatus() {
    }

    public DateStatus(String date, StatusWork statusWork) {
        this.date = date;
        this.statusWork = statusWork;
    }

    public DateStatus(Employee employee, String date, StatusWork statusWork) {
        this.employee = employee;
        this.date = date;
        this.statusWork = statusWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public StatusWork getStatusWork() {
        return statusWork;
    }

    public void setStatusWork(StatusWork statusWork) {
        this.statusWork = statusWork;
    }

    @Override
    public String toString() {
        return "DateStatus{" +
                "id=" + id +
                ", employee=" + employee.getLastName() +
                ", date=" + date +
                ", statusWork=" + statusWork +
                '}';
    }
}

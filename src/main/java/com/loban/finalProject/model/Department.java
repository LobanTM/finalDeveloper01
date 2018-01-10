package com.loban.finalProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="departments")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "departments")
    private List<DateEvent> dateEvents;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private Set<Employee> employees;


    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DateEvent> getDateEvents() {
        return dateEvents;
    }

    public void setDateEvents(List<DateEvent> dateEvents) {
        this.dateEvents = dateEvents;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

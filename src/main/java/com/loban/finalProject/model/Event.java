package com.loban.finalProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<DateEvent> dateEvents;

    public Event() {
    }



    public Event(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public List<DateEvent> getDateEvents() {
//        return dateEvents;
//    }
//
//    public void setDateEvents(List<DateEvent> dateEvents) {
//        this.dateEvents = dateEvents;
//    }

    @Override
    public String toString() {
        return type;
    }
}

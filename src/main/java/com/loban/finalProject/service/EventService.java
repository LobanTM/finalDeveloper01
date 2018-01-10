package com.loban.finalProject.service;

import com.loban.finalProject.model.Event;

import java.util.List;

public interface EventService {

    Event findById(Long id);
    List<Event> findAllEvent();

    Event save(Event event);
    void update(Event event);
    void delete(Long id);
}

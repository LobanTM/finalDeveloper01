package com.loban.finalProject.service;


import com.loban.finalProject.model.Event;
import com.loban.finalProject.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateEventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findById(Long id) {
        log.info("Find Event by ID {}", id);
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> findAllEvent() {
        List<Event> list = eventRepository.findAll();
        if(list!=null){
            log.info("Find all Events");
            return list;
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        log.info("Save Event ID {}", event.getId());
        Event saveEvent =  eventRepository.save(event);
        return saveEvent;
    }

    @Override
    public void update(Event event) {
        Event event1 = eventRepository.findOne(event.getId());
        if(event1!=null){
            log.info("Update Event ID {}", event1.getId());
            eventRepository.delete(event.getId());
        }
        eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event1 = eventRepository.findOne(id);
        if(event1!=null){
            log.info("Delete Event ID {}", id);
            eventRepository.delete(id);
        }
    }
}

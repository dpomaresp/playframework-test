package services;

import daos.EventDAO;
import models.Event;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventService {
    @Inject
    private EventDAO eventDAO;

    public Event getEvent(Long id) {
        Event event = null;

        try {
            event =  eventDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return event;
    }

    public List<Event> getFilteredEvents(Optional<Long> tenant, Optional<Long> userId) {
        List<Event> eventList = new ArrayList<>();

        try {
            eventList =  eventDAO.findWithFilters(tenant, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public boolean save(Event event) {
        boolean saved = false;

        try {
            saved = eventDAO.save(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saved;
    }
}

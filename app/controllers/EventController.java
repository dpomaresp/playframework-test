package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Event;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.EventService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class EventController extends Controller {
    @Inject
    EventService eventService;

    public Result getEvent(Long id) {
        Event event = eventService.getEvent(id);

        if(event != null) {
            return ok(Json.toJson(event));
        } else {
            return notFound();
        }
    }

    public Result getEventWithFilters(Optional<Long> tenant, Optional<Long> userId) {

        List<Event> events = eventService.getFilteredEvents(tenant, userId);

        if(events != null && !events.isEmpty()) {
            return ok(Json.toJson(events));
        } else {
            return notFound();
        }
    }

    public Result saveEvent() {
        JsonNode jsonNode = request().body().asJson();
        Event event = Json.fromJson(jsonNode, Event.class);
        boolean saved = false;

        if(event != null) {
            saved = eventService.save(event);

            return ok(Json.toJson(saved));
        } else {
            return badRequest("Coudln't save the event. Please check the data provided is correct");
        }
    }
}

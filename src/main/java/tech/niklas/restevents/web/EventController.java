package tech.niklas.restevents.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.niklas.restevents.db.EventRepository;
import tech.niklas.restevents.db.PersonRepository;
import tech.niklas.restevents.exception.ConflictingEventException;
import tech.niklas.restevents.exception.EventNotFoundException;
import tech.niklas.restevents.model.Event;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
public record EventController(EventRepository eventRepository, PersonRepository personRepository) {
    @GetMapping("events/{id}")
    public Event findById(@PathVariable Integer id) {
        return eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }

    @PostMapping("events")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        if (event.getEnd_time().isBefore(event.getBegin_time())) {
            throw new ConflictingEventException();
        }

        Event savedEvent = eventRepository.save(event);
        String path = "/events/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath(path).build(savedEvent.getId());

        return ResponseEntity.created(uri).body(savedEvent);
    }
}

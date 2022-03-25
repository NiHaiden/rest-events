package tech.niklas.restevents.web;

import org.springframework.web.bind.annotation.*;
import tech.niklas.restevents.db.EventRepository;
import tech.niklas.restevents.db.PersonRepository;
import tech.niklas.restevents.exception.EventNotFoundException;
import tech.niklas.restevents.exception.PersonNotFoundException;
import tech.niklas.restevents.model.Event;
import tech.niklas.restevents.model.Person;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class PersonController {

    PersonRepository personRepository;
    EventRepository eventRepository;

    public PersonController(PersonRepository personRepository, EventRepository eventRepository) {
        this.personRepository = personRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/persons/events")
    public Set<Person> getEventsByPerson(@RequestParam(name = "personId") Integer personId) {
        return personRepository.findById(personId).orElseThrow(() -> {
            throw new PersonNotFoundException();
        }).getEvents();
    }

    @PostMapping("/persons/{id}/event")
    public Set<Event> addEventToPerson(@PathVariable Integer id, @RequestBody @Valid Event eventId) {
        Person person = personRepository.findById(id).orElseThrow(() -> { throw new PersonNotFoundException(); });
        Event event = eventRepository.findById(eventId.getId()).orElseThrow(() -> { throw new EventNotFoundException();});


    }
}

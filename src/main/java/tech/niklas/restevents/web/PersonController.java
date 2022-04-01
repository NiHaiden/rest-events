package tech.niklas.restevents.web;

import org.springframework.web.bind.annotation.*;
import tech.niklas.restevents.db.EventRepository;
import tech.niklas.restevents.db.PersonRepository;
import tech.niklas.restevents.exception.ConflictingEventException;
import tech.niklas.restevents.exception.EventNotFoundException;
import tech.niklas.restevents.exception.PersonNotFoundException;
import tech.niklas.restevents.model.Event;
import tech.niklas.restevents.model.Person;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public record PersonController(PersonRepository personRepository, EventRepository eventRepository) {
    @GetMapping("/allpersons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @GetMapping("/persons/events")
    public Set<Event> getEventsByPerson(@RequestParam(name = "personId") Integer personId) {
        return personRepository.findById(personId).orElseThrow(() -> {
            throw new PersonNotFoundException();
        }).getEvents();
    }

    @PostMapping("/persons/{id}/events")
    public Set<Event> addEventToPerson(@PathVariable Integer id, @RequestBody Event eventId) {
        Person person = personRepository.findById(id).orElseThrow(() -> {
            throw new PersonNotFoundException();
        });
        Event event = eventRepository.findById(eventId.getId()).orElseThrow(() -> {
            throw new EventNotFoundException();
        });

        person.getEvents().forEach(evelement -> {
            if (event.getBegin_time().isBefore(evelement.getEnd_time())
                    && event.getEnd_time().isAfter(evelement.getBegin_time())) {
                throw new ConflictingEventException();
            }
        });
        person.getEvents().add(event);
        personRepository.save(person);
        return person.getEvents();
    }
}

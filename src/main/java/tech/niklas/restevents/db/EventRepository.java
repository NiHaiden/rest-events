package tech.niklas.restevents.db;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.niklas.restevents.model.Event;
import tech.niklas.restevents.model.Person;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByPersonsContains(Person person);
}

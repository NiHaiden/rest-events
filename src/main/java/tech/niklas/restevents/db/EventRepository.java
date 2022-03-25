package tech.niklas.restevents.db;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.niklas.restevents.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}

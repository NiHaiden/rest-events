package tech.niklas.restevents.db;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.niklas.restevents.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}

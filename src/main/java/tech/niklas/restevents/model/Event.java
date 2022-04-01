package tech.niklas.restevents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @NotBlank(message = "Event-Name darf nicht leer sein!")
    @Column(name = "event_name")
    private String eventName;

    @NotNull(message = "Beginndatum darf nicht leer sein")
    @Column(name = "begin_time")
    private LocalDateTime begin_time;

    @NotNull(message = "Enddatum darf nicht leer sein!")
    @Column(name = "end_time")
    private LocalDateTime end_time;

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<Person> persons = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

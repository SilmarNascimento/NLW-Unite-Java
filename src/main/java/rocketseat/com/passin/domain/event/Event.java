package rocketseat.com.passin.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketseat.com.passin.controller.dto.event.EventInputDto;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String details;

  @Column(nullable = false, unique = true)
  private String slug;

  @Column(nullable = false, name = "maximum_attendees")
  private Integer maximumAttendees;

  public static Event parseEvent(EventInputDto eventInputDto) {
    Event newEvent = new Event();
    newEvent.setTitle(eventInputDto.title());
    newEvent.setDetails(eventInputDto.details());
    newEvent.setSlug(createSlug(eventInputDto.title()));
    newEvent.setMaximumAttendees(eventInputDto.maximumAttendees());
    return newEvent;
  }

  private static String createSlug(String text) {
    String normalized = Normalizer.normalize(text, Form.NFD);
    return normalized
        .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}", "")
        .replaceAll("[^\\w\\s]", "")
        .replaceAll("\\s+", "-")
        .toLowerCase();
  }
}

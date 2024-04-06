package rocketseat.com.passin.controller.dto.event;

import jakarta.persistence.Column;
import rocketseat.com.passin.domain.event.Event;

public record EventOutputDto(
    String id,
    String title,
    String details,
    String slug,
    Integer maximumAttendees
) {
  public static EventOutputDto parseDto(Event event) {
    return new EventOutputDto(
        event.getId(),
        event.getTitle(),
        event.getDetails(),
        event.getSlug(),
        event.getMaximumAttendees()
    );
  }
}

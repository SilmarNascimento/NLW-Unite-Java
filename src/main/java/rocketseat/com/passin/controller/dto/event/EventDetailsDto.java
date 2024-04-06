package rocketseat.com.passin.controller.dto.event;

import rocketseat.com.passin.domain.event.Event;

public record EventDetailsDto(
    String id,
    String title,
    String details,
    String slug,
    Integer maximumAttendees,
    Integer attendeesAmount
) {
  public static EventDetailsDto parseDto(Event event, Integer numberOfAttendees) {
    return new EventDetailsDto(
        event.getId(),
        event.getTitle(),
        event.getDetails(),
        event.getSlug(),
        event.getMaximumAttendees(),
        numberOfAttendees
    );
  }

}

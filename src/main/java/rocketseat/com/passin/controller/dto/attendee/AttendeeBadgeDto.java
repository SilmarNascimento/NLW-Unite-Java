package rocketseat.com.passin.controller.dto.attendee;

import rocketseat.com.passin.domain.attendee.Attendee;

public record AttendeeBadgeDto(
    String name,
    String email,
    String checkInUrl,
    String eventId
) {
  public static AttendeeBadgeDto parseDto(Attendee attendee, String uri) {
    return new AttendeeBadgeDto(
        attendee.getName(),
        attendee.getEmail(),
        uri,
        attendee.getEvent().getId()
    );
  }
}

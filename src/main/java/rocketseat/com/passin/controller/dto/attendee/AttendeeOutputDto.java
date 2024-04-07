package rocketseat.com.passin.controller.dto.attendee;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkIn.CheckIn;

public record AttendeeOutputDto(
    String id,
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime checkedInAt
) {
  public static AttendeeOutputDto parseDto(Attendee attendee, Function<String, Optional<CheckIn>> findCheckIn) {
    Optional<CheckIn> checkIn = findCheckIn.apply(attendee.getId());
    LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
    return new AttendeeOutputDto(
        attendee.getId(),
        attendee.getName(),
        attendee.getEmail(),
        attendee.getCreatedAt(),
        checkedInAt
    );
  }

  public static List<AttendeeOutputDto> parseDto(List<Attendee> attendees, Function<String, Optional<CheckIn>> findCheckIn) {
    return attendees.stream().map((attendee) -> {
      Optional<CheckIn> checkIn = findCheckIn.apply(attendee.getId());
      LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
      return new AttendeeOutputDto(
          attendee.getId(),
          attendee.getName(),
          attendee.getEmail(),
          attendee.getCreatedAt(),
          checkedInAt
      );
    }).toList();
  }
}

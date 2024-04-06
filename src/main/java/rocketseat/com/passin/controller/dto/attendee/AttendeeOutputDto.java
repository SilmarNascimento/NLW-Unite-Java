package rocketseat.com.passin.controller.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeOutputDto(
    String id,
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime checkedInAt
) {

}

package rocketseat.com.passin.controller.dto.attendee;

import java.util.List;

public record AttendeeListOutputDto(
    List<AttendeeOutputDto> attendees
) {

}

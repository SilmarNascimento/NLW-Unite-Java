package rocketseat.com.passin.controller.dto.event;

public record EventInputDto(
    String title,
    String details,
    Integer maximumAttendees
) {

}

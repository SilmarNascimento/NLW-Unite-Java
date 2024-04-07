package rocketseat.com.passin.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.controller.dto.attendee.AttendeeBadgeDto;
import rocketseat.com.passin.service.AttendeeService;
import rocketseat.com.passin.service.CheckInService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
  private final AttendeeService attendeeService;
  private final CheckInService checkInService;

  @GetMapping("/{attendeeId}/badge")
  public ResponseEntity<AttendeeBadgeDto> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
    return ResponseEntity.ok(attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder));
  }

  @PostMapping("/{attendeeId}/check-in")
  public ResponseEntity<Void> registerCheckIn(
      @PathVariable String attendeeId,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    attendeeService.checkInAttendee(attendeeId);

    URI uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

    return ResponseEntity
        .created(uri)
        .build();
  }
}

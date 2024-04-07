package rocketseat.com.passin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.controller.dto.attendee.AttendeeBadgeDto;
import rocketseat.com.passin.service.AttendeeService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
  private final AttendeeService attendeeService;

  @GetMapping("/{attendeeId}/badge")
  public ResponseEntity<AttendeeBadgeDto> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
    return ResponseEntity.ok(attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder));
  }
}

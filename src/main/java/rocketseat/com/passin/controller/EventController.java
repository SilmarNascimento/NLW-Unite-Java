package rocketseat.com.passin.controller;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.controller.dto.attendee.AttendeeInputDto;
import rocketseat.com.passin.controller.dto.attendee.AttendeeOutputDto;
import rocketseat.com.passin.controller.dto.event.EventDetailsDto;
import rocketseat.com.passin.controller.dto.event.EventInputDto;
import rocketseat.com.passin.controller.dto.event.EventOutputDto;
import rocketseat.com.passin.service.AttendeeService;
import rocketseat.com.passin.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;
  private final AttendeeService attendeeService;

  @GetMapping("/{eventId}")
  public ResponseEntity<EventDetailsDto> getEvent(@PathVariable String eventId) {
    return ResponseEntity
        .ok(eventService.getEventDetail(eventId));
  }

  @PostMapping
  public ResponseEntity<EventOutputDto> createEvent(
      @RequestBody EventInputDto inputDto,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    EventOutputDto outputDto = eventService.createEvent(inputDto);
    URI uri = uriComponentsBuilder.path("/events/{eventId}").buildAndExpand(outputDto.id()).toUri();

    return ResponseEntity
        .created(uri)
        .body(outputDto);
  }

  @GetMapping("/{eventId}/attendees")
  public ResponseEntity<List<AttendeeOutputDto>> getEventAttendees(@PathVariable String eventId) {
    return ResponseEntity
        .ok(attendeeService.getEventAttendees(eventId));
  }

  @PostMapping("/{eventId}/attendees")
  public ResponseEntity<AttendeeOutputDto> registerParticipant(
      @PathVariable String eventId,
      @RequestBody AttendeeInputDto inputDto,
      UriComponentsBuilder uriComponentsBuilder
  ) {
    AttendeeOutputDto outputDto = eventService.registerAttendeeOnEvent(eventId, inputDto);
    URI uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(outputDto.id()).toUri();

    return ResponseEntity
        .created(uri)
        .body(outputDto);
  }

}

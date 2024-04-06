package rocketseat.com.passin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketseat.com.passin.controller.dto.event.EventDetailsDto;
import rocketseat.com.passin.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;

  @GetMapping("/{eventId}")
  public ResponseEntity<EventDetailsDto> getEvent(@PathVariable String eventId) {
    EventDetailsDto serviceResponse = eventService.getEventDetail(eventId);
    return ResponseEntity.ok(serviceResponse);
  };

}

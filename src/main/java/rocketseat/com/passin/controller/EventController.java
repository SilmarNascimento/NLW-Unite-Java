package rocketseat.com.passin.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.controller.dto.event.EventDetailsDto;
import rocketseat.com.passin.controller.dto.event.EventInputDto;
import rocketseat.com.passin.controller.dto.event.EventOutputDto;
import rocketseat.com.passin.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
  private final EventService eventService;

  @GetMapping("/{eventId}")
  public ResponseEntity<EventDetailsDto> getEvent(@PathVariable String eventId) {
    return ResponseEntity.ok(eventService.getEventDetail(eventId));
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

}

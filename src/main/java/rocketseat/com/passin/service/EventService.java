package rocketseat.com.passin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.controller.dto.attendee.AttendeeInputDto;
import rocketseat.com.passin.controller.dto.attendee.AttendeeOutputDto;
import rocketseat.com.passin.controller.dto.event.EventDetailsDto;
import rocketseat.com.passin.controller.dto.event.EventInputDto;
import rocketseat.com.passin.controller.dto.event.EventOutputDto;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.execption.EventIsFullException;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final AttendeeService attendeeService;

  public EventDetailsDto getEventDetail(String eventId) {
    Event eventFound = getEventById(eventId);
    List<Attendee> attendeeList = attendeeService.getAllAttendeesFromEvent(eventId);
    return EventDetailsDto.parseDto(eventFound, attendeeList.size());
  }

  public EventOutputDto createEvent(EventInputDto eventInputDto) {
    Event newEvent = Event.parseEvent(eventInputDto);
    Event createdEvent = eventRepository.save(newEvent);
    return EventOutputDto.parseDto(createdEvent);
  }

  public AttendeeOutputDto registerAttendeeOnEvent(String eventId, AttendeeInputDto inputDto) {
    attendeeService.verifyAttendeeSubscription(eventId, inputDto.email());

    Event eventFound = getEventById(eventId);
    List<Attendee> attendeeList = attendeeService.getAllAttendeesFromEvent(eventId);
    if (attendeeList.size() >= eventFound.getMaximumAttendees()) throw new EventIsFullException();

    Attendee newAttendee = new Attendee(
        inputDto.name(),
        inputDto.email(),
        eventFound,
        LocalDateTime.now()
    );
    attendeeService.registerAttendee(newAttendee);

    return AttendeeOutputDto.parseDto(newAttendee, (event) -> Optional.empty());
  }

  private Event getEventById(String eventId) {
    return eventRepository.findById(eventId)
        .orElseThrow(() -> new RuntimeException("Event not found with Id: " + eventId));
  }
}

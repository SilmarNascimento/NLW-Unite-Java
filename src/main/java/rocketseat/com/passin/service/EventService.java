package rocketseat.com.passin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.controller.dto.event.EventDetailsDto;
import rocketseat.com.passin.controller.dto.event.EventInputDto;
import rocketseat.com.passin.controller.dto.event.EventOutputDto;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
  private final EventRepository eventRepository;
  private final AttendeeRepository attendeeRepository;

  public EventDetailsDto getEventDetail(String eventId) {
    Event eventFound = eventRepository.findById(eventId)
        .orElseThrow(() -> new RuntimeException("Event not found with Id: " + eventId));
    List<Attendee> attendeeList = attendeeRepository.findByEventId(eventId);
    return EventDetailsDto.parseDto(eventFound, attendeeList.size());
  }

  public EventOutputDto createEvent(EventInputDto eventInputDto) {
    Event newEvent = Event.parseEvent(eventInputDto);
    Event createdEvent = eventRepository.save(newEvent);
    return EventOutputDto.parseDto(createdEvent);
  }



}

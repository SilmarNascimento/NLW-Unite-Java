package rocketseat.com.passin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class Eventservice {
  private final EventRepository eventRepository;

  public void getEventDetail(String eventId) {
    Event eventFound = eventRepository.findById(eventId)
        .orElseThrow(() -> new RuntimeException("Event not found with Id: " + eventId));
  }

}

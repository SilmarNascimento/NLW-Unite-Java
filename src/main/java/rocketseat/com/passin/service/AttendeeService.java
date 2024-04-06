package rocketseat.com.passin.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.repositories.AttendeeRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
  private final AttendeeRepository attendeeRepository;

  public List<Attendee> getAllAttendeesFromEvent(String eventId) {
    return attendeeRepository.findAllByEventId(eventId);
  }
}

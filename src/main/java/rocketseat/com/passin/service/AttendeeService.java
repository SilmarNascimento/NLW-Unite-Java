package rocketseat.com.passin.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.controller.dto.attendee.AttendeeOutputDto;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkIn.CheckIn;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
  private final AttendeeRepository attendeeRepository;
  private final CheckInRepository checkInRepository;

  public List<Attendee> getAllAttendeesFromEvent(String eventId) {
    return attendeeRepository.findAllByEventId(eventId);
  }

  public List<AttendeeOutputDto> getEventAttendees(String eventId) {
    List<Attendee> attendeeList = getAllAttendeesFromEvent(eventId);
    return AttendeeOutputDto.parseDto(attendeeList, this::findCheckIn);
  }

  public Optional<CheckIn> findCheckIn(String attendeeId) {
    return checkInRepository.findByAttendeeId(attendeeId);
  }
}

package rocketseat.com.passin.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.passin.controller.dto.attendee.AttendeeBadgeDto;
import rocketseat.com.passin.controller.dto.attendee.AttendeeOutputDto;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkIn.CheckIn;
import rocketseat.com.passin.execption.AlreadyExistsException;
import rocketseat.com.passin.execption.NotFoundException;
import rocketseat.com.passin.repositories.AttendeeRepository;
import rocketseat.com.passin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
  private final AttendeeRepository attendeeRepository;
  private final CheckInService checkInService;

  public List<Attendee> getAllAttendeesFromEvent(String eventId) {
    return attendeeRepository.findAllByEventId(eventId);
  }

  public List<AttendeeOutputDto> getEventAttendees(String eventId) {
    List<Attendee> attendeeList = getAllAttendeesFromEvent(eventId);
    return AttendeeOutputDto.parseDto(attendeeList, checkInService::getCheckIn);
  }

  public void registerAttendee(Attendee newAttendee) {
    attendeeRepository.save(newAttendee);
  }

  public void checkInAttendee(String attendeeId) {
    Attendee attendeeFound = getAttendeebyId(attendeeId);
    checkInService.registerCheckIn(attendeeFound);
  }

  public AttendeeBadgeDto getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
    Attendee attendeeFound = getAttendeebyId(attendeeId);

    String uri = uriComponentsBuilder
        .path("/attendees/{attendeeId}/check-in")
        .buildAndExpand(attendeeId)
        .toUri().toString();

    return AttendeeBadgeDto.parseDto(attendeeFound, uri);
  }

  public void verifyAttendeeSubscription(String eventId, String email) {
    Optional<Attendee> attendeeRegistered = attendeeRepository.findByEventIdAndEmail(eventId, email);
    if (attendeeRegistered.isPresent()) throw new AlreadyExistsException("Attendee is already registered");
  }

  private Attendee getAttendeebyId(String attendeeId) {
    return attendeeRepository.findById(attendeeId)
        .orElseThrow(() -> new NotFoundException("Attendee not found with id: " + attendeeId));
  }
}

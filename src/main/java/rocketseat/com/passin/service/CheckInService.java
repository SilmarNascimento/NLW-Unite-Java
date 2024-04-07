package rocketseat.com.passin.service;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkIn.CheckIn;
import rocketseat.com.passin.execption.AlreadyExistsException;
import rocketseat.com.passin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class CheckInService {
  private final CheckInRepository checkInRepository;

  public CheckIn registerCheckIn(Attendee attendee) {
    verifyCheckInExists(attendee.getId());

    CheckIn newCheckIn = new CheckIn();
    newCheckIn.setAttendee(attendee);
    newCheckIn.setCreatedAt(LocalDateTime.now());

    return checkInRepository.save(newCheckIn);
  }

  private void verifyCheckInExists(String attendeeId) {
    Optional<CheckIn> checkIn= checkInRepository.findByAttendeeId(attendeeId);
    if (checkIn.isPresent()) throw new AlreadyExistsException("Attendee already checked in");
  }
}

package rocketseat.com.passin.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passin.domain.checkIn.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
  Optional<CheckIn> findByAttendeeId(String attendeeId);

}

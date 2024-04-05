package rocketseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passin.domain.checkIn.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

}
